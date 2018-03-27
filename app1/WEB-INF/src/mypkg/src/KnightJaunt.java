import java.util.ArrayList;

/**
 * Created by Ryan Coote on 3/23/2018.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class KnightJaunt {

    static String start;
    static String end;
    static char[] annotationLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    static int[] annotationNumbers = {1, 2, 3, 4, 5, 6, 7, 8};
    static String[][] chessboard = new String[8][8];
    static LinkedNodeList<String> path = new LinkedNodeList<>();
    static HashMap<String, myBoolean> squaresVisited = new HashMap<>();
    static LinkedList<LinkedNode<String>> processQueue = new LinkedList<>();
    static myBoolean trueBool = new myBoolean();
    static myBoolean falseBool = new myBoolean();
    static LinkedNode<String> lastNode;


    public KnightJaunt(String startSquare, String endSquare) {
        start = startSquare;
        end = endSquare;
        this.lastNode = new LinkedNode<>("Z9");
    }

    public String shortestJaunt() {
        //Initialize our LinkedNodeList with our starting Square
        LinkedNode<String> firstNode = new LinkedNode<>(start);
        System.out.println("First node is: " + firstNode.getData());
        trueBool.setVisit();
        processQueue = new LinkedList<>();

        path.addFirst(firstNode);
        System.out.println("Added first node " + firstNode.getData() + "to list");
        LinkedNode<String> currentNode = path.getFirst();
        System.out.println("Assigned currentNode: " + currentNode.getData() + " from first node " + firstNode.getData());

        //this code initializes the chessboard so we can mathematically move the knight around
        //and get the associated square annotation
        //it also initializes the Hash Map and search and find booleans
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard.length; j++) {
                String letter = String.valueOf(annotationLetters[i]);
                chessboard[i][j] = letter.concat(String.valueOf(annotationNumbers[j]));
                squaresVisited.put(chessboard[i][j], falseBool);
            }
        }

        //setting the start node as visited
        squaresVisited.put(start, trueBool);

        //iterate through till we find the end
        System.out.println("Searching for end point");
        processQueue.add(currentNode);
        //lastNode.setLastNode(knightPath(processQueue, squaresVisited));
        //lastNode = knightPath(processQueue, squaresVisited);
        knightPath(processQueue, squaresVisited);
        System.out.println("Found last node " + lastNode.getData());

        //get the proper order of moves
        System.out.println("Performing solution_agg");
        return KnightJaunt.solution_agg(path, lastNode);
    }



    public void knightPath(LinkedList<LinkedNode<String>> iterationQueue,
                                                HashMap<String, myBoolean> visited){
        LinkedNode<String> currentNode = iterationQueue.peekFirst();
        ArrayList<String> legalMoves;
        legalMoves = KnightJaunt.knightMoves(currentNode.getData(), chessboard);
        boolean endFound = false;
        LinkedNode<String> newNode = null;
        LinkedNode<String> endNode = new LinkedNode<String>("");

        for (int i = 0; i < legalMoves.size(); i++) {
            //check to see if the square has been visited
            if (visited.get(legalMoves.get(i)).getVisit() == false) {
                //set it so the square is visited
                visited.put(legalMoves.get(i), trueBool);

                //create the new Node and add it to path
                newNode = new LinkedNode(legalMoves.get(i));
                path.insertAfter(currentNode, newNode);
                if (newNode.getData().equals(end)) {
                    System.out.println("The end is " + newNode.getData());
                    endNode.setData(newNode.getData());
                    endNode.setPrev(newNode.getPrev());
                    endFound = true;
                    lastNode.setData(endNode.getData());
                    lastNode.setPrev(endNode.getPrev());
                    System.out.println("Checking if LastNode was Set");
                    System.out.println("Last Node " + lastNode.getData());
                }
            }
        }

        if(endFound == false) {
            List<LinkedNode<String>> children = currentNode.getNext();
            for (int i = 0; i < children.size(); i++) {
                iterationQueue.add(children.get(i));
            }
            iterationQueue.poll();
            knightPath(processQueue, squaresVisited);
        }
    }


    // this is a method that will add Nodes to the Linked List based upon given chess annotations
    // Each of the 8 types of chess move will be applied mathematically to the annotation and then
    // added as children if they are legal
    public static ArrayList<String> knightMoves(String annotation, String[][] chessboard) {

        ArrayList<String> solution = new ArrayList<>();
        char annotationChar = annotation.charAt(0);
        int annotationInt = Character.getNumericValue(annotation.charAt(1));
        int[][] validMoves = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
        int[] chessAnnot = new int[2];

        //these for loops get the 'position' relative to the chesboard, using the static arrays above
        for (int i = 0; i < annotationLetters.length; i++) {
            if (annotationChar == annotationLetters[i])
                chessAnnot[0] = i;
        }

        for (int i = 0; i < annotationNumbers.length; i++) {
            if (annotationInt == annotationNumbers[i])
                chessAnnot[1] = i;
        }

        //this loop calculates all of the available valid moves available for the knight at its current position
        //and adds the valid ones to the solution ArrayList
        for (int i = 0; i < validMoves.length; i++) {
            int column = chessAnnot[0];
            int row = chessAnnot[1];

            column = column + validMoves[i][0];
            row = row + validMoves[i][1];

            if (column > -1 && column < 8 && row > -1 && row < 8) {
                solution.add(chessboard[column][row]);
            }
        }
        System.out.println("Legal Moves for " + annotation + ": ");
        for(int i = 0; i<solution.size(); i++){
            System.out.println(solution.get(i));
        }


        return solution;
    }

    //this gets us the output in a clean fashion
    public static String solution_agg(LinkedNodeList path, LinkedNode<String> endNode) {
        System.out.println("Initiating solution_agg");
        LinkedNode<String> traverseNode = endNode;
        LinkedNodeList<String> solutionList = new LinkedNodeList<>();
        solutionList.addFirst(traverseNode);
        String[] scrap = new String[50];
        String solution = new String();
        int depth= 0;

        System.out.println("Beginning Node Traverse");
        while (traverseNode != null) {
            System.out.println("At node " + traverseNode.getData());

            //Writes the solution out
            scrap[depth] = traverseNode.getData();
            System.out.println(scrap[depth]);
            depth++;

            //sets our traverseNode to be the parent
            traverseNode = traverseNode.getPrev();
        }

        int counter = 0;
        for(int i = depth-1; i > -1; i--){
           if(i > 0){
               solution += "\"" + scrap[i] + "\" ,";
           }else{
               solution += "\"" + scrap[i] + "\" ";
           }
           counter++;
        }

        solution = "{ \"moves\" : [ " + solution + "]}";
        System.out.println("Traverse completed");
        System.out.println("Solution is :" + solution);
        return solution;
    }
}
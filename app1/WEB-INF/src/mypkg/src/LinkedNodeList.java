/**
 * Created by Ryan Coote on 3/24/2018.
 */

import java.util.*;

public class LinkedNodeList<E> {

    private LinkedNode<E> head;

    public LinkedNodeList()
    {
        head = null;
    }

    public void addFirst(LinkedNode<E> firstNode)
    {
        head = firstNode;
    }

    public void insertAfter(LinkedNode<E> before, LinkedNode<E> toInsert) {
        toInsert.setPrev(before);
        before.setNext(toInsert);
        }


    public void insertBefore(LinkedNode<E>after, LinkedNode<E> toInsert){
        toInsert.clearNext();
        toInsert.setNext(after);
        if(after == head){
            this.addFirst(toInsert);
        }
        after.setPrev(toInsert);
    }

    public LinkedNode<E> getFirst()
    {
        if(head == null) throw new NoSuchElementException();

        return head;
    }

    public LinkedNode<E> searchNodeChildren(LinkedNode<E> searchNode, E searchData){
        List<LinkedNode<E>> children = searchNode.getNext();
        LinkedNode<E> result = null;

        for(int i = 0; i < children.size(); i++){
            if(searchData == children.get(i).getData()){
                result = children.get(i);
                return result;
            }
        }

        for(int i = 0; i < children.size(); i++){
            result = searchNodeChildren(children.get(i), searchData);
        }

        return result;
    }

}



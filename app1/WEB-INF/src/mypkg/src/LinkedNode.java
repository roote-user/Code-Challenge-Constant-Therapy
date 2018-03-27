/**
 * Created by Ryan Coote on 3/24/2018.
 */

import java.util.List;
import java.util.ArrayList;

public class LinkedNode<E> {
    private E data;
    private LinkedNode<E> prev;
    private List<LinkedNode<E>> next;

    public LinkedNode(E data, LinkedNode<E> prev, List<LinkedNode<E>> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public LinkedNode(E data){
        this.data = data;
        this.prev = null;
        this.next = new ArrayList<>();
    }

    public LinkedNode<E> getPrev()
    {
        return this.prev;
    }

    public E getData(){
        return this.data;
    }

    public List<LinkedNode<E>> getNext(){
        return this.next;
    }

    public boolean hasChilden(){
            return this.next.isEmpty();
    }

    public void setData(E newData){
        this.data = newData;
    }

    public void setPrev(LinkedNode parent){
        this.prev = parent;
    }

    public void setNext(LinkedNode child){
        this.next.add(child);
    }

    public void clearNext(){
        this.next.clear();
    }

    public void clearPrev(){
        this.setPrev(null);
    }

}
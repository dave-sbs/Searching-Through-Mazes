/**
 * Dave Boku
 * April, 2023
 * CS 231
 * Project 7
 * Heap.java
 * 
 * Purpose: Creates a tree that works on the principles of a Heap and Left Complete Binary Tree. 
 */


import java.util.*;


public class Heap<T> implements PriorityQueue<T>{

    private static class Node<T>{
        Node<T> left, right, parent;
        T data;

        public Node(T data, Node<T> left, Node<T> right, Node<T> parent){
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private int size;
    private Node<T> root, last;
    private Comparator<T> comparator;

    public Heap(){
        this(null, false);
    }

    public Heap(boolean maxHeap){
        this(null, maxHeap);
    }

    public Heap (Comparator<T> comparator){
        this(comparator, false);
    }

    public Heap(Comparator<T> comparator, boolean maxHeap){
        if (comparator != null){
            this.comparator = comparator;
        } else {
            this.comparator = new Comparator<T>(){

                @Override
                public int compare(T o1, T o2){
                    return ((Comparable<T>) o1).compareTo(o2);
                }
            };
        } if (maxHeap){
            this.comparator = new Comparator<T>(){

                @Override
                public int compare(T o1, T o2){
                    return ((Comparable<T>) o2).compareTo(o1);
            }
        };
        }
    }

    @Override
    public void offer(T item) {
        if (size == 0){
            root = new Node<T>(item, null, null, null);
            last = root;
            size++;
            return;
        }

        //whenever size is even we don't have to switch parent
        //size being all elements in a tree
        if (size % 2 == 0){
            last.parent.right = new Node<T>(item, null, null, last.parent);
            last = last.parent.right;
        } else {
            Node<T> curNode = last;
            while (curNode != root && curNode == curNode.parent.right){
                curNode = curNode.parent;
            }

            if (curNode != root){
            curNode = curNode.parent.right;
            }

            while(curNode.left != null){
                curNode = curNode.left;
            }

            curNode.left = new Node<T>(item, null, null, curNode);
            last = curNode.left;
        }
        size++;
        bubbleUp(last);
    }

    private void swap(Node<T> node1, Node<T> node2){
        T temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    private void bubbleUp(Node<T> curNode){
        if(curNode == root){
            return;
        }

        T myData = curNode.data;
        T parentData = curNode.parent.data;
        if(comparator.compare(myData, parentData) < 0){
            swap(curNode, curNode.parent);
            bubbleUp(curNode.parent);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        return root.data;
    }

    /**
     * remove root
     * 
     * place the last element to the top
     * 
     * bubble down
     */


     public T poll() {
        if (size == 0) {
            return null;
        }
        
        T removed = root.data;
    
        if (size == 1) {
            root = null;
            last = null;
            size--;
            return removed;
        }
    
        if (size % 2 != 0) {
            root.data = last.data;
            last.parent.right = null;
            last = last.parent.left;
        } else {
            Node<T> curNode = last;
            root.data = last.data;
    
            if (size == 2) {
                last = root;
                root.left = null;
            } else {
                while (curNode != root && curNode == curNode.parent.left) {
                    curNode = curNode.parent;
                }
    
                if (curNode != root) {
                    curNode = curNode.parent.left;
                }
    
                while (curNode.right != null) {
                    curNode = curNode.right;
                }
                last.parent.left = null;
                last = curNode;
            }
        }
        size--;
        bubbleDown(root);
        return removed;
    }
    

    /**
     * Moves a node downwards comparing order of priority and swaping objects of lower priority with high priority objects
     * 
     * @param curNode
     */
    private void bubbleDown(Node<T> curNode){

        if (curNode.left == null){
            return;
        } else if (curNode.right == null){
            if(comparator.compare(curNode.left.data, curNode.data) < 0){
                swap(curNode, curNode.left);
                bubbleDown(curNode.left);
            }
        }
        else{
            if(comparator.compare(curNode.left.data, curNode.right.data) < 0){
                if (comparator.compare(curNode.left.data, curNode.data) < 0){
                    swap(curNode, curNode.left);
                    bubbleDown(curNode.left);
                }
            } else {
                if (comparator.compare(curNode.right.data, curNode.data) < 0){
                swap(curNode, curNode.right);
                bubbleDown(curNode.right);
                }
            }
       }
    }

    /**
     * Breadth First or Depth Firrst
     */
    @Override
    public void updatePriority(T item) {
        //Updates the priority of the given item
        if(root.data == item){
            bubbleDown(root);
        }
        else if(size <= 3){
            if(root.left.data == item){
                bubbleUp(root.left);
            }
            else{
                bubbleUp(root.right);
            }
        }

        else if(root.left != null){
            updatePriority(item, root.left);
        }
    }
    
    public void updatePriority(T item, Node<T> curNode){
        // updates the priority of a given item
        if(curNode.data == item){
            if(comparator.compare(item, curNode.parent.data) < 0){
                bubbleUp(curNode);
            } else{
                bubbleDown(curNode);
            }
        }
        else{
            if(curNode.left != null){
                updatePriority(item, curNode.left);
            }
            else if(curNode.parent.right != null){
                updatePriority(item, curNode.parent.right);
            }
            else{
                updatePriority(item, curNode.parent.parent.right);
            }
        }
    }
}
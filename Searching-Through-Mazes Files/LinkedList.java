/**
 * Name: Dave Boku
 * Date: 03/06/2023
 * Project 5
 * LinkedList.java
 * CS 231A
 */

 import java.util.Iterator;    // defines the Iterator interface
 import java.util.ArrayList;   

 
 public class LinkedList<T> implements Iterable<T>, Queue<T>, Stack<T>
 {
     
     private class LLIterator implements Iterator<T>{
         Node<T> transversal;
 
         public LLIterator(Node<T> head){
         // the constructor for the LLIterator given the head of a list.
             transversal = head;
         }
 
         public boolean hasNext(){
             //returns true if there are still values to traverse
             return (transversal != null);
         }
         
         public T next(){
             // returns the next item in the list
             T current = transversal.getData();
             transversal = transversal.getNext();
             return current;
         }
 
         public void remove(){
 
         }
     }
 
     private static class Node<T>{
         T data;
         Node<T> next;
         Node<T> prev;
 
         public Node(T data) {
             this(data, null, null);
         }
 
 
         public Node(T data, Node<T> next) {
             this.data = data;
             this.next = next;
         }
 
         public Node(T data, Node<T> next, Node<T> prev) {
             this.data = data;
             this.next = next;
             this.prev = prev;
         }
 
         public T getData() {
             return data;
         }
 
         public void setNext(Node<T> next) {
             this.next = next;
         }
 
         public Node<T> getNext() {
             return next;
         }
 
         public void setPrev(Node<T> prev) {
             this.prev = prev;
         }
 
         public Node<T> getPrev() {
             return prev;
         }
     }
     
     private int size;
 
     private Node<T> head;
 
     private Node<T> tail;
 
     public Iterator<T> iterator(){
         // Return a new LLIterator pointing to the head of the list
         return new LLIterator(this.head);
     }
 
     public LinkedList(){
         //constructor that initializes the fields so it is an empty list.
         size = 0;
         head = null;
 
     }
 
 
     public T get(int index){
         //returns the item specified by the given index.
         if(index < 0 || index >= size){
             System.out.println("index out of bounds");
             return null;
         }
         Node<T> walker = head;
         for(int i = 0; i < index; i++){
             walker = walker.getNext();
         } 
 
         return walker.getData();
     }
 
     public void add(T data) {
         // adds at start of list
         Node<T> newNode = new Node<>(data);
         if (size == 0) {
             tail = newNode;
         } 
         else {
             head.setPrev(newNode);
             newNode.setNext(head);
         }
         head = newNode;
         size++;
     }
 
     public void add(int index, T item){
         // inserts the item at the specified position in the list.
         if (index == 0) {
             this.add(item);
         } else if (index == size){
            this.addLast(item);
         }
         else {
             Node<T> walker = head;
             for (int i = 0; i < index - 1; i++) {
                 walker = walker.getNext();
             }
             Node<T> newNode = new Node<T>(item, walker.getNext());
             walker.setNext(newNode);
             size++;
         }
     }
 
     public void addLast(T data) {
         //inserts the item at the end of the list.
         Node<T> newNode = new Node<>(data);
         if (size == 0) {
            head = newNode;
         }
         else{
            tail.setNext(newNode);
            newNode.setPrev(tail);
         }
         tail = newNode;
         size++;
    }
 
     public int size(){
         //returns the size of the list
         return size;
     }
 
     public boolean isEmpty(){
         // returns true if the list is empty, otherwise this method returns false
         return size == 0;
     }
     
     public void clear(){
         //empties the list (resets the fields so it is an empty list).
         size = 0;
         head = null;
     }
     
 
     public T remove(){
         //removes the first item of the list and returns it.
         Node<T> removed = head;
         head = head.getNext();
         size--;
         return removed.getData();
     }
 
     public T remove(int index){
         //removes the item at the specified position in the list and returns it.
         if(index == 0){
             return this.remove();
         }
         else{
             Node<T> walker = head;
             for(int i = 0; i < index - 1 ; i++){
                 walker = walker.getNext();
             }
             Node<T> removed = walker.getNext();
             walker.setNext(walker.getNext().getNext());
             size--;
             return removed.getData();
         }
     }
 
     public boolean contains(Object o){
         //returns true if o is present in this list, otherwise this method returns false.
         for(int i = 0; i < this.size(); i++){
             if(this.get(i).equals(o)){
                 return true;
             }
         }
         return false;
     }
 
     public boolean equals(Object o){
         //returns true if o is a LinkedList that also contains the same items in the same order.
         if (!(o instanceof LinkedList)){
             return false;
         }
         // If I have reached this line, o must be a LinkedList
         @SuppressWarnings("unchecked") 
         LinkedList<T> oAsALinkedList = (LinkedList<T>) o;
         // Now I have a reference to something Java knows is a LinkedList!
         if(oAsALinkedList.size() != this.size()){
             return false;
         }
         for(int i = 0; i < this.size(); i++){
             if(!oAsALinkedList.get(i).equals(this.get(i))){
                 return false;
             }
         }
         return true;
     }
 
 
     public ArrayList<T> toArrayList(){
         // converts linkedlist to arraylist
         ArrayList<T> arrLL = new ArrayList<T>(size);
         for(int i = 0; i < this.size(); i++){
             arrLL.add(this.get(i));
         }
         return arrLL;
     }
 
     public String toString(){
         //returns a String representation of the list.
         String items = "";
         for(int i = 0; i < this.size(); i++){
            items += this.get(i) + " ";
         }
         return items;
     }
 
 
     public void offer(T item) {
        addLast(item);
     }
 
     public T peek() {
        return get(0);
     }
 
     public T poll() {
        return remove();
     }

     public T pop() {
        return remove();
     }

     public void push(T item){
        add(item);
     }
 }
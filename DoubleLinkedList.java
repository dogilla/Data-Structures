public class DoubleLinkedList {
  private class Node{
    int data;
    Node next;
    Node previous;

    public Node(int data) {
      this.data = data;
    }
   }

   Node head;
   Node tail;
   int elements;

   public void add(int element) {
     if (head == null) {
       head = new Node(int element);
       return null;
     } else {
       Node current = head;
       while(current.next != null) {
         current = current.next;
       }
       added = new Node(int element);
       current.next = added;
       added.previous = current; 
     }
   }

   public void remove() {
     if(head == null) return;
     search(int element);
   }

   public



}

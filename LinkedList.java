public class LinkedList {
  private class Node{
    int data;
    Node next;

    public Node(int data) {
      this.data = data;
    }
   }

   Node head;
   int elements;

   public void append(int element) {
     if (head == null) {
       head = new Node(int element);
       return null
     } else {
       Node current = head;
       while(current.next != null) {
         current = current.next;
       }
       current.next = new Node(int element);
     }
   }


}

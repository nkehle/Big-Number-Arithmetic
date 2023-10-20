public class Node {  //represents one digit in the linked list
    public int digit;
    public int carry;
    public Node next;
    public Node(int digit){
        this.digit = digit;
        this.next = null;
        this.carry = 0;
    }
}

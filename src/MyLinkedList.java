
public class MyLinkedList {
    private Node head;  //last digit in a number
    private Node tail;
    private int numItems;

    private String printVal;

    public int getNumItems(){ return this.numItems;}
    public Node getHead(){ return this.head;}

    public MyLinkedList(Node head){  //singly linked list
        this.head = head;
        this. numItems = 1;
        this.tail = head;
    }
    public MyLinkedList(){  //singly linked list without parameter
        Node def = new Node(0);
        this.head = def;
        this. numItems = 0;
        this.tail = null; //from def to null
    }

    public void addNode(int digit){
        Node newNode = new Node(digit);
        if (this.numItems == 0){
            this.head = newNode;
            this.head.next = this.tail;
            this.tail = newNode;
            //this.head.next = this.tail;

        } else if (this.numItems == 1){  //if there's only one item in the list
            this.head.next = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.numItems ++;
    }

    public void makeBigNum(String num){
        int ctr = num.length();
        if (isZero(num)){
            addNode(0);
        } else{
            for (int i = num.length()-1; i >= 0; i--){
                char tmp = num.charAt(i);
                addNode(Character.getNumericValue(tmp));
            }
//            while(num != 0){
//                int remain = num % 10;
//                addNode(remain);
//                num = num / 10;
//            }
        }
    }
    public static boolean isZero(String num){
        for(int i = 0; i < num.length(); i++){
            if (Character.getNumericValue(num.charAt(i)) != 0) {
                return false;
            }
        } return true;
    }

//    public void removeZeros(){
//        int ctr = 0;
//        String str = this.reverseStr();
//        char ch = str.charAt(ctr);
//
//        if(isZero(str)){
//            Node tmp = new Node(0);
//            this.head = tmp;
//            this.head.next = null;
//        } else {
//            while (ch == '0'){
//                this.head = this.head.next;
//                ctr ++;
//            }
//
//        }
//    }

    public void clearCarry(){
        Node tmp = this.head;
        for(int i = 0; i < this.numItems; i++){
            tmp.carry = 0;
            tmp = tmp.next;
        }
    }

    public void resetListMult(int j){
        this.numItems = 0;
        this.head = null;
        this.tail = null;

        for(int i = 0; i < j; i++){
            this.addNode(0);
        }

    }

//    public void printList() {  //prints the list
//        Node tmp = this.head;
//        while (tmp != null) {
//            System.out.print(tmp.digit);
//            tmp = tmp.next;
//        }
//    }

    public String reverseStr(){  //reverses the ints so they can easily be added into MyLinkedList
        char ch;
        String res = "";
        for(int i=0; i < this.numItems; i ++){
            ch = this.toString().charAt(i);
            res = ch + res;
        }
        return res;
    }

    @Override
    public String toString(){
        Node tmp = this.head;
        String res = "";
        while (tmp != null) {
            res += Integer.toString(tmp.digit);
            tmp = tmp.next;
        }
        return res;
    }

}

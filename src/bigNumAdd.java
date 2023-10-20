
public class bigNumAdd implements Expression{

    //values that can be changed for adding and carrying
    public MyLinkedList num1;
    public MyLinkedList num2;

    //used if the values get changed in the adding process
    public String ogNum1;
    public String ogNum2;
    public String op;
    public MyLinkedList result;

    public bigNumAdd(MyLinkedList num1, MyLinkedList num2, String op){
        this.num1 = num1;
        this.num2 = num2;
        this.op = op;
        this.result = new MyLinkedList();
        //copy original values
        this.ogNum1 = num1.reverseStr();
        this.ogNum2 = num2.reverseStr();
    }

    public MyLinkedList compute(){
        MyLinkedList max;
        MyLinkedList min;

        if (this.num1.getNumItems() == this.num2.getNumItems()){
            max = this.num1;
            min = this.num2;
        } else {
            max = getMaxBigNum();
            min = getMinBigNum();
        }
        Node tmpNode1 = max.getHead();
        Node tmpNode2 = min.getHead();

        for (int i = 0; i < min.getNumItems(); i++){      // add first digit
            int tmpVal = tmpNode1.digit + tmpNode2.digit;
            if (tmpVal > 9){                              // if needing to carry
                if (tmpNode1.next == null){               // if at the end of number
                    this.result.addNode(tmpVal%10);
                    this.result.addNode(1);
                } else {                                  // if more than 9 but not end of number
                    tmpNode1.next.digit += 1;
                    this.result.addNode(tmpVal%10);
                }
            } else {
                this.result.addNode(tmpVal);              // if added digits are < 9
            }
            tmpNode1 = tmpNode1.next;                     // move to the next
            tmpNode2 = tmpNode2.next;                     // move to the next

        } for (int i = 0; i < (max.getNumItems()-min.getNumItems()); i++){
            if (tmpNode1.digit > 9){                      // if needing to carry
                if (tmpNode1.next == null){               // if at the end of number
                    this.result.addNode(tmpNode1.digit%10);
                    this.result.addNode(1);
                } else {                                  // if more than 9 but not end of number
                    tmpNode1.next.digit += 1;
                    this.result.addNode(tmpNode1.digit%10);
                }
            } else {
                this.result.addNode(tmpNode1.digit);      // if added digits are < 9
            }
            tmpNode1 = tmpNode1.next;
        }
        return this.result;
    }

    public void printResult(){
        String n1 = removeZeros(this.ogNum1);
        String n2 = removeZeros(this.ogNum2);
        String operator = op;
        String res = removeZeros(this.result.reverseStr());
        System.out.println(n1 + " " + operator + " " + n2 + " = " + res);
    }

//    public String removeZeros(String str){
//        int ctr = 0;
//        String tmp = str;
//        char ch = str.charAt(ctr);
//
//        if(MyLinkedList.isZero(str)){
//            return "0";
//        } else {
//            while (ch == '0'){
//                ctr++;
//                ch = str.charAt(ctr);
//            }
//            return tmp.substring(ctr);
//        }
//    }
    public MyLinkedList getMaxBigNum(){
        if (this.num1.getNumItems() > this.num2.getNumItems()){
            return this.num1;
        } return this.num2;
    }
    public MyLinkedList getMinBigNum(){
        if (this.num1.getNumItems() < this.num2.getNumItems()){
            return this.num1;
        } return this.num2;
    }

}

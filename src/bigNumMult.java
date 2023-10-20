public class bigNumMult implements Expression {

    //values that can be changed for adding and carrying
    public MyLinkedList num1;
    public MyLinkedList num2;
    //used if the values get changed in the adding process
    public String ogNum1;
    public String ogNum2;

    public String op;
    public MyLinkedList result = new MyLinkedList(new Node(0));
    //public MyLinkedList result = new MyLinkedList();


    public int tmpRes;
    private MyLinkedList tmpResBig = new MyLinkedList(new Node(0));
    //private MyLinkedList tmpResBig = new MyLinkedList();

    public bigNumMult(MyLinkedList num1, MyLinkedList num2, String op){
        this.num1 = num1;
        this.num2 = num2;
        this.op = op;
        //copy original values
        this.ogNum1 = num1.reverseStr();
        this.ogNum2 = num2.reverseStr();
    }


    public MyLinkedList compute() {
        MyLinkedList max;
        MyLinkedList min;
        if (this.num1.getNumItems() == this.num2.getNumItems()){
            max = this.num1;
            min = this.num2;
        } else {
            max = getMaxBigNum();
            min = getMinBigNum();
        }
//        Node tmpNode1 = max.getHead();
//        Node tmpNode2 = min.getHead();

        int tmpVal, tmpRes;
        MyLinkedList tmpBigNumRes;
        Node tmpNode2 = min.getHead();

        for(int j=0; j < min.getNumItems(); j++){        //for the top number
            //this.tmpRes = 0;
            this.tmpResBig.resetListMult(j);
            Node tmpNode1 = max.getHead();

            for(int i=0; i < max.getNumItems(); i++){   // for the bottom number
                tmpVal = (tmpNode1.digit * tmpNode2.digit) + tmpNode1.carry;  //simple multiply value //t1c//
                if (tmpVal > 9){
                    if (tmpNode1.next != null){             // NOT end of the number
                        tmpNode1.next.carry = tmpVal/10;
                        this.tmpResBig.addNode((tmpVal % 10));  //t1c//
                    } else {  //end of number
                        this.tmpResBig.addNode(tmpVal % 10);
                        this.tmpResBig.addNode(tmpVal/10); // + (tmpCar /10));
                    }
                } else {
                    this.tmpResBig.addNode(tmpVal);
                }
                tmpNode1 = tmpNode1.next;
            }
            //System.out.println(this.tmpResBig);
            max.clearCarry();
            addTmpBig();
            tmpNode2 = tmpNode2.next;
        }
        return this.result;
    }

    public void addTmpBig(){
        bigNumAdd adderRes = new bigNumAdd(this.tmpResBig, this.result, "+");
        this.result = adderRes.compute();
    }

    public MyLinkedList getMaxBigNum() {
        if (this.num1.getNumItems() > this.num2.getNumItems()){
            return this.num1;
        } return this.num2;
    }

    public MyLinkedList getMinBigNum() {
        if (this.num1.getNumItems() < this.num2.getNumItems()){
            return this.num1;
        } return this.num2;
    }

    public void printResult() {
        String n1 = removeZeros(this.ogNum1);
        String n2 = removeZeros(this.ogNum2);
        String operator = op;
        String res = removeZeros(this.result.reverseStr());
        System.out.println(n1 + " " + operator + " " + n2 + " = " + res);
    }

//    @Override
//    public String removeZeros(String str) {
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
}

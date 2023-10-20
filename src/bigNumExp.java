public class bigNumExp implements Expression{

    //values that can be changed for adding and carrying
    public MyLinkedList num1;
    //used if the values get changed in the adding process
    public int exp;
    public String ogNum1;
    public String ogNum2;

    public String op;
    public MyLinkedList result = new MyLinkedList();


    private MyLinkedList tmpRes = new MyLinkedList();

    public bigNumExp(MyLinkedList num1, int exp, String op){
        this.num1 = num1;
        this.exp = exp;
        this.op = op;
        //copy original values
        this.ogNum1 = num1.reverseStr();
        this.ogNum2 = String.valueOf(exp);
    }

    public MyLinkedList compute(int n) {
        if(n == 0){
            return new MyLinkedList(new Node(1));
        }
        if (n==1){
            return this.num1;
        }
        this.tmpRes = compute(n/2);
        bigNumMult m1 = new bigNumMult(this.tmpRes, this.tmpRes, "*");
        this.result = m1.compute();
        if ((n % 2) == 1){
            bigNumMult m2 = new bigNumMult(this.result, this.num1, "*");
            this.result = m2.compute();
        }
        return this.result;
    }


    public void printResult() { // turn into default method
        String n1 = removeZeros(this.ogNum1);
        String n2 = removeZeros(this.ogNum2);
        String operator = op;
        String res = removeZeros(compute(this.exp).reverseStr());
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

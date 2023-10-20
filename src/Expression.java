public interface Expression {
    //public MyLinkedList compute();
    void printResult();
    default String removeZeros(String str){
        int ctr = 0;
        String tmp = str;
        char ch = str.charAt(ctr);

        if(MyLinkedList.isZero(str)){
            return "0";
        } else {
            while (ch == '0'){
                ctr++;
                ch = str.charAt(ctr);
            }
            return tmp.substring(ctr);
        }
    }
}

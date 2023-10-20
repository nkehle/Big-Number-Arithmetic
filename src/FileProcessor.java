import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileProcessor {

    /**
     * Processes arithmetic expressions line-by-line in the given file.
     *
     * @param filePath Path to a file containing arithmetic expressions.
     */
    public static void processFile(String filePath) {
        File infile = new File(filePath);
        try (Scanner scan = new Scanner(infile)) {
            while (scan.hasNext()) {
                String line = scan.nextLine();
                // TODO: Process each line of the input file, handling blank
                if (line.length() != 0) {
                    processLine(line);
                }
                // lines and spacing differences as appropriate
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + infile.getPath());  //coverage?
        }
    }

    public static void processLine(String line){  //change back to void
        line = line.replaceAll("\\s", "");
        String[] splt = line.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)+"); //regular expression for figits and operators
        String num1 = splt[0], num2 = splt[2]; //sets numbers equal to correct spots in array
        String op = splt[1]; //sets operator to char
        compute(num1,num2,op);

    }

    public static void compute(String num1, String num2, String op){  //change back to void
        MyLinkedList bigNum1 = new MyLinkedList();
        MyLinkedList bigNum2 = new MyLinkedList();
        bigNum1.makeBigNum(num1);
        bigNum2.makeBigNum(num2);
        switch(op) {
            case "+":
                //add
                bigNumAdd add = new bigNumAdd(bigNum1,bigNum2,op);
                add.compute();
                add.printResult();
                break;
            case "*":
                //multiply
                bigNumMult mult = new bigNumMult(bigNum1,bigNum2,op);
                mult.compute();
                mult.printResult();
                break;
            case "^":
                //exponent
                int exp1 = Integer.parseInt(num2);
                bigNumExp exp = new bigNumExp(bigNum1,exp1,op);
                exp.compute(exp1);
                exp.printResult();
                break;
        }
        //return null;
    }

}

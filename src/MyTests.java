import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MyTests {
    /**
     * The actual standard output stream.
     */
    private PrintStream old;

    /**
     * The streams we're using to capture printed output.
     */
    protected ByteArrayOutputStream baos;

    /**
     * Gets called before each test method. Need to do this so that we can
     * capture the printed output from each method.
     */
    @BeforeEach
    public void setUp() {
        this.old = System.out; // Save a reference to the original stdout stream.
        this.baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    public void testMultipleFiles() {
        boolean thrown = false;
        try {
            BigNumArithmetic.main(new String[] {"testTxt/addTest1.txt", "testTxt/addTest2.txt"});
        } catch (IllegalArgumentException e) {
            thrown = true;
        } assertTrue(thrown);
    }

    @Test
    public void noFile() {
        boolean thrown = false;
        BigNumArithmetic.main(new String[] {""});
    }

    @Test
    public void invalidOperator () {
        BigNumArithmetic.main(new String[] {"testTxt/invalidOperator.txt"});
        String output = this.baos.toString().trim();
    }

    /**
     * Addition Tests
     */
    @Test
    public void testAdd1() {
        BigNumArithmetic.main(new String[] {"testTxt/addTest1.txt"});
        String output = this.baos.toString().trim();
        assertEquals("1 + 5 = 6", output);
    }


    @Test
    public void testAdd2() {
        BigNumArithmetic.main(new String[] {"testTxt/addTest2.txt"});
        String output = this.baos.toString().trim();
        assertEquals("10 + 10 = 20", output);
    }

    @Test
    public void testAdd3() {
        BigNumArithmetic.main(new String[] {"testTxt/addTest3.txt"});
        String output = this.baos.toString().trim();
        assertEquals("29 + 2 = 31", output);
    }

    @Test //ask about this one
    public void testAdd4() {
        BigNumArithmetic.main(new String[] {"testTxt/addTest4.txt"});
        String output = this.baos.toString().trim();
        assertEquals("0 + 0 = 0", output);
    }

    @Test
    public void testAdd5() {
        BigNumArithmetic.main(new String[] {"testTxt/addTest5.txt"});
        String output = this.baos.toString().trim();
        assertEquals("999999 + 1 = 1000000", output);
    }

    @Test
    public void testAdd6() {
        BigNumArithmetic.main(new String[] {"testTxt/addTest6.txt"});
        String output = this.baos.toString().trim();
        assertEquals("1234567890 + 987654321 = 2222222211", output);
    }

    @Test //Fail
    public void testAdd7() {
        BigNumArithmetic.main(new String[] {"testTxt/addTest7.txt"});
        String output = this.baos.toString().trim();
        assertEquals("1 + 2 = 3", output);

    }

    @Test //Fail
    public void testAdd8() {
        BigNumArithmetic.main(new String[] {"testTxt/addTest8.txt"});
        String output = this.baos.toString().trim();
        assertEquals("1748300001 + 97320091122 = 99068391123", output);

    }

    @Test //Fail
    public void testAdd9() {
        BigNumArithmetic.main(new String[] {"testTxt/addTest9.txt"});
        String output = this.baos.toString().trim();
        assertEquals("999 + 999 = 1998", output);

    }

    /**
     * Multiplication Tests
     */
    @Test
    public void testMult1() {
        BigNumArithmetic.main(new String[] {"testTxt/multTest1.txt"});
        String output = this.baos.toString().trim();
        assertEquals("2 * 3 = 6", output);
    }

    @Test
    public void testMult2() { //problem still reamins in the zeros from the addition -- keeps storing it as a null value
        BigNumArithmetic.main(new String[] {"testTxt/multTest2.txt"});
        String output = this.baos.toString().trim();
        assertEquals("12 * 2 = 24", output);
    }

    @Test
    public void testMult3() {
        BigNumArithmetic.main(new String[] {"testTxt/multTest3.txt"});
        String output = this.baos.toString().trim();
        assertEquals("20 * 10 = 200", output);
    }

    @Test
    public void testMult4() {
        BigNumArithmetic.main(new String[] {"testTxt/multTest4.txt"});
        String output = this.baos.toString().trim();
        assertEquals("123 * 58 = 7134" , output);
    }

    @Test //Fail
    public void testMult5() {
        BigNumArithmetic.main(new String[] {"testTxt/multTest5.txt"});
        String output = this.baos.toString().trim();
        assertEquals("621 * 3479 = 2160459" , output);
    }

    @Test
    public void testMult6() {
        BigNumArithmetic.main(new String[] {"testTxt/multTest6.txt"});
        String output = this.baos.toString().trim();
        assertEquals("111111111 * 122333444455555 = 13592604925913506171605" , output);
    }

    @Test // FAIL 0's
    public void testMult7() {
        BigNumArithmetic.main(new String[] {"testTxt/multTest7.txt"});
        String output = this.baos.toString().trim();
        assertEquals("2123 * 0 = 0" , output);
    }

    @Test
    public void testMult8() {
        BigNumArithmetic.main(new String[] {"testTxt/multTest8.txt"});
        String output = this.baos.toString().trim();
        assertEquals("123456789 * 987654321 = 121932631112635269" , output);
    }

    @Test
    public void testMult10() {
        BigNumArithmetic.main(new String[] {"testTxt/multTest9.txt"});
        String output = this.baos.toString().trim();
        assertEquals("178 * 178 = 31684" , output);
    }

    @Test  // FAIL
    public void testMult11() {
        BigNumArithmetic.main(new String[] {"testTxt/multTest10.txt"});
        String output = this.baos.toString().trim();
        assertEquals("1 * 122333444455555666666777777788888888999999999 = " +
                "122333444455555666666777777788888888999999999" , output);
    }

    /**
     * Tests for exponentiation
     */

    //Mine:8
    //Answ:8
    @Test
    public void testExp1(){
        BigNumArithmetic.main(new String[] {"testTxt/expTest1.txt"});
        String output = this.baos.toString().trim();
        assertEquals("2 ^ 3 = 8" , output);

    }

    @Test
    public void testExp2(){
        BigNumArithmetic.main(new String[] {"testTxt/expTest2.txt"});
        String output = this.baos.toString().trim();
        assertEquals("7 ^ 13 = 96889010407" , output);
    }

    @Test
    public void testExp3(){
        BigNumArithmetic.main(new String[] {"testTxt/expTest3.txt"});
        String output = this.baos.toString().trim();
        assertEquals("10 ^ 50 = 100000000000000000000000000000000000000000000000000" , output);
    }

    @Test
    public void testExp4(){
        BigNumArithmetic.main(new String[] {"testTxt/expTest4.txt"});
        String output = this.baos.toString().trim();
        assertEquals("2 ^ 2 = 4" , output);
    }

    @Test
    public void testExp5(){
        BigNumArithmetic.main(new String[] {"testTxt/expTest5.txt"});
        String output = this.baos.toString().trim();
        assertEquals("123 ^ 0 = 1" , output);
    }
    /**
     * Misc Test
     */

//    @Test //Fail
//    public void misc2() {
//        BigNumArithmetic.main(new String[] {"testTxt/miscTest.txrt"});
//        String output = this.baos.toString().trim();
//        assertEquals("1 + 2 = 3" , output);
//    }

    @AfterEach
    public void tearDown() {
        System.out.flush();
        System.setOut(this.old);
    }
}

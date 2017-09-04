/********************************************************
 * Robert Wagner
 * CISC 3150 HW #1
 * 2017-09-04
 *
 * Question4.java:
 *   In which a file of numbers is read and output
 *
 ********************************************************/
import java.util.Scanner;

public class Question4 {

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in).useDelimiter(",|\\n|\\t");
        while (s.hasNextInt()) {
            int i = s.nextInt();
            System.out.println(i);
        }
    }
}

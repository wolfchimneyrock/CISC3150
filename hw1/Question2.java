/********************************************************
 * Robert Wagner
 * CISC 3150 HW #1
 * 2017-09-04
 *
 * Question2.java:
 *   In which circle's areas are found repeatedly
 *
 ********************************************************/

import java.util.Scanner;
import shapes.*;

public class Question2 {

    static boolean prompt(String p) {
        System.out.print(p);
        return true;
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        while (prompt("Enter Radius: ") &&  s.hasNextDouble()) {
            try {
                Circle c = Circle.fromCoords(0, 0, s.nextDouble());
                System.out.printf("Area: %.3f\n", c.area()); 
            } catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage());
            }
        }
        s.close();
        System.out.println();
    }
}

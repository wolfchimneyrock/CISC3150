/********************************************************
 * Robert Wagner
 * CISC 3150 HW #1
 * 2017-09-04
 *
 * Question5.java:
 *   In which a triangle is born
 *
 ********************************************************/

import java.util.*;
import shapes.*;

public class Question5 {

    static final String pointLabels[] = {"A", "B", "C"};

    public static void main(String args[]) {

        Scanner s = new Scanner(System.in);

        List<Point> points = new ArrayList<Point>();
        
        System.out.println("Input three points to form a triangle.");

        for (String label : pointLabels) {
            double x = 0;
            double y = 0;
            System.out.printf("Enter point %s [X Y]: ",label);
            try {
                x = s.nextDouble();
                y = s.nextDouble();
            } catch (NoSuchElementException exc) {
                System.out.println("Invalid input, aborting.");
                System.exit(1);
            }
            points.add(new Point(x, y));
        }

        try {
            Triangle t = Triangle.fromPointList(points);
            System.out.println("That is a Valid triangle.");
        } catch (IllegalArgumentException exc) {
            System.out.println("That is NOT a Valid triangle.");
        }
      
        s.close();
             
    }
}


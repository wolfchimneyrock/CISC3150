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
        
        for (String label : pointLabels) {
            System.out.printf("Enter point %s [X Y]: ",label);
            double x = s.nextDouble();
            double y = s.nextDouble();
            points.add(new Point(x, y));
        }

        try {
            Triangle t = Triangle.fromPointList(points);
            System.out.println("Valid triangle");
        } catch (IllegalArgumentException exc) {
            System.out.println("NOT Valid triangle");
        }
      
        s.close();
             
    }
}


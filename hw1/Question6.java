/********************************************************
 * Robert Wagner
 * CISC 3150 HW #1
 * 2017-09-04
 *
 * Question6.java:
 *   In which we test circles' intersection
 *
 ********************************************************/

import java.util.*;
import shapes.*;

public class Question6 {

    static final String circleLabels[] = {"A", "B"};

    public static void main(String args[]) {

        Scanner      s       = new Scanner(System.in);
        List<Circle> circles = new ArrayList<Circle>();
        
        for (String label : circleLabels) {
            System.out.printf("Enter circle %s [X Y R]: ",label);
            double x = s.nextDouble();
            double y = s.nextDouble();
            double r = s.nextDouble();
            try {
                circles.add(Circle.fromCoords(x, y, r));
            } catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage());
                System.exit(1);
            }
        }
        int count = circleLabels.length;
        for (int a = 0; a < count - 1; a++) {
            for (int b = a + 1; b < count; b++) {
                String message;
                Intersection i = circles.get(a).intersectsWith(circles.get(b));
                switch(i) {
                    case TANGENT:
                        message = "Circles %s and %s are tangent\n";
                        break;
                    case OVERLAP:
                        message = "Circles %s and %s overlap\n";
                        break;
                    case ENCLOSES:
                        message = "Circle %s encloses circle %s\n";
                        break;
                    case ENCLOSED:
                        message = "Circle %s is enclosed by circle %s\n";
                        break;
                    case COINCIDE:
                        message = "Circles %s and %s are co-incident\n";
                        break;
                    default:
                        message = "Circles %s and %s do not intersect\n";
                }
                System.out.printf(message, circleLabels[a], circleLabels[b]);
            }
        }
        s.close();
    }
}


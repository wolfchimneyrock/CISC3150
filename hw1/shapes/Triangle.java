/********************************************************
 * Robert Wagner
 * CISC 3150 HW #1
 * 2017-09-04
 *
 * shapes/Triangle.java:
 *   In which a triangle is born
 *
 ********************************************************/

package shapes;
import java.util.*;
import shapes.Point;

public class Triangle {
    Point a, b, c;
    
    private Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private static boolean isValid(Point a, Point b, Point c) {
        double[] lengths = new double[3];
        lengths[0] = a.distanceTo(b);
        lengths[1] = a.distanceTo(c);
        lengths[2] = b.distanceTo(c);
        Arrays.sort(lengths);
        return lengths[2] < lengths[0] + lengths[1];
    }

    public static Triangle fromPointList(List<Point> points) {
        if (Triangle.isValid(points.get(0), points.get(1), points.get(2))) {
            Triangle t = new Triangle(points.get(0), points.get(1), points.get(2));
            return t;
        } else
            throw new IllegalArgumentException("Triangle is not valid");
    }

    public static Triangle fromPoints(Point a, Point b, Point c) {
        if (Triangle.isValid(a, b, c)) {
            Triangle t = new Triangle(a, b, c);
            return t;
        } else
            throw new IllegalArgumentException("Triangle is not valid");
    }
}

import java.util.*;

class Point {
    double x;
    double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        return Math.sqrt(
               Math.pow(this.x - other.x, 2) +
               Math.pow(this.y - other.y, 2));
    }
}

enum Intersection {
    NONE,
    TANGENT,
    OVERLAP,
    ENCLOSED,
    ENCLOSES,
    COINCIDE
}

class Circle {
    Point center;
    double radius;

    private Circle(double x, double y, double radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }

    public static Circle fromPoint(double x, double y, double radius) {
        if (radius > 0) {
            Circle c = new Circle(x, y, radius);
            return c;
        } else
            throw new IllegalArgumentException("Radius must be greater than zero");
    }

    public Intersection intersectsWith(Circle other) {
        double distance = this.center.distanceTo(other.center);
        double radSum   = this.radius + other.radius;

        if (distance == 0 && this.radius == other.radius)
            return Intersection.COINCIDE;

        if (distance == radSum)
            return Intersection.TANGENT;

        if (distance < radSum) {
            if (this.radius >= distance + other.radius)
                return Intersection.ENCLOSES;
        
            if (other.radius >= distance + this.radius) 
                return Intersection.ENCLOSED;
        
            return Intersection.OVERLAP;
        }
        
        return Intersection.NONE;
    }

}

public class Question6 {

    static final String circleLabels[] = {"A", "B"};

    public static void main(String args[]) {

        Scanner s = new Scanner(System.in);

        List<Circle> circles = new ArrayList<Circle>();
        
        for (String label : circleLabels) {
            System.out.printf("Enter circle %s [X Y R]: ",label);
            double x = s.nextDouble();
            double y = s.nextDouble();
            double r = s.nextDouble();
            try {
                circles.add(Circle.fromPoint(x, y, r));
            } catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage());
                System.exit(1);
            }
        }
      
        Intersection i = circles.get(0).intersectsWith(circles.get(1));

        String message;

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

        System.out.printf(message, circleLabels[0], circleLabels[1]);
        s.close();
             
    }
}


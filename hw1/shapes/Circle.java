package shapes;
import shapes.Point;
import shapes.Intersection;

public class Circle {
    Point center;
    double radius;

    private Circle(double x, double y, double radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }

    public static Circle fromCoords(double x, double y, double radius) {
        if (radius > 0) {
            Circle c = new Circle(x, y, radius);
            return c;
        } else
            throw new IllegalArgumentException("Radius must be greater than zero");
    }

    public double area() {
        return Math.PI * Math.pow(this.radius, 2);
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

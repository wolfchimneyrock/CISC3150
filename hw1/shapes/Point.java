package shapes;

public class Point {
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

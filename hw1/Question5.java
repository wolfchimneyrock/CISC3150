import java.util.*;

class Point {
    double x;
    double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distanceTo(Point other) {
        return Math.sqrt(
               Math.pow(this.x - other.x, 2) +
               Math.pow(this.y - other.y, 2));
    }
}

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

        List<Double> lengths = new ArrayList<Double>();
      
        for (int a = 0; a < 2; a++) 
            for (int b = a + 1; b < 3; b++) 
                lengths.add(points.get(a).distanceTo(points.get(b)));

        Collections.sort(lengths);

        System.out.printf("Side lengths: %.2f %.2f %.2f\n", lengths.get(0), lengths.get(1), lengths.get(2));
        
        if (lengths.get(2) < lengths.get(0) + lengths.get(1)) 
            System.out.println("Valid triangle");
        else
            System.out.println("NOT Valid triangle");

        s.close();
             
    }
}


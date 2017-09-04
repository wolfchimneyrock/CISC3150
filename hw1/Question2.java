import java.util.Scanner;

public class Question2 {

    static boolean prompt(String p) {
        System.out.print(p);
        return true;
    }

    public static void main(String args[]) {
        Double radius;
        Double area;
        Scanner s = new Scanner(System.in);
        while (prompt("Enter Radius: ") &&  s.hasNextDouble()) {
            radius = s.nextDouble();
            area = Math.PI * Math.pow(radius, 2);
            System.out.printf("Area: %.3f\n", area); 
        }
        s.close();
        System.out.println();
    }
}

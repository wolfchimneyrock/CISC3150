/********************************************************
 * Robert Wagner
 * CISC 3150 HW #2
 * 2017-09-06
 *
 * Question1.java:
 *   In which a counting pyramid is born
 *
 ********************************************************/

import java.util.*;
class Question1 {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter height: ");
        int height = s.nextInt();
        s.close();

        if (height < 1) System.exit(1);

        int             spacing = 2;
        if (height > 9) spacing++;
        
        String format = String.format("%%%dd", spacing);
        
        String gapFormat = String.format("%%%dd\n", spacing * height);
        System.out.printf(gapFormat, 1);
        
        for (int i = 2; i <= height; i++) {
            
            gapFormat = String.format("%%%dd", spacing * (height - i + 1));
            System.out.printf(gapFormat, 1);

            for (int j = 2; j < i; j++) 
                System.out.printf(format, j);
            
            for (int j = i; j > 0; j--)
                System.out.printf(format, j);

            System.out.println();
        }
        
    }
}

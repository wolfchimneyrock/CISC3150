/********************************************************
 * Robert Wagner
 * CISC 3150 HW #2
 * 2017-09-06
 *
 * Question4.java:
 *   Glorified GOTO
 *
 ********************************************************/

import java.util.*;

class Question4 {
    public static void main(String arg[]) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter stopping point: ");
        String stopping = s.nextLine();

        looper: 
        for (int y = 0; y < 10; y++) {
            for (char x = 'A'; x <= 'Z'; x++) {
                String str = String.format("%c%d", x, y);
                System.out.printf("%s ", str);
                if (str.equals(stopping)) {
                    System.out.println("...");
                    break looper;
                }
            }
            System.out.println();   
        }
        
    }
}

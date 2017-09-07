/********************************************************
 * Robert Wagner
 * CISC 3150 HW #2
 * 2017-09-06
 *
 * Question6.java:
 *   Delimit per character
 *
 ********************************************************/

import java.util.*;

class Question6 {
    public static void main(String arg[]) {
        Scanner s = new Scanner(System.in).useDelimiter("");
        List<String> strs = new ArrayList<String>();
        System.out.print("Enter string, ^D to finish: ");
        while (s.hasNext()) {
            strs.add(s.next());
        }
        s.close();
        for (String str : strs) 
            System.out.println(str);
    }
}

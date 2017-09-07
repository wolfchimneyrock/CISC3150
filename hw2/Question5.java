/********************************************************
 * Robert Wagner
 * CISC 3150 HW #2
 * 2017-09-06
 *
 * Question5.java:
 *   Reading CSV
 *
 ********************************************************/

import java.util.*;

class Question5 {
    public static void main(String arg[]) {
        Scanner s = new Scanner(System.in).useDelimiter(", |,| |\\n|\\t");
        List<String> strs = new ArrayList<String>();
        while (s.hasNext()) {
            strs.add(s.next());
        }
        s.close();
        for (String str : strs) 
            System.out.println(str);
    }
}

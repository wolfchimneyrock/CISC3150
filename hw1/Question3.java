/********************************************************
 * Robert Wagner
 * CISC 3150 HW #1
 * 2017-09-04
 *
 * Question3.java:
 *   In which a month is named
 *
 ********************************************************/

import java.util.Random;

public class Question3 {

    static final String monthNames[] = {
        "January", "February", "March", 
        "April",   "May",      "June",
        "July",    "August",   "September", 
        "October", "November", "December"
    };

    public static void main(String args[]) {
        Random rnd = new Random();
        int month = rnd.nextInt(12);
        System.out.println(monthNames[month]);
    }
}


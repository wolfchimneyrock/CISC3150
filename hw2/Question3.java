/********************************************************
 * Robert Wagner
 * CISC 3150 HW #2
 * 2017-09-06
 *
 * Question3.java:
 *   a man, a plan, a canal: panama
 *
 ********************************************************/

import java.util.*;
class Question3 {
    public static boolean isPalindrome(String s) {
        int l = s.length();
        int n = l / 2;
        for (int i = 0; i < n; i++)
            if (s.charAt(i) != s.charAt(l - i - 1))
                return false;
        return true;
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter string: ");
        String str = s.nextLine();
        String stripped = str.toLowerCase().replaceAll("[^a-z0-9]+", "");
        s.close();
        if (stripped.length() > 0 && isPalindrome(stripped))
            System.out.printf("'%s' is a palindrome.\n", str);
        else
            System.out.printf("'%s' is NOT a palindrome.\n", str);
    }
}

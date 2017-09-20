/********************************************************
 * Robert Wagner
 * CISC 3150 HW #3
 * 2017-09-20
 *
 * MyStringTest.java:
 *   In which a string is tested
 *
 ********************************************************/

public class MyStringTest {
    public static void main(String[] args) {

        // test valueOf() method
        int[] test_values = { Integer.MIN_VALUE, -999, -1, 0, 10, 1000, 9999, Integer.MAX_VALUE };
        for (int x : test_values) {
            MyString test = MyString.valueOf(x);
            assert(test.toString().equals(String.valueOf(x)));
            System.out.printf("MySring.valueOf(%d) = '%s'\n", x, test.toString());
        }

        char[] zero_data = {'0'};
        MyString zero_str = new MyString(zero_data);
        System.out.printf("zero_str = '%s'\n", zero_str);
        MyString other_zero = new MyString(zero_data);
        System.out.printf("other_str = '%s'\n", other_zero);
        assert(zero_str.length() == 1);
        System.out.printf("zero_str.length() = '%d'\n", zero_str.length());
        assert(zero_str.equals(other_zero));
        System.out.printf("zero_str.equals(other_zero) = %b\n", zero_str.equals(other_zero));
        assert(!zero_str.equals(null));
        System.out.printf("zero_str.equals(null) = %b\n", zero_str.equals(null));
        assert(!zero_str.equals(0));
        System.out.printf("zero_str.equals(0) = %b\n", zero_str.equals(0));
        assert(!zero_str.equals("0"));
        System.out.printf("zero_str.equals('0') = %b\n", zero_str.equals("0"));
        assert(zero_str.toString().equals("0"));
        System.out.printf("zero_str.toString().equals('0') = %b\n", zero_str.toString().equals("0"));
        char[] z = zero_str.getMyString();
        System.out.println("Attempting to mutate zero_str...");
        z[0] = '1';
        assert(!z.equals(zero_str.getMyString()));
        System.out.printf("zero_str = '%s'\n", zero_str);
        if (z.toString().equals("0")) {
            System.out.println("mutate failed. passed!");
        }
        char[] empty_data = {};
        MyString empty_str = new MyString(empty_data);
        System.out.printf("empty_str = '%s'\n", empty_str);
        assert(empty_str.length() == 0);
        System.out.printf("empty_str.length() = '%d'\n", empty_str.length());
        assert(empty_str.toString().equals(""));
        System.out.printf("empty_str.toString().equals('0') = %b\n", empty_str.toString().equals("0"));
        char[] proper_data = {'P','r','o','p','e','r',' ','C','a','s','e'};
        MyString proper_str = new MyString(proper_data);
        System.out.printf("proper_str = '%s'\n", proper_str);

        assert(proper_str.length() == 11);
        System.out.printf("proper_str.length() = '%d'\n", proper_str.length());

        assert(!proper_str.equals(proper_str.toLowerCase()));
        assert(proper_str.toLowerCase().toString().equals("proper case"));
        System.out.printf("proper_str.toLowerCase() = '%s'\n", proper_str.toLowerCase());
        assert(!proper_str.equals(proper_str.toUpperCase()));
        assert(proper_str.toUpperCase().toString().equals("PROPER CASE"));
        System.out.printf("proper_str.toUpperCase() = '%s'\n", proper_str.toUpperCase());

        assert(proper_str.toLowerCase().equals(proper_str.toUpperCase().toLowerCase()));
        System.out.printf("proper_str.toLowerCase().equals(proper_str.toUpperCase().toLowerCase()) == %b\n", 
                proper_str.toLowerCase().equals(proper_str.toUpperCase().toLowerCase()));
        MyString case_str = proper_str.substring(7, 10);
        System.out.printf("case_str = proper_str.substring(7,10) == '%s'\n", case_str);
        assert(case_str.toString().equals("Case"));
        System.out.printf("case_str.toString().equals('Case') = %b\n", case_str.toString().equals("Case"));
        assert(case_str.toLowerCase().toString().equals("case"));
        System.out.printf("case_str.toLowerCase().toString().equals('Case') = %b\n", 
                case_str.toLowerCase().toString().equals("Case"));
        System.out.println("All tests pass.");
    }
}

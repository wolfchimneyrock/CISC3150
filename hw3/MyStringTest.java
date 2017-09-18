public class MyStringTest {
    public static void main(String[] args) {

        // test valueOf() method
        int[] test_values = { Integer.MIN_VALUE, -999, -1, 0, 10, 1000, 9999, Integer.MAX_VALUE };
        for (int x : test_values) {
            MyString test = MyString.valueOf(x);
            assert(test.toString().equals(String.valueOf(x)));
        }

        char[] zero_data = {'0'};
        MyString zero_str = new MyString(zero_data);
        MyString other_zero = new MyString(zero_data);
        assert(zero_str.length() == 1);
        assert(zero_str.equals(other_zero));
        assert(!zero_str.equals(null));
        assert(!zero_str.equals(0));
        assert(!zero_str.equals("0"));
        assert(zero_str.toString().equals("0"));
        char[] z = zero_str.getMyString();
        z[0] = '1';
        assert(!z.equals(zero_str.getMyString()));

        char[] empty_data = {};
        MyString empty_str = new MyString(empty_data);
        assert(empty_str.length() == 0);
        assert(empty_str.toString().equals(""));

        char[] proper_data = {'P','r','o','p','e','r',' ','C','a','s','e'};
        MyString proper_str = new MyString(proper_data);
        assert(!proper_str.equals(proper_str.toLowerCase()));
        assert(!proper_str.equals(proper_str.toUpperCase()));
        assert(proper_str.toLowerCase().equals(proper_str.toUpperCase().toLowerCase()));
        System.out.println(proper_str.substring(7, 10).toLowerCase());
        System.out.println(proper_str.toUpperCase());
        System.out.println(proper_str);
    }
}

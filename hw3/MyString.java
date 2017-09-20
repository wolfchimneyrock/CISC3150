/********************************************************
 * Robert Wagner
 * CISC 3150 HW #3
 * 2017-09-20
 *
 * MyString.java:
 *   In which a string is born
 *
 ********************************************************/

public class MyString {
    private char[] data;

    public MyString(char[] chars) {
        this.data = chars.clone();
    }

    public char charAt(int index) {
        if (index < 0 || index >= this.data.length) 
            throw new IndexOutOfBoundsException();
        return this.data[index];
    }

    public int length() {
        return this.data.length;
    }

    public MyString substring(int begin, int end) {
        if (begin < 0 || end < 0 || begin > end || begin >= this.data.length || end >= this.data.length)
            throw new IndexOutOfBoundsException();
        int length = end - begin + 1;
        char[] result = new char[length];
        System.arraycopy(this.data, begin, result, 0, length);
        return new MyString(result);
    }

    public MyString toLowerCase() {
        char[] result = this.data.clone();
        for (int i = 0; i < this.data.length; i++) {
            if (result[i] >= 'A' && result[i] <= 'Z')
                result[i] -= 'A' - 'a';
        }
        return new MyString(result);   
    }

    public MyString toUpperCase() {
        char[] result = this.data.clone();
        for (int i = 0; i < this.data.length; i++) {
            if (result[i] >= 'a' && result[i] <= 'z')
                result[i] -= 'a' - 'A';
        }
        return new MyString(result);   
    }

    public boolean equals(Object s) {
        if (s != null && s instanceof MyString) {
            MyString other = (MyString)s;
            if (this.data.length == other.data.length) {
                for (int i = 0; i < this.data.length; i++) {
                    if (this.charAt(i) != other.charAt(i))
                        return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public char[] getMyString() {
        return this.data.clone();
    }

    public String toString() {
        return new String(this.data);
    }

    public static MyString valueOf(int value) {
        if (value >= 0 && value < 10) {
            char[] data = {(char)('0' + value)};
            return new MyString(data);
        }
        int length; 
        if (value < 0)
            length = 1 + (int)Math.ceil(Math.log10(-1L * value));
        else
            length = (int)Math.ceil(Math.log10(value));
        char[] result = new char[length];
        result[0] = '-';
        while (length > 0 && value != 0) {
            result[--length] = (char)('0' + Math.abs(value % 10));
            value = value / 10;
        }
        return new MyString(result);
    }
}

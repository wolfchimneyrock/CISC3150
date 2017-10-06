/********************************************************
 * Robert Wagner
 * CISC 3150 HW #6
 * 2017-10-06
 *
 * Child.java:
 *   In which an inherited protected member is accessed
 *
 ********************************************************/

public class Child extends Parent {
    public Child(int y) {
        this.x = y;
    }

    public static void main(String[] args) {
        Child c = new Child(10);
        System.out.println(c.x);
    }
}


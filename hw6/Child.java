public class Child extends Parent {
    public Child(int y) {
        this.x = y;
    }

    public static void main(String[] args) {
        Child c = new Child(10);
        System.out.println(c.x);
    }
}


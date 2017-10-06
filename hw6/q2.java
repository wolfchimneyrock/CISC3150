public class q2 {
    public static void main(String[] args) {

        System.out.println("a.");
        Dog d = new Dog();
        System.out.println(d.call(3));


        System.out.println("b.");
        Object c = new Cat();
        System.out.println(c);

        System.out.println("c.");
        Object v = new Pet();
        //System.out.println(v.call());

        System.out.println("d.");
        Pet p = new Pet();
        System.out.println(p);

        System.out.println("e.");
        Pet q = new Dog();
        System.out.println(q.call());

        System.out.println("f.");
        Cat t = new Cat();
        System.out.println(t.toString("Come here, kitty"));

        System.out.println("g.");
        Cat u = new Cat();
        //System.out.println(u.call(3));

        System.out.println("h.");
        Dog e = new Dog();
        System.out.println(e.feet());

        System.out.println("i.");
        Pet r = new Dog();
        //System.out.println(r.call(3));

    }
}

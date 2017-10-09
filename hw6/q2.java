public class q2 {
    public static void main(String[] args) {

        System.out.println("a. Dog::call(int) is called (item E)");
        Dog d = new Dog();
        System.out.println(d.call(3));
        System.out.println();

        System.out.println("b. Cat::toString() is called (item G)");
        Object c = new Cat();
        System.out.println(c);
        System.out.println();

        System.out.println("c. Object::call() does not exist at compile time");
        Object v = new Pet();
        //System.out.println(v.call());
        System.out.println("*** Compile error: Object does not have call() method");
        System.out.println();

        System.out.println("d. Object::toString() is called since Pet does not override it");
        Pet p = new Pet();
        System.out.println(p);
        System.out.println();

        System.out.println("e. Dog::call() is called (Item C)");
        Pet q = new Dog();
        System.out.println(q.call());
        System.out.println();

        System.out.println("f. Cat::toString(String) is called (Item H)");
        Cat t = new Cat();
        System.out.println(t.toString("Come here, kitty"));
        System.out.println();

        System.out.println("g. Cat::call(int), Pet::call(int), Object::call(int) do not exist");
        Cat u = new Cat();
        //System.out.println(u.call(3));
        System.out.println("*** Compile error: Cat does not have a call(int) overloaded method");
        System.out.println();

        System.out.println("h. Pet::feet() is called since Dog does not override it");
        Dog e = new Dog();
        System.out.println(e.feet());
        System.out.println();

        System.out.println("i. Pet::call(int), Object::call(int) do not exist");
        Pet r = new Dog();
        //System.out.println(r.call(3));
        System.out.println("*** Compile error: Pet does not have a call(int) method");
        System.out.println();

    }
}

import java.io.*;

public class q2 implements Serializable {
    static int s = 10;
    int x;
    q2(int x) { this.x = x; }

    public static void main(String[] args) throws Exception {
        q2 q = new q2(50);

        System.out.printf("Original:\ns = %d\nx = %d\n", q.s, q.x);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("q2.obj"));

        oos.writeObject(q);
        oos.close();

        q.x = 30;
        q.s = 20;
        System.out.printf("Modified:\ns = %d\nx = %d\n", q.s, q.x);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("q2.obj"));

        q2 r = (q2)ois.readObject();

        System.out.printf("Reloaded:\ns = %d\nx = %d\n", r.s, r.x);

        ois.close();
    }
}

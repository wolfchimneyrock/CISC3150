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


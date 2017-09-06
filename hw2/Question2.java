/********************************************************
 * Robert Wagner
 * CISC 3150 HW #2
 * 2017-09-06
 *
 * Question2.java:
 *   In which a calendar is printed
 *
 ********************************************************/

import java.util.*;
class CalendarMonth {
    static final String DAYS = " Su Mo Tu We Th Fr Sa";
    static final String MONTHS[] = {"January",   "February", "March",    "April",
                                    "May",       "June",     "July",     "August",
                                    "September", "October",  "November", "December"};
    static final int SPACING = 3;
    static final String FORMAT = "%3d";
    private String lines[] = {"", "", "", "", "", "", "", "", ""};

    public String lineOf(int x) {
        return this.lines[x];
    }

    private CalendarMonth(int year, int month) {

        Calendar c = Calendar.getInstance();
        c.set(year, month, 1);
        int titleLen = MONTHS[month].length();
        int titleGap = (17 - titleLen) / 2;
        String titleFormat = String.format("%%%ds %%-%dd", titleLen + titleGap, 4 + titleGap);
        this.lines[0] = String.format(titleFormat, MONTHS[month], year);
        this.lines[1] = DAYS;
        int skip = c.get(Calendar.DAY_OF_WEEK);

        int currentLine = 2;
        int currentDay  = 1;
        String gapFormat = String.format("%%%dd", skip * SPACING);
        this.lines[currentLine] = String.format(gapFormat, currentDay++);
        while (true) {
            if (currentDay > 28) {
                c.set(year, month, currentDay);
                if (currentDay != c.get(Calendar.DAY_OF_MONTH)) {
                    int lastGap = SPACING * ((44 - currentDay - skip) % 7);
                    if (lastGap > 0) {
                        String lastFormat = String.format("%%%ds", lastGap);
                        this.lines[currentLine] += String.format(lastFormat, "");
                    }
                    break;
                }
            }
            if ((skip + currentDay) % 7 == 2) currentLine++;
            this.lines[currentLine] += String.format(FORMAT, currentDay++);
        }
        for (int l = currentLine + 1; l < 8; l++)
            this.lines[l] = String.format("%21s", " ");
    }

    public static List<CalendarMonth> ofYear(int year) {
        List<CalendarMonth> months = new ArrayList<CalendarMonth>();;
        for (int m = 0; m < 12; m++) 
            months.add(new CalendarMonth(year, m));
        return months;
    }
}

class Question2 {
    static final int COLS = 3;
    static final int ROWS = 4;

    public static void main(String args[]) {
        int year;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter year: ");
        year = s.nextInt();
        s.close();

        List<CalendarMonth> months = CalendarMonth.ofYear(year);
        for (int y = 0; y < ROWS; y++) {
            for (int line = 0; line < 9; line++) {
                for (int x = 0; x < COLS; x++)
                    System.out.print(months.get(COLS*y + x).lineOf(line) + "   ");
                System.out.println();
            }
        }
         
    }
}

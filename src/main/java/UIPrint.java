package main.java;

public class UIPrint {

    public static char star = '*';
    public static char tick = '\u2713';
    public static char cross = '\u2718';

    public static void drawLine(char ch, int length) {
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }
}

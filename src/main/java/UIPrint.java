package main.java;

public class UIPrint {

    public static char star = '*';
    public static String tick = "[\u2713]";
    public static String cross = "[\u2718]";
    public static String todoIcon = "[T]";
    public static String deadlineIcon = "[D]";
    public static String eventIcon = "[E]";

    public static void drawLine(char ch, int length) {
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }
}

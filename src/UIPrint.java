public class UIPrint {

    public static char star = '*';
    public static String tick = "[✓]";
    public static String cross = "[✗]";
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

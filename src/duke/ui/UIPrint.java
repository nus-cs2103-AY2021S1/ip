package duke.ui;

public class UIPrint {

    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static char star = '*';
    public static String tick = "[Done]";
    public static String cross = "[Not Done]";
    public static String todoIcon = "[T]";
    public static String deadlineIcon = "[D]";
    public static String eventIcon = "[E]";

    public static void drawLine(char ch, int length) {
        System.out.println(getLine(ch, length));
    }

    public static String getLine(char ch, int length) {
        String line = "\n";
        for (int i = 0; i < length; i++) {
            line += ch;
        }
        line += "\n";

        return line;
    }
}

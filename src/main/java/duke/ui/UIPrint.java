package duke.ui;

/**
 * UIPrint stores strings such as logo, icons, symbols, and provides
 * methods to form Strings or print strings.
 */
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

    /**
     * Draws a line formed by a char with customized length.
     * @param ch the char to form the line
     * @param length the length of the line
     */
    public static void drawLine(char ch, int length) {
        System.out.println(getLine(ch, length));
    }

    /**
     * Forms a line formed by a char with customized length.
     * @param ch the char to form the line
     * @param length the length of the line
     * @return the formed line
     */
    public static String getLine(char ch, int length) {
        String line = "\n";
        for (int i = 0; i < length; i++) {
            line += ch;
        }
        line += "\n";

        return line;
    }
}

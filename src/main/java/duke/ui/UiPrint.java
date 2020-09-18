package duke.ui;

/**
 * UiPrint stores strings such as LOGO, icons, symbols, and provides
 * methods to form Strings or print strings.
 */
public class UiPrint {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final char STAR = '*';
    public static final String TICK = "[Done]";
    public static final String CROSS = "[Not Done]";
    public static final String TODO_ICON = "[T]";
    public static final String DEADLINE_ICON = "[D]";
    public static final String EVENT_ICON = "[E]";

    /**
     * Draws a line formed by a char with customized length.
     * @param ch the char to form the line
     * @param length the length of the line
     */
    public static void drawLine(char ch, int length) {
        assert length >= 0 : "the length of a line cannot be negative";

        System.out.println(getLine(ch, length));
    }

    /**
     * Forms a line formed by a char with customized length.
     * @param ch the char to form the line
     * @param length the length of the line
     * @return the formed line
     */
    public static String getLine(char ch, int length) {
        assert length >= 0 : "the length of a line cannot be negative";

        String line = "\n";
        for (int i = 0; i < length; i++) {
            line += ch;
        }
        line += "\n";

        return line;
    }
}

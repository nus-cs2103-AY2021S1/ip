package duke;

public class FormatPrinter {
    /**
     * print() formats any String to match the format of Duke's responses and
     * prints them.
     * @param str any string to be formatted and printed
     */
    public static void print(String str) {
        System.out.println(Formatter.formatResponse(str));
    }
}

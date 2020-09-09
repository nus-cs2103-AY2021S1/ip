package duke.tool;

/**
 * Represents an interpreter for user input and formats the input such that
 * the chat bot is able to understand and handle it.
 */
public class Parser {

    /**
     * Formats the user input given such that the Duke chat bot is able to
     * handle it.
     *
     * @param str The user input.
     * @return The user input formatted for Duke chat bot handling.
     */
    public static String[] parse(String str) {
        assert str != null;
        return str.split(" ", 2);
    }

    public static String[] parseDescription(String str) {
        return str.split( " /every ");
    }

    public static String[] parseRecurrence(String str) { return str.split(" @ "); }

}

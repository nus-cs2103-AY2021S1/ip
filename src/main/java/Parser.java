/**
 * Represents an interpreter for user input and formats the input such that
 * the chat bot is able to understand and handle it.
 */
public class Parser {

    /**
     * Formats the user input given such that the Duke chat bot is able to
     * handle it.
     * @param str the user input
     * @return the user input formatted for Duke chat bot handling
     */
    public static String[] parse(String str) {
        return str.split(" ", 2);
    }

}

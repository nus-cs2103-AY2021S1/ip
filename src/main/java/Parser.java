/**
 * A class that checks whether the user's inputs are valid.
 */
public class Parser {

    /**
     * Checks the validity of the user's input.
     * @param command user's input.
     * @return the command if the input is valid
     */
    public static String parse(String command) {
        String trimmed = command.trim();
        String first = trimmed.split(" ")[0].trim();

        if (first.equals("todo") || first.equals("deadline") || first.equals("event") || first.equals("list")
                || first.equals("done") || first.equals("delete") || first.equals("find") || first.equals("bye")) {
            return command;

        } else {
            return " ";
        }
    }
}

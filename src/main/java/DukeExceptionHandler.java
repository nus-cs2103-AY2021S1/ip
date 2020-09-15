/**
 * Handles exceptions in Duke.
 */
public class DukeExceptionHandler {

    /**
     * Handles exceptions in Duke.
     * @param text user input.
     * @return error message.
     */
    public static String handleException(String text) {

        if (text.equals("todo") || text.equals("deadline") || text.equals("event")
                || text.equals("done") || text.equals("delete")|| text.equals("find")) {
            NoDescriptionException error = new NoDescriptionException(text);
            return error.toString();

        } else if (!text.contains("todo") && !text.contains("deadline") && !text.contains("event")
                && !text.contains("done") && !text.contains("delete") && !text.contains("find")
                && !text.equals("list") && !text.equals("bye") && !text.equals("priority")
                && !text.contains("priority")) {
            InvalidInputException error = new InvalidInputException(text);
            return error.toString();
        }

        return null;
    }
}

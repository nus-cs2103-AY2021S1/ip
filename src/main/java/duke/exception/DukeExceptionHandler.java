package duke.exception;

/**
 * DukeExceptionHandler outputs exception messages
 */
public class DukeExceptionHandler {

    /**
     * Static method that handles exception by releasing error message
     *
     * @param input
     * @return String of error message
     */
    public static String handleException(String input) {

        if (isEmptyDescription(input)) {
            //tasks created with no description
            InvalidDescriptionException error = new InvalidDescriptionException(input);
            return error.toString();

        } else if (isWrongInput(input)) {
            WrongInputException error = new WrongInputException(input);
            return error.toString();
        }

        return null;
    }

    public static boolean isEmptyDescription(String input) {
        if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
            return true;
        }
        else return false;
    }

    public static boolean isWrongInput(String input) {
        if (!input.contains("todo") && !input.contains("deadline") && !input.contains("event")
                && !input.contains("done") && !input.equals("list") && !input.contains("find") && !input.contains("bye")
                    && !input.contains("delete")) {
            return true;
        }
        else return false;
    }
}
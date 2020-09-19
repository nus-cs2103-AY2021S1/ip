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
            IncorrectInputException error = new IncorrectInputException(input);
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
        boolean CONTAINS_TODO = input.contains("todo");
        boolean CONTAINS_DEADLINE = input.contains("deadline");
        boolean CONTAINS_EVENT = input.contains("event");
        boolean CONTAINS_DONE = input.contains("done");
        boolean CONTAINS_LIST = input.contains("list");
        boolean CONTAINS_FIND = input.contains("find");
        boolean CONTAINS_BYE = input.contains("bye");
        boolean CONTAINS_DELETE = input.contains("delete");

        if (!CONTAINS_TODO && !CONTAINS_DEADLINE && !CONTAINS_EVENT && !CONTAINS_DONE && !CONTAINS_LIST &&
                !CONTAINS_FIND && !CONTAINS_BYE && !CONTAINS_DELETE) {
            return true;
        } else {
            return false;
        }
    }
}
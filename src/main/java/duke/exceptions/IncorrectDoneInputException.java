package duke.exceptions;

/**
 * Class to initiate IncorrectDoneInputException.
 * Thrown when the input to done command is not in range or not a number.
 */
public class IncorrectDoneInputException extends DukeException {
    public IncorrectDoneInputException(int listSize) {
        super(getErrorMessage(listSize));
    }

    /**
     * Gets the appropriate error message for IncorrectDoneInputException.
     *
     * @param listSize Size of the task list.
     * @return String describing error message.
     */
    private static String getErrorMessage(int listSize) {
        if (listSize == 0) {
            return "There are no tasks in your list! Put some tasks in your list before using this command.";
        } else {
            return "Input for done command is invalid! Input a number between 1 and " + listSize + ".";
        }
    }
}

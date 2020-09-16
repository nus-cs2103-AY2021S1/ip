package duke.exceptions;

/**
 * Class to initiate IncorrectDeleteInputException.
 * Thrown when the input to delete command is not in range or not a number.
 */
public class IncorrectDeleteInputException extends DukeException {
    public IncorrectDeleteInputException(int listSize) {
        super(getErrorMessage(listSize));
    }

    /**
     * Gets the appropriate error message for IncorrectDeleteInputException.
     *
     * @param listSize Size of the task list.
     * @return String describing error message.
     */
    private static String getErrorMessage(int listSize) {
        if (listSize == 0) {
            return "There are no tasks in your list! Put some tasks in your list before using this command.";
        } else {
            return "Input for delete command is invalid! Input a number between 1 and " + listSize + ".";
        }
    }
}

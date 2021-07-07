package duke.task;

import java.util.regex.Pattern;

import duke.InvalidSaveException;

/**
 * Encapsulates Tasks that are to be completed.
 * Object only carries information about the task to be completed.
 */
public class ToDo extends Task {

    /** Symbol representing the type of Task this is */
    public static final String SYMBOL = "T";
    /** Number of separate fields in a todo save summary */
    private static final int NUM_FIELDS_SUMMARY = 3;

    /**
     * Creates a ToDo object with the given task description.
     *
     * @param taskDescription description of the task to be done.
     */
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of Task this object is.
     *
     * @return string representing the ToDo task with a "[T]" identifier.
     */
    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }

    /**
     * Returns a ToDo object corresponding to the summary given.
     *
     * @param summary string summary of the ToDo object to be reconstructed.
     * @return ToDo object representing the summary given.
     * @throws InvalidSaveException if the summary in the save file is invalid.
     */
    public static ToDo reconstructFromSummary(String summary) throws InvalidSaveException {
        String[] details = summary.split(Pattern.quote(SYMBOL_SEPARATOR));
        if (details.length != NUM_FIELDS_SUMMARY) {
            throw new InvalidSaveException("Wrong number of details!");
        } else if (!isValidSaveSymbol(details[1])) {
            throw new InvalidSaveException("Invalid completion status! Ensure that it is either 0 or 1");
        }

        boolean isDone = details[1].equals(SYMBOL_DONE);
        ToDo newToDo = new ToDo(details[2]);
        if (isDone) {
            newToDo.markDone();
        }
        return newToDo;
    }

    /**
     * Returns a 'T' representing a ToDo type.
     *
     * @return string "T".
     */
    @Override
    public String getSymbol() {
        return SYMBOL;
    }
}

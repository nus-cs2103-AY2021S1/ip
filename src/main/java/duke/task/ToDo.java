package duke.task;

/**
 * Encapsulates a todo
 */
public class ToDo extends Task {

    private final int NUM_OF_DATA_STRINGS = 3;
    private final String TASK_TYPE_STRING = "todo";
    private final String PRINTING_STRING = "[T][%s] %s";

    /**
     * Constructor
     *
     * @param description Description of the todo
     * @param isComplete Completion status of the todo
     */
    public ToDo(String description, boolean isComplete) {
        super(description, isComplete, null);
    }

    /**
     * Gets the string representation of the todo to be written into the file upon exit
     *
     * @return String representation of the todo in the file
     */
    @Override
    public String[] getDataStrings() {
        String[] dataStrings = new String[NUM_OF_DATA_STRINGS];
        dataStrings[0] = TASK_TYPE_STRING;
        dataStrings[1] = String.valueOf(isComplete);
        dataStrings[2] = description;
        return dataStrings;
    }

    /**
     * Gets the string representation of the todo to be printed in the UI.
     *
     * @return String representation of the todo displayed on the UI
     */
    @Override
    public String toString() {
        return String.format(PRINTING_STRING, getStatusIcon(), description);
    }
}

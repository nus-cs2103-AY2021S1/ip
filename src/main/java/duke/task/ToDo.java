package duke.task;

/**
 * Represents a to-do to be completed.
 */
public class ToDo extends Task {

    /**
     * Creates a new To-do with the specified description.
     *
     * @param description the description of the to-do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Loads previous to-do details and creates a new instance of the to-do.
     *
     * @param taskDetails the details of the to-do in 'saved' format.
     * @return a new instance of the to-do.
     */
    public static ToDo load(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|", 3);
        for (int i = 0; i < splitTaskDetails.length; i++) {
            splitTaskDetails[i] = splitTaskDetails[i].strip();
        }
        ToDo todo = new ToDo(splitTaskDetails[2].strip());
        if (splitTaskDetails[1].equals("true")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Returns the description and status of a to-do in a particular string format.
     * This format is used for saving to-dos, i.e. 'saved' format.
     *
     * @return a string representation of the to-do in a 'saved' format.
     */
    @Override
    public String saveAs() {
        return "T | " + super.saveAs();
    }

    /**
     * Returns a string representation of the to-do.
     *
     * @return a string representation of the to-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
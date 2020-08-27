/**
 * Represents a ToDo task, which is a type of task without any date or time attached to it
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object.
     * 
     * @param description the description of the ToDo task
     * @throws PandaBotEmptyTaskDescriptionException If the description given is empty
     */
    public ToDo(String description) throws PandaBotEmptyTaskDescriptionException {
        super(description);
    }

    /**
     * Returns a String representation of the task that is displayed to the user
     *
     * @return a String Representation of the task that is displayed to the user
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String representation of the task for saving to the save file 
     *
     * @return a String representation of the task for saving to the save file
     */
    @Override
    public String saveAsText() {
        return "T | " + super.saveAsText();
    }
}

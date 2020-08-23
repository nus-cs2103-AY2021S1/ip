/**
 * Encapsulates Tasks that are to be completed.
 * Object only carries information about the task to be completed.
 */
public class ToDos extends Task {

    /**
     * Creates a ToDos object with the given task description.
     * @param taskDescription description of the task to be done
     */
    public ToDos(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of Task this object is.
     * @return string representing the ToDos task with a "[T]" identifier
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

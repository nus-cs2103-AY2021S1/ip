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
     * @param toDoDetails the details of the to-do in 'saved' format.
     * @return a new instance of the to-do.
     */

    public static ToDo load(String toDoDetails) {
        // Split into type of task, status and description
        String[] splitToDoDetails = toDoDetails.split("\\|", 3);

        String status = splitToDoDetails[1];
        String description = splitToDoDetails[2];
        ToDo todo = new ToDo(description);

        if (status.equals("true")) {
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
     * Checks if this ToDo is equal to the specified object.
     *
     * @param obj the object to check.
     * @return true if this is equal to the specified object or false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo toDo = (ToDo) obj;

            return this.description.equals(toDo.description);
        } else {
            return false;
        }
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

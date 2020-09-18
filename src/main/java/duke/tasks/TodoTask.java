package duke.tasks;

import duke.util.DukeException;

/**
 * TodoTask object with description.
 */
public class TodoTask extends Task {

    /**
     * Constructor.
     * @param description string description of event.
     * @throws DukeException
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Overloaded Constructor to create existing tasks in storage.
     * @param description string description of event.
     * @param isCompleted boolean check if task has been completed.
     * @throws DukeException
     */
    public TodoTask(String description, String priorityLevel, boolean isCompleted) {
        super(description);
        this.priorityLevel = priorityLevel;
        this.isCompleted = isCompleted;
    }

    /**
     * Displays the string output representation in storage.
     * @return string representation.
     */
    @Override
    public String fileString() {return "T | " + super.fileString(); }

    /**
     * Displays the string output representation.
     * @return string representation.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

package mattbot.tasks;

/**
 * Represents a Deadline task that is created by the user.
 * Stores the set if information of the task entered by the user.
 */
public class Deadline extends Task {

    /**
     * Creates an Event object with the given values.
     *
     * @param name name of Deadline.
     * @param type type of the task.
     */
    public Deadline(String name, String type) {
        super(name, type);
    }
}

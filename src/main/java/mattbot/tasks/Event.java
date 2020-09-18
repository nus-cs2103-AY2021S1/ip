package mattbot.tasks;


/**
 * Represents an Event task that is created by the user.
 * Stores the set if information of the task entered by the user.
 */
public class Event extends Task {

    /**
     * Creates an Event object with the given values.
     *
     * @param name name of Event.
     * @param type type of the task.
     */
    public Event(String name, String type) {
        super(name, type);
    }
}

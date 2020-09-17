package mattbot.tasks;

/**
 * Represents a Todo task that is created by the user.
 * Stores the set if information of the task entered by the user.
 */
public class Todo extends task {

    /**
     * Creates an Event object with the given values.
     *
     * @param name name of Todo.
     * @param type type of the task.
     */
    public Todo(String name, String type) {
        super(name, type);
    }

}

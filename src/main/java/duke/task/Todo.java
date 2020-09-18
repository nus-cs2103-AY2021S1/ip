package duke.task;

/**
 * Represents a Todo Task. A <code>Todo</code> object contains a description and
 * keeps track of whether it has been completed.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     */
    public Todo(String description) {
        super(description);
        super.type = Task.Type.TODO;
    }

    /**
     * Constructor for Todo with additional isDone parameter.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        super.isDone = isDone;
        super.type = Task.Type.TODO;
    }

    public String getTypeIcon() {
        String icon = "[T]";
        return icon;
    }
}

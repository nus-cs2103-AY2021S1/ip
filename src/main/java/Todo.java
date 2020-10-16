/**
 * Todo class represents a Task that is a Todo.
 * Extends from the Task class.
 */
public class Todo extends Task {
    /**
     * Constructor that creates a Todo object.
     * @param name name of the Todo.
     */
    public Todo(String name) {
        super(name);
        taskType = "T";
    }

    /**
     * Overloaded constructor that creates a Todo object with a specified
     * completion status.
     * @param name name of the Todo.
     * @param isDone completion status of the Todo.
     */
    public Todo(String name, Boolean isDone) {
        super(name, isDone);
        taskType = "T";
    }
    
    @Override
    public String toString() {
        return String.format("[%s]%s", taskType, super.toString());
    }
}

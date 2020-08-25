/**
 * Represents a task without any time attached to it.
 */
public class Todo extends Task {

    /**
     * Creates To-do object. 
     * @param description To-do description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates To-do object.
     * @param done Done state of task.
     * @param description To-do description.
     */
    public Todo(String done, String description) {
        super(description);
        this.isDone = done.equals("1");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    @Override
    public String saveString() {
        return "T" + super.saveString();
    }
}
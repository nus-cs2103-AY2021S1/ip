package godfather.task;

/**
 * Contains a description of a future task to do
 */
public class ToDo extends Task {
    public ToDo(String description, int newTaskID) {
        super(description, newTaskID);
    }
    @Override
    public boolean isComplete() {
        return super.isComplete();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

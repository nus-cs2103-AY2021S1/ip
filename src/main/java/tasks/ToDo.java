package tasks;

/**
 * Represents the todos of the user that he input
 * into logic.Duke
 */
public class ToDo extends Task {
    public ToDo(String taskDesc) {
        super(taskDesc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

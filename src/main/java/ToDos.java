/**
 * Represents the todos of the user that he input
 * into Duke
 */
public class ToDos extends Task {
    public ToDos( String taskDesc) {
        super(taskDesc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

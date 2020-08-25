import java.util.StringTokenizer;

public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     *
     * @param description ToDo task description
     **/
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TODO.getInitial() + "]" + super.toString();
    }
}

package dobby.task;

/**
 * Class for todo tasks
 */
public class Todo extends Task {
    private static final String TAG = "[T]";

    public Todo(String description) {
        super(description, TAG);
    }

    @Override
    public String getDescription() {
        return TAG + super.getDescription();
    }
}

package dobby.task;

public class Todo extends Task {
    private final String TAG = "[T]";

    public Todo (String description) {
        super(description, "[T]");
    }

    @Override
    public String getDescription () {
        return TAG + super.getDescription();
    }
}

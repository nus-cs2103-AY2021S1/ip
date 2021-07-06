package duke.task;

public class TodoTask extends Task {
    static final String TASK_TYPE = "T";

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts task to string - task type, tick/check and description
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", TASK_TYPE, getStatusIcon(), description);
    }

    /**
     * Converts task to string representation in database- task type, 1 for done/0 for not done, and description
     */
    @Override
    public String toDBString() {
        return TASK_TYPE + "~" + super.toDBString();
    }
}

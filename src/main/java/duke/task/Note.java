package duke.task;

public class Note extends Task {
    static final String TASK_TYPE = "N";
    private String name;

    public Note(String description, String name) {
        super(description);
        this.name = name;
    }

    public Note(String description, boolean isDone) {
        super(description, false);
    }

    /**
     * Converts task to string - task type, tick/check and description
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", TASK_TYPE, name, description);
    }

    /**
     * Converts task to string representation in database- task type, 1 for done/0 for not done, and description
     */
    @Override
    public String toDBString() {
            return TASK_TYPE + "~" + super.toDBString() + "~" + name;
    }
}

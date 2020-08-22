public class Task {
    public static String SAVE_DELIMITER = "|";
    public static String ESCAPED_SAVE_DELIMITER = "\\|";

    protected String name;
    protected boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public Task(String name, boolean completed) {
        this.name = name;
        this.isCompleted = completed;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public String getStatusIcon() {
        return this.isCompleted ? "\u2713" : "\u2718";
    }

    public String format() {
        return this.name + SAVE_DELIMITER + this.isCompleted;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }
}

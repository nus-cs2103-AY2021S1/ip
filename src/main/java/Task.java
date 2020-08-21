public class Task {
    public static String SAVE_DELIMITER = "|";
    public static String ESCAPED_SAVE_DELIMITER = "\\|";

    protected String name;
    protected boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public void complete() {
        this.completed = true;
    }

    public String getStatusIcon() {
        return this.completed ? "\u2713" : "\u2718";
    }

    public String format() {
        return this.name + SAVE_DELIMITER + this.completed;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }
}

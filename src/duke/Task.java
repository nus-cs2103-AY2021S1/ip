package duke;

public class Task {
    public String name;
    public boolean isDone;
    public String time;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    Task(String name, String time) {
        this.name = name;
        this.time = time;
    }

    /**
     * Returns a string of icon.
     * @return String
     */
    String getIcon() {
        return isDone ? "[" + "\u2713" + "] " : "[" + "\u2718" + "] ";
    }

    /**
     * Updates the task as done.
     */
    void taskIsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string of indicator.
     * @return String
     */
    String getIndicator() {
        return "[T]";
    }

}

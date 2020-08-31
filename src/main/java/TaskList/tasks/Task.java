package main.java.TaskList.tasks;

public class Task {
    private String task;
    private String type = "Task";
    private boolean done = false;
    private String fullText;

    public Task (String task, String fullText) {
        this.task = task;
        this.fullText = fullText + "cone"; // the last 4 letters say done if a task is done, and cone if its not
    }

    protected String getType() {
        return type;
    }

    /**
     * Obtains fullText.
     * @return Raw text input combined with a status flag (completed or not) at the end.
     */
    public String getFullText() {
        return fullText;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        done = true;
        fullText = fullText.substring(0, fullText.length() - 4) + "done";
    }

    /**
     * String representation of the task, based on type and done-ness.
     * @return
     */
    public String toString() {
        return done ? "[✓] " + task : "[✗] " + task;
    }
}

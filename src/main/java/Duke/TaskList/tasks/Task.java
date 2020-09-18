package Duke.TaskList.tasks;

public abstract class Task {
    protected String task;
    private boolean done = false;
    protected String fullText;
    protected int commandIndex;

    public Task (String task, String fullText, int commandIndex) {
        this.task = task;
        this.commandIndex = commandIndex;
        this.fullText = fullText + "cone"; // the last 4 letters say done if a task is done, and cone if its not
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
        return done ? "[Barked] " + task : "[Not barked yet] " + task;
    }

    public abstract void update(String newTask);
}

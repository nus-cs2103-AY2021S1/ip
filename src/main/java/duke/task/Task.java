package duke.task;

/**
 * Represents a task. A <code>Task</code> object contains a content and a boolean which indicates whether the task has
 * been completed or not.
 */
public class Task {
    protected String content;
    protected boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public String toDataFileFormat() {
        return String.format("? | %d | %s", isDone ? 1 : 0, this.content);
    }

    public void markTaskAsDone(){
        isDone = true;
    };

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getStatusIcon());
        stringBuilder.append(this.content);
        return stringBuilder.toString();
    }
}

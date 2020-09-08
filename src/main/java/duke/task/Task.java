package duke.task;

/**
 * Represents a task. A <code>Task</code> object contains a content and a boolean which indicates whether the task has
 * been completed or not.
 */
public class Task {
    protected String content;
    protected boolean isDone;
    protected String tagName;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
        this.tagName = "";
    }

    public String toDataFileFormat() {
        return String.format("? | %d | %s | %s", isDone ? 1 : 0, tagName, content);
    }

    public void markTaskAsDone() {
        isDone = true;
    }

    public void setTag(String tagName) {
        this.tagName = tagName;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getContent() {
        return this.content;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getStatusIcon());
        if (!tagName.equals("")) {
            String tagDescription = "[" + this.tagName + "] ";
            stringBuilder.append(tagDescription);
        }
        stringBuilder.append(this.content);
        return stringBuilder.toString();
    }
}

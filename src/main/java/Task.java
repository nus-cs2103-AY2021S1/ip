import java.util.Date;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected final TaskType taskType;

    public Task(String description, TaskType taskType, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getSavedString() {
        return taskType.getSymbol() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public boolean isOccuringOn(Date date) {
        return false;
    }

    public boolean fulfilCriteria(Date date, String keyWord) {
        return (date == null || isOccuringOn(date)) && (keyWord == null || description.toLowerCase().contains(keyWord.toLowerCase()));
    }

    @Override
    public String toString() {
        return "[" + taskType.getSymbol() + "]" + getStatusIcon() + " " + description;
    }
}

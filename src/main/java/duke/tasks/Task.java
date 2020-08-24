package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected final String done = "[\u2713] ";
    protected final String start = "[\u2718] ";
    protected TaskType type;

    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TaskType getType() {
        return type;
    }


    public Task(String description, int index, boolean isOver) {
        this.description = description;
        this.isDone = isOver;
        this.index = index;
    }

    public String getStatusWithIndex() {
        return String.format("%s. %s", index, toString());
    }

    public String toString() {
        return String.format("%s%s", isDone ? this.done : this.start, this.description);
    }
}
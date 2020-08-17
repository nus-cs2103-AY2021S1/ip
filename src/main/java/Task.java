public class Task {
    private String description;
    private boolean isDone;

    public Task(String name, boolean isCompleted) {
        this.description = name;
        this.isDone = isCompleted;
    }

    public String toString() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getSymbol() {
        return (isDone ? "\u2713" : "\u2718");
    }

}

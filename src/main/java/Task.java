public class Task {

    protected String description;
    protected boolean isDone;
    protected String symbol;
    protected String by;

    public Task(String description) {
        this.description = description;
        isDone = false;
        symbol = "";
        by = "";
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return (getStatusIcon() + " " + description);
    }
}

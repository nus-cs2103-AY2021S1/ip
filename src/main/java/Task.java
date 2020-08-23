public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTime() {
        return "";
    }

    public String getStatusIcon() {
        return isDone 
            ? "\u2713" 
            : "\u2718"; //return tick or X symbols
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String symbol = this.getStatusIcon();
        return "[" + symbol + "] " + this.description;
    }
}
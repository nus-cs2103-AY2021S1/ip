public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    public String getStatusLetter() {
        return (isDone ? "y" : "n");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String createSaveDataLine();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " ";
    }
}
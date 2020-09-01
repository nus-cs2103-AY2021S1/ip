public abstract class Task {
    protected boolean isCompleted = false;
    protected String name;

    protected Task (String name) {
        this.name = name;
    }

    protected Task (String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public void markDone() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        String completionStatus = isCompleted ? "[✓]" : "[✗]";
        return completionStatus + " " + name;
    }

    public String toSaveFormat() {
        String completionStatus = isCompleted ? "1" : "0";
        return completionStatus + " | " + name;
    }
}

public class Task {
    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return isDone ? "O" : "X";
    }

    public boolean markAsDone() {
        if (this.isDone) {
            return false;
        }
        else {
            this.isDone = true;
            return true;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
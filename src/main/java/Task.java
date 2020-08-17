public class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void checked() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String tick = "✔";
        String cross = "✘";
        if (isDone) {
            return "[" + tick + "] " + this.description;
        } else {
            return "[" + cross + "] " + this.description;
        }
    }
}

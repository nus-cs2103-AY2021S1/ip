public class Task {
    private String message;
    private boolean isDone;

    Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getTypeLetter() {
        // dummy value
        return "";
    }
}

public class Task {
    protected String description;
    protected boolean isDone;
    char type;
    String unparseMessage;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(boolean isDone, String description, char type) {
        this.isDone = isDone;
        this.description = description;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void finish() {
        this.isDone = true;
        int indexOf0 = unparseMessage.indexOf('0');
        unparseMessage = unparseMessage.substring(0, indexOf0) + "1" + unparseMessage.substring(indexOf0+1);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

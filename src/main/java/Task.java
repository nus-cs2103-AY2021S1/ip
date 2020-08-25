public class Task {
    protected String tag = "";
    protected String description;
    protected boolean isDone;
    public static final String tick = "\u2713";
    public static final String cross = "\u2718";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return isDone ? tick : cross; //return tick or X symbols
    }

    public int getStatusBinary() {
        return isDone? 1 : 0;
    }

    public String getTaskType() {
        return tag;
    }

    public String markAsDone() {
        isDone = true;
        return toString();
    }

    public String toPrint() {
        return tag + "|" + getStatusBinary() + "|" + getDescription();
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

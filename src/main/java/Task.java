public class Task {
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
        return (isDone ? tick : cross); //return tick or X symbols
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public String markAsDone() {
        isDone = true;
        return getStatus();
    }

//    public String toString() {
//        return "";
//    }
}

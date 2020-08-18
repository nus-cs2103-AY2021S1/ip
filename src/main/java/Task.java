public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        String sign = isDone ? "✓" : "✘"; //return tick or X symbols
        return (String.format("[%s]", sign));
    }

    public String getOutput() {
        return String.format("%s %s", getStatusIcon(), this.description);
    }

    public void setDone() {
        isDone = true;
    }
}
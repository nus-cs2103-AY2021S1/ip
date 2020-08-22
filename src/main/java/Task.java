public class Task {
    protected boolean isDone;
    protected final String name;

    protected Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    protected Task(String line, boolean isAutomated) {
        this.isDone = line.charAt(4) == '\u2713';
        this.name = line.substring(7);
    }

    public String getName() {
        return this.name;
    }

    public void complete() {
        this.isDone = true;
    }

    protected String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getName();
    }
}

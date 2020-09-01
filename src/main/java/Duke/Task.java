package Duke;

public class Task {
    public final static String TICK = "\u2713";
    public final static String CROSS = "\u2718";

    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public String getParsedData() {
        return String.valueOf(this.isDone) + "/" + this.name;
    }

    public String toString() {
        String symbol = isDone ? Task.TICK : Task.CROSS;
        return "[" + symbol + "] " + name;
    }
}


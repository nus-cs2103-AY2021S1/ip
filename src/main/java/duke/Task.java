package duke;

public class Task {
    protected String title;
    protected boolean complete;

    public Task(String title) {
        this.title = title;
        this.complete = false;
    }

    public Task(String title, Boolean isComplete) {
        this.title = title;
        this.complete = isComplete;
    }

    public boolean isDone() {
        return this.complete;
    }

    public void complete() {
        this.complete = true;
    }

    public String saveString() {
        int completeSymbol = this.complete ? 1 : 0;
        return String.format("T|%d|%s", completeSymbol, this.title);
    }

    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("%s %s", completeSymbol, this.title);
    }
}
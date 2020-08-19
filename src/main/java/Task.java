public class Task {
    protected String title;
    protected boolean complete;

    public Task(String title) {
        this.title = title;
        this.complete = false;
    }

    public boolean isDone() {
        return this.complete;
    }

    public void complete() {
        this.complete = true;
    }

    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("%s %s", completeSymbol, this.title);
    }
}
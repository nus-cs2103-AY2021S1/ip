public abstract class Task {
    protected String description;
    protected boolean done = false;

    protected Task(String description) {
        this.description = description;
    }

    protected boolean isDone() {
        return this.done;
    }

    protected Task markAsDone() {
        this.done = true;
        return this;
    }


    @Override
    public String toString() {
        String status = this.done ? "[✓]" : "[✗]";
        return status + " " + description;
    }

}

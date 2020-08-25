public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    public void done() {
        this.isDone = true;
    }

    public void markAsDone() {
        done();
    }

    public String doneString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public abstract String toSaveString();

    ;
    @Override
    public String toString() {
        return String.format("%s %s", this.getIcon(), description);
    }

}

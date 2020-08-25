public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        UI.printMarkAsDone(this);
    }

    public String writeSaveFormat() {
        String store = "D |";
        if (isDone) {
            store += " 1 |";
        } else {
            store += " 0 |";
        }
        store += " " + this.description;
        return store;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    public boolean containsKeyword(String substring){
        return this.description.contains(substring);
    }

}


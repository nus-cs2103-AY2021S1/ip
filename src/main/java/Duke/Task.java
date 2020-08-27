package Duke;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return tick or cross symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean checkDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toData () {
        return checkDone()
                ? "T//1//" + getDescription()
                : "T//0//" + getDescription();
    }

    public String toString() {
        return '[' + getStatusIcon() + "] " + getDescription();
    }

}

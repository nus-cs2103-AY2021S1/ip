package Duke;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "X");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean checkIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toData () {
        return checkIsDone()
                ? "T//1//" + getDescription()
                : "T//0//" + getDescription();
    }

    public String toString() {
        return '[' + getStatusIcon() + "] " + getDescription();
    }

}

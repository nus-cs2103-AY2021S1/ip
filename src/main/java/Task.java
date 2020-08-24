import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getType() {
        if (type.equals("ToDos")) {
            return "T";
        } else if (type.equals("Deadlines")) {
            return "D";
        } else if (type.equals("Events")) {
            return "E";
        }
        return "Error";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "  [" + this.getType() + "][" + this.getStatusIcon() + "]" + this.description;
    }
}

/**
 * Represents a general task that has a name and status of completion.
 * More specific task will inherit from this class.
 */
public class Task {
    protected String name;
    protected boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public boolean isDone() {
        return this.status;
    }

    public void markAsDone() {
        this.status = true;
    }

    public String toString() {
        return "[" + (status ? "\u2713" : "\u2718") + "] " + this.name.trim();
    }


    public String convertTxt() {
        return "T | " + (this.status ? "1" : "0") + " | " + name;
    }

    public String getDate() {
        return "";
    }

    public String getName() {
        return this.name.trim();
    }


}

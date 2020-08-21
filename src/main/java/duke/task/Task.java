package duke.task;

abstract public class Task {

    protected String description;
    protected int status;

    public Task(String description) {
        this.description = description;
        this.status = 0;
    }

    public Task(String description, int status) {
        this.description = description;
        this.status = status;
    }


    public String getStatusIcon() {
        return (status == 1 ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        status = 1;
    }

    public int getStatus() {
        return status;
    }
    abstract public String saveText(int status);

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}

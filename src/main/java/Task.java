public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void complete() {
        if (this.isDone) {
            System.out.println("Task is already completed.");
        } else {
            System.out.println("Task completed: ");
        }
        this.isDone = true;
        System.out.println(this.toString());
    }

    public String store() {
        return this.isDone + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
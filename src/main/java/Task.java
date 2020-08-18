public class Task {
    protected String description;
    protected boolean isDone;

    //Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Overloading
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    //Methods
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public Task markAsDone() {
        return new Task(this.description, true);
    }

    @Override
    public String toString() {
        return this.description;
    }
}

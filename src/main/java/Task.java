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

    public static Task create(String description) throws TaskException {
        if (description.length() == 0) {
            throw new TaskException(" ☹ OOPS!!! The description of a task cannot be empty.");
        }
        return new Task(description);
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
    
    public String stringify() {
        return this.toString();
    }

    @Override
    public String toString() {
        return this.description;
    }
}

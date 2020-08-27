public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
    }

    public void markAsDone() {
        isDone = true;
    }
    
    public static Task taskify(String taskString) throws DukeException {
        return Parser.getTask(taskString);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
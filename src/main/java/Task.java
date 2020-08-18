public class Task {
    public String name;
    public boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone= isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markDone() {
        Task done = new Task(this.name, true);
        return done;
    }

}

public class Task {
    protected String description;
    protected boolean done = false;

    public Task(String description){
        this.description = description;

    }

    public void completeTask() {
        done = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "\u2713" : "\u2718", this.description);

    }
}

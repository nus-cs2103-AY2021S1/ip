public class Task {
    private boolean done;
    private String name;

    Task(String name) {
        this.done = false; // new tasks are not done
        this.name = name;
    }

    public void markDone() {
        this.done = true;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.done;
    }
}

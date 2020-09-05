package tasks;

public class Task {
    private boolean done;
    private String name;

    /**
     * Creates a Task.
     *
     * @param name name of the task.
     */
    public Task(String name) {
        this.done = false; // new tasks are not done
        this.name = name;
    }

    public String getDescription() {
        return this.name;
    }

    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    /**
     * ToString method to display the tasks in.
     */
    public String toString() {
        if (isDone()) {
            return "[Completed] " + this.name;
        } else {
            return "[Pending] " + this.name;
        }
    }

    public String toWrite() {
        return "";
    }
}

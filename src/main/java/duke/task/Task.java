package duke.task;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Completes a task.
     */
    public void completeTask() {
        this.done = true;
    }

    /**
     * Describes task.
     *
     * @return String that describes task.
     */
    @Override
    public String toString() {
        String symbol = (this.done ? "[\u2713] " : "[\u2718] ");
        return symbol + this.description;
    }

    /**
     * Describes task to be saved in hard disk.
     *
     * @return String that will be stored on hard disk.
     */
    public String saveToHardDisk() {
        int isDone = this.done ? 1 : 0;
        return " | " + isDone + " | " + this.description;
    }
}

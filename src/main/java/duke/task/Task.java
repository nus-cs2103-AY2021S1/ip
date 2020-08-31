package duke.task;

/**
 * Abstract class Task that provides basic functionality to the other Task objects
 * that extends from it.
 */
public abstract class Task {

    private boolean done;
    private String task;

    Task(String task) {
        this.done = false;
        this.task = task;
    }

    Task(String task, boolean done) {
        this.done = done;
        this.task = task;
    }
    public boolean isDone() {
        return this.done;
    }
    public String getDescription() {
        return this.task;
    }

    /**
     * Marks the Task as completed.
     *
     * @return The Task that has been completed.
     */
    public String markDone() {
        this.done = true;
        return "Great job, keep it up!\n" + this.toString();
    }

    /**
     * Marks the Task as not completed.
     *
     * @return The Task that has been marked as not complete.
     */
    public String revertDone() {
        this.done = false;
        return "Guess you made a mistake huh?\n" + this.toString();
    }

    /** Provides the string representation of the Task when it is saved.
     *
     * @return The string representation of the Task to be used in saving.
     */
    public String getSaveString() {
        String result = "";
        if (this.done) {
            result += "[1] ";
        } else {
            result += "[0] ";
        }
        result += this.task;
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        if (this.done) {
            result += "[\u2713] ";
        } else {
            result += "[\u2718] ";
        }
        result += this.task;
        return result;
    }
}

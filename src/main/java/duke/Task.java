package duke;

/**
 * Represents a task item that user creates.
 */
public class Task {
    /**
     * Represents the completion status of a task object.
     * PENDING: the task has yet to be completed.
     * DONE: the task is already completed.
     */
    public enum Status {
        PENDING,
        DONE;

        /**
         * Produce a String to represent the completion status of a task to user.
         * @return
         */
        public String statusToSymbol() {
            if (this == DONE) {
                return "[\u2713] ";
            } else {
                return "[\u2718] ";
            }
        }

    }
    String name;
    Status status;

    /**
     * Constructor
     * @param name Name of the task
     * @param status Completion status of the task
     */
    public Task(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    /**
     * Change the status of the task to DONE
     */
    public void markAsDone() {
        this.status = Status.DONE;
    }

    /**
     * Generate a String that represents this task object.
     * This String is to be stored into data management file, not to be printed for user.
     * @return The String generated
     */
    public String toStore() {
        return this.toString();
    }

    /**
     * Undo the task, change the status of a task back to PENDING.
     */
    public void undo() {
        this.status = Status.PENDING;
    }
}

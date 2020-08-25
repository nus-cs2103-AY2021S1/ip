package ultron.tasks;

public abstract class Task {

    /**
     * Store the messages.
     */
    private final String message;

    /**
     * Boolean to check if task is completed.
     */
    private boolean completed;

    /**
     * Abstract task class for all Tasks.
     * @param description   Description of the Task
     */
    //Constructor for Task
    public Task(final String description) {

        //Store the message
        this.message = description;

        //Mark the current task as not done
        this.markUnDone();
    }

    /**
     * Get the description of the task.
     * @return Message for the task
     */
    public String getMessage() {

        //Return the message
        return this.message;
    }

    /**
     * Check is the task is done.
     * @return boolean denoting if the task is completed
     */
    public boolean isDone() {

        //returns true if the task is done
        return this.completed;
    }

    /**
     * Get the type of task.
     * @return String for type of task
     */
    public abstract String getType();

    /**
     * Get the command for the class as a string.
     * @return String for command
     */
    public abstract String getCommand();

    /**
     * Mark the task as completed.
     */
    public void markDone() {


        //Mark the task as complete
        this.completed = true; //mark the task as done

    }

    /**
     * Mark the task as not completed.
     */
    public void markUnDone() {

        //Mark the test as not done
        this.completed = false; //mark the task as undone
    }

    /**
     * Get the status icon denoting if the task is completed.
     * @return Status icon as a String
     */
    public String getStatusIcon() {

        //Get the statusIcon based on state
        return (this.isDone() ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Convert the task to string format.
     * @return String format of the task
     */
    @Override
    public String toString() {

        //Convert the object to string
        return String.format("[%s] %s",
                this.getStatusIcon(),
                this.getMessage());
    }
}

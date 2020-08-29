package tasks;

/**
 * The Task Class is a Abstract Base class for any extending class that acts as 
 * a Task object for the Duke Chatbot
 */
abstract class Task {
    // SEPERATION Attribute is used to encode the different attributes of the Task Class
    public static final String SEPERATOR = "#sep#";
    protected String description;
    protected boolean isDoneTask;

    /**
     * Constructor for a Task
     * @param description String description of the task
     * @param done Done Status of the task
     */
    protected Task(String description, boolean done) {
        this.description = description;
        this.isDoneTask = done;
    }

    /**
     * Returns the check for if the task is already done.
     * @return Boolean representing whether the task is done
     */
    public boolean done() {
        return this.isDoneTask;
    }

    /**
     * Mark a generic Task object as done
     */
    public void doTask() {
        this.isDoneTask = true;
    }

    /**
     * Get the description of the task
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checklist icon for displaying in the toString Representation.
      * @return either done or not done
     */
    public String statusIcon() {
        return this.isDoneTask ? "[\u2713] " : "[\u2718] ";
    }

    @Override
    public String toString() {
        return this.statusIcon() + this.getDescription();
    }

    /**
     * Takes done status and attributes to encode the String
     * @return a encoded string version of task for writing to text file.
     */
    public String saveTask() {
        return this.isDoneTask + SEPERATOR + description;
    }
}

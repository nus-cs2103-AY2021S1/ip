package main.java.duke;

/**
 * Encapsulates the details of a task.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Creates an instance of the Task class, the parent class for Todo, Deadline and Event classes
     * @param description description of the task
     */
    public Task(String description) {
        String[] descriptionComponents = description.split("/priority ", 2);
        if (descriptionComponents.length > 1) {
            // There is a priority for the task
            String priorityString = descriptionComponents[1];
            try {
                storePriority(priorityString);
            } catch (PriorityException pe) {
                System.out.println(pe.getMessage());
            }
            this.description = descriptionComponents[0];
        } else {
            // things as per normal
            this.description = description;
        }
        this.isDone = false;
    }

    /**
     * Stores the priority in the Task priority field
     * @param priorityString the string containing the priority to be parsed.
     * @throws PriorityException
     */
    public void storePriority(String priorityString) throws PriorityException {
        if (priorityString.equals("low")) {
            priority = Priority.LOW;
        } else if (priorityString.equals("medium")) {
            priority = Priority.MEDIUM;
        } else if (priorityString.equals("high")) {
            priority = Priority.HIGH;
        } else {
            throw new PriorityException("Priority can only be one of low, medium or high");
        }
    }

    /**
     * Returns the symbol indicating if the task has been completed.
     * "\u2713" is unicode for the tick icon and
     * "\u2718 is unicode for the cross icon.
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks this instance of Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this instance of Task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Sets the priority of this Task
     * @param p the value of the enum Priority
     */
    public void setPriority(Priority p) {
        this.priority = p;
    }

    /**
     * Returns a string representation of the Task object.
     * @return a string representation of the Task object.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + description;
    }
}

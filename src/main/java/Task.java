/**
 * The Task class is the parent class of all the different
 * types of tasks (e.g. ToDo, Event, Deadline)
 * Gives a basic description of all the different types of tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task instance with isDone variable set to
     * false.
     *
     * @param description The description or the details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Marks the task to be done
     * by setting the isDone property of a task to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overrides the Object's toString method
     * to describe the details of the task e.g. Type, description
     * @return The String that represents the deadline's details.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone?  "Done" : "!!!!", description);
    }


    /**
     * This method compares this task with another and determine if they are equal.
     *
     * @param obj The task that needs to be compared with the current task.
     * @return True if this task is equal to obj task.
     */
    @Override
    public boolean equals(Object obj) {
        Task temp = (Task) obj;
        return this.description.equals(temp.description);
    }
}

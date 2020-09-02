
/**
 * The Task class is the parent class of all the different
 * types of tasks (e.g. ToDo, Event, Deadline)
 * Gives a basic description of all the different types of tasks
 */
public class Task {
    protected String task_info;
    protected boolean task_completion;

    /**
     * Constructs a Task instance with task_completion set to
     * false.
     *
     * @param task_info The description or the details of the task.
     */
    public Task(String task_info) {
        this.task_info = task_info;
        this.task_completion = false;
    }

    /**
     * Marks the task as complete
     * by setting the task__completion of a task to be true.
     */

    public void complete() {
        task_completion = true;
    }

    /**
     * Overrides the Object's toString method
     * to describe the details of the task e.g. Type, task_completion
     * @return The String that represents the deadline's details.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", task_completion ?  "Done" : "!!!!", task_info);
    }

}
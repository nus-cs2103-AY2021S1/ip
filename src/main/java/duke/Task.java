package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description the description of the Task item.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Updates the Task object by setting it to be done or not yet done.
     *
     * @param check sets the status of the object to be done or not done. 1 = done, any other int is not done.
     */
    public void updateTask(int check) {
        if (check == 1) {
            isDone = true;
        } else {
            isDone = false;
        }
    }

    @Override
    public String toString() {
        return (isDone ? "[" + "\u2713" + "]" + " " + this.description
                : "[" + "\u2718" + "]" + " " + this.description);
    }
}

package sparkles.task;

/**
 * Represent a Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeOfTask;

    /**
     * Create a Task object representing a Task from the user.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        typeOfTask = null;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Return "O" or "X" based on whether the task is
     * done or not done.
     *
     * @return "O" or "X"
     */
    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = !isDone;
    }

    /**
     * Print task's details with numbering.
     *
     * @param i numbering of the task
     */
    public String printTask(int i) {
        String output = "     " + i + "." + this.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Print task's details.
     */
    public String printTask() {
        String output = "     " + this.toString();
        System.out.println(output);
        return output;
    }

    public String getString() {
        return "     " + this.toString();
    }

    /**
     * Package the deadline to a format used to store in the task.txt.
     * A file in the local disk to store tasks.
     *
     * @return String of Task's details in custom disk format
     */
    public String convertToDiskFormat() {
        return this.getStatusIcon() + " | " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Task)) {
            return false;
        } else {
            Task t = (Task) obj;

            boolean sameDesc = this.description.equals(t.description);
            boolean sameTypeOfTask = this.typeOfTask.equals(t.typeOfTask);
            return sameDesc && sameTypeOfTask;
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}

package sparkles.task;

/**
 * Represent a Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return "O" or "X" based on whether the task is
     * done or not done.
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
     * @param i
     */
    public void printTask(int i) {
        String output = "     " + i + "." + this.toString();
        System.out.println(output);
    }

    /**
     * Print task's details.
     */
    public void printTask() {
        String output = "     " + this.toString();
        System.out.println(output);
    }

    public String getString() {
        return "     " + this.toString();
    }

    /**
     * Package the deadline to a format used to store in the task.txt.
     * A file in the local disk to store tasks.
     * @return
     */
    public String diskFormat() {
        return this.getStatusIcon() + " | " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof  Task)) {
            return false;
        } else {
            Task t = (Task) obj;

            return t.description.equals(this.description);
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
/**
 * The Task class represents a task.
 *
 * @author Jaya Rengam
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String type;

    Task(String name, boolean isDone, String type) {
        this.name = name;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Sets isDone to true
     *
     * @throws CartonaException if the task is already done
     */
    public void complete() throws CartonaException {
        if (this.isDone) {
            throw new CartonaException(String.format("Error: Task %s is already done", this.toString()));
        }
        this.isDone = true;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[✓] %s", name);
        } else {
            return String.format("[✗] %s", name);
        }
    }

    /**
     * Gets an alternative string representation of the task
     */
    public String getAbbreviatedString() {
        int isDoneRep = this.isDone ? 1 : 0;
        return String.format("%s | %d | %s", this.type, isDoneRep, this.name);
    }

    public boolean checkIfDone() {
        return this.isDone;
    }
}

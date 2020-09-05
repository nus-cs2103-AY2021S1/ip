package task;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Gets name of the task
     * @return task name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the task to be done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets whether the task is done
     *
     * @return whether done or not
     */
    public boolean getDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] ", (this.isDone ? "✓" : "✗")) + this.getName();
    }

}

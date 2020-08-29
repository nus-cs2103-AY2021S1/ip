package task;

public abstract class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
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
        this.done = true;
    }

    /**
     * Gets whether the task is done
     *
     * @return whether done or not
     */
    public boolean getDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}

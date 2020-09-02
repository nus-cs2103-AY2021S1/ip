package task;

abstract public class Task {

    /**
     * The name of the current event
     **/
    protected final String name;

    /**
     * The status of the event
     **/
    protected final boolean isDone;

    /**
     * Initializes current task
     *
     * @param name
     * @param isDone
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Get the name of the current task
     **/
    public String getName() {
        return this.name;
    }

    /**
     * Get the type of the current task
     **/
    public abstract String getType();

    /**
     * Check get the status of the event, return 1 if done, 0 if is not done
     **/
    public int isDone() {
        return isDone ? 1 : 0;
    }

    /**
     * Set the current task to done
     **/
    public abstract Task setToTrue();

    /**
     * Get the specific time of the current task
     **/
    public abstract String getEnd();

    /**
     * Check if the current task match the keyword
     **/
    public boolean isNameMatchKeyWord(String keyword) {
        return this.name.toLowerCase().contains(keyword);
    }

}

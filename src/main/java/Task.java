package main.java;

/**
 * Task object
 */
public class Task {
    protected String taskName;
    protected Boolean done;

    /**
     * Initializes the task object
     *
     * @param taskName task name or description
     * @throws DukeInvalidTaskException
     */
    public Task(String taskName) throws DukeInvalidTaskException {
        if (!taskName.equals(null) && !taskName.equals(" ")) {
            this.taskName = taskName;
            this.done = false;
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    /**
     * Marks the task as done
     */
    public void checkOff() {
        this.done = true;
    }

    /**
     * Checks if the task is done
     * @return a boolean indicating if the task is done
     */
    public Boolean isDone(){
        return this.done;
    }

    /**
     * Gets the task name
     * @return String representing the task's name
     */
    public String getTaskName(){
        return this.taskName;
    }

    /**
     * Gets a representation of the task object
     * @return String object representing the task
     */
    @Override
    public String toString(){
        String finished = this.done ? "✓" : "✗";
        String toReturn = "[" + finished + "]" + taskName;
        return toReturn;
    }
}

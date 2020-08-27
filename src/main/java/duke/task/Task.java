package duke.task;

import duke.component.DukeException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructor for task abstract class
     * @param description string of task's description
     *
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * returns approprivate icon for task done or not
     * @return string of icon
     */
    public String getStatusIcon() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "] ");
    }

    /**
     * changes status of task to done
     */
    public void markDone() {
        this.isDone = true;
    }


    public String toString() {
        return this.getStatusIcon() + description;
    }

    /**
     * checks if task is done based on inary value
     * @param number character of number representing done status
     * @return boolean representing icon status
     * @throws DukeException exception thrown when exception caught while running
     */
    public static boolean checkIfDone(char number) throws DukeException {
        switch(number) {
            case '0':
                return false;
            case '1':
                return true;
            default:
                throw new DukeException("Item in list on HDD does not have a 'done' status");
        }
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public abstract String stringToSave();


}


package duke.task;

import java.time.LocalDateTime;

public abstract class Task {
    protected final String text;
    protected boolean done = false;

    public Task(String text) {
        this.text = text;
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    abstract public String toString();

    /**
     * Returns the task in a format readable by Storage when loading a save file.
     *
     * @return The task in a Storage readable format.
     */
    abstract public String toCommand();

    /**
     * Returns true if the dateTime of the task is within the the given time frame.
     * Otherwise, returns false.
     *
     * @param givenDateTime Given start date and time of the time frame.
     * @param hours The duration of the time frame in hours.
     * @return True or False.
     */
    abstract public boolean compareTime(LocalDateTime givenDateTime, long hours);
}

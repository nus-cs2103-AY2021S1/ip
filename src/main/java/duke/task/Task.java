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
    public abstract String toString();

    /**
     * Returns the task in a format readable by Storage when loading a save file.
     *
     * @return The task in a Storage readable format.
     */
    public abstract String toCommand();

    /**
     * Returns true if the dateTime of the task is within the the given time frame.
     * Otherwise, returns false.
     *
     * @param givenDateTime Given start date and time of the time frame.
     * @param hours The duration of the time frame in hours.
     * @return True or False.
     */
    public abstract boolean compareTime(LocalDateTime givenDateTime, long hours);

    /**
     * Returns true if the keyword is in the Task description.
     * Returns false otherwise.
     *
     * @param keyword The given keyword to search for.
     * @return True or False.
     */
    public boolean find(String keyword) {
        String pattern = "(.*)" + keyword + "(.*)";
        return text.matches(pattern);
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    public abstract TaskType getType();
}

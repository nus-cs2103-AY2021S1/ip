package main.java.task;

import java.time.LocalDateTime;

public class Task {
    protected String type;
    protected String description;
    protected LocalDateTime time;
    protected boolean isDone = false;

    /**
     * Create a task with a title and its optional datetime
     * @param description the title of the task
     * @param time the time of the task
     */
    public Task(String description, LocalDateTime time) {
        type = "Task";
        this.description = description;
        this.time = time;
    }

    /**
     * Marks the task as finished.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Serializes the task to be store in the disk.
     * @return the serial of the task
     */
    public String serialize() {
        String datetimeString = time.getDayOfMonth() + "/" +
                time.getMonthValue() + "/" +
                time.getYear() + " " +
                (time.getHour() * 100 + time.getMinute());
        if (time.equals(LocalDateTime.MIN)) {
            datetimeString = "null";
        }
        return type + "%%%" + description + "%%%" + datetimeString + "%%%" + (isDone ? 1 : 0);
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", (isDone ? "\u2713" : "\u2718"), description);
    }
}


package duke.task;

import duke.misc.Const;

import java.time.LocalDateTime;

public class Task {
    protected String type;
    protected String description;
    protected LocalDateTime time = Const.DEFAULT_TIME;
    protected boolean isDone = false;

    /**
     * Create a task with a title and its optional datetime
     *
     * @param description the title of the task
     * @param time        the time of the task
     */
    public Task(String description, LocalDateTime time) {
        type = "Task";
        this.description = description;
        this.time = time;
    }

    public Task(String description, LocalDateTime time, boolean isDone) {
        type = "Task";
        this.description = description;
        this.time = time;
        this.isDone = isDone;
    }

    public Task(String description) {
        type = "Task";
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        type = "Task";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as finished.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Serializes the task to be store in the disk.
     *
     * @return the serial of the task
     */
    public String serialize() {
        assert time != null: "cannot serialize when time is null";
        String datetimeString = time.getDayOfMonth() + "/"
                + time.getMonthValue() + "/"
                + time.getYear() + " "
                + (time.getHour() * 100 + time.getMinute());
        if (time.equals(Const.DEFAULT_TIME)) {
            datetimeString = Const.NO_TIME;
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


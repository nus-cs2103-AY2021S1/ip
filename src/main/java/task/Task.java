package main.java.task;

import java.time.LocalDateTime;

public class Task {
    protected String type;
    protected String description;
    protected LocalDateTime time;
    protected boolean isDone = false;

    public Task(String description, LocalDateTime time) {
        type = "Task";
        this.description = description;
        this.time = time;
    }

    public void markAsDone() {isDone = true;}

    public String serialize() {
        String datetimeString = time.getDayOfMonth() + "/" +
                time.getMonthValue() + "/" +
                time.getYear() + " " +
                (time.getHour() * 100 + time.getMinute());
        if (time.equals(LocalDateTime.MIN)) datetimeString = "null";
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


package main.java.com.jacob.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected LocalDateTime dueDateTime;

    /**
     * Constructor for Task
     * @param description Description for Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for parsing date time
     * @param description Description of task
     * @param dateTime date time of task
     */
    public Task(String description, String dateTime) {
        this.description = description;
        this.isDone = false;
        this.dueDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        // example command: deadline return book /by 2019-10-15 1800
    }

    /**
     * Get status of task completion
     * @return Done status
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Set task as done
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Get description
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get current status of task
     * @return current status of task
     */
    public String getCurrentStatus() {
        if (dueDateTime != null) {
            return "  [" + type + "]" + "[" + getStatusIcon() + "] " + getDescription()
                    + " " + dueDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha"));
        }
        return "  [" + type + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Convert the description to the format suitable for file
     * @return String containing suitable format
     */
    public String convertToFile() {
        if (dueDateTime != null) {
            return String.format("%s,%s,%s,%s", type, isDone ? 1 : 0 , getDescription(),
                    dueDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm")));
        }
        return String.format("%s,%s,%s", type, isDone ? 1 : 0, getDescription());
    }

    /**
     * Get Due Date and Time parsed
     * @return LocalDateTime object with the date time
     */
    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }
}

package viscount.task;

import viscount.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    private static final String STRING_FORMAT = "[E][%s] %s (at: %s)";
    private static final String TASK_DATA_FORMAT = "%s|%d|%s|%s";

    private LocalDateTime eventTime;

    public Event(String description, boolean isDone, LocalDateTime eventTime) {
        super(TaskType.EVENT, description, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public boolean hasDateTime() {
        return true;
    }
    
    @Override
    public LocalDateTime getDateTime() {
        return eventTime;
    }

    /**
     * Gives a task data representation of the task in String format.
     *
     * @return Task data representation of the task.
     */
    @Override
    public String toTaskData() {
        return String.format(Event.TASK_DATA_FORMAT, taskType.name(), isDone ? 1 : 0, description,
                eventTime.format(Parser.TASK_DATA_DATE_TIME_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format(Event.STRING_FORMAT, getStatusIcon(), description, 
                eventTime.format(Parser.OUTPUT_DATE_TIME_FORMATTER));
    }
}

package viscount.task;

import viscount.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private static final String STRING_FORMAT = "[D][%s] %s (by: %s)";
    private static final String TASK_DATA_FORMAT = "%s|%d|%s|%s";

    private LocalDateTime dueDate;

    public Deadline(String description, boolean isDone, LocalDateTime dueDate) {
        super(TaskType.DEADLINE, description, isDone);
        this.dueDate = dueDate;
    }
    
    @Override
    public boolean hasDateTime() {
        return true;
    }
    
    @Override
    public LocalDateTime getDateTime() {
        return dueDate;
    }

    @Override
    public String toTaskData() {
        return String.format(Deadline.TASK_DATA_FORMAT, taskType.name(), isDone ? 1 : 0, description,
                dueDate.format(Parser.TASK_DATA_DATE_TIME_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format(Deadline.STRING_FORMAT, getStatusIcon(), description, 
                dueDate.format(Parser.OUTPUT_DATE_TIME_FORMATTER));
    }
}

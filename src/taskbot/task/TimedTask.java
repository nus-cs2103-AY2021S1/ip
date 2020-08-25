package taskbot.task;

import java.time.LocalDateTime;

public abstract class TimedTask extends Task {
    private LocalDateTime time;
    public TimedTask(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    /**
     * Creates an Event task given its completeness
     * @param task Description of task
     * @param time Time when task occurs
     * @param isDone Whether the task is complete
     */
    public TimedTask(String task, LocalDateTime time, boolean isDone) {
        super(task, isDone);
        this.time = time;
    }

    /**
     * @return LocalDateTime of the task.
     */
    public LocalDateTime getTime() {
        return time;
    }
}

package taskbot.task;

import java.time.LocalDateTime;

/**
 * Encapsulates a task with time limits.
 */
public abstract class TimedTask extends Task {
    /** Time limit of the task */
    private LocalDateTime time;

    /**
     * Creates a timed task.
     *
     * @param task The task description.
     * @param time LocalDateTime associated with the task.
     */
    public TimedTask(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    /**
     * Creates a timed task given its completeness.
     *
     * @param task Description of task.
     * @param time Time when task occurs.
     * @param isDone Whether the task is complete.
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

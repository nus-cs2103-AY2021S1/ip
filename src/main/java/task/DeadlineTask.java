package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Default Constructor for Deadline Task
     *
     * @param name     Name of Task.
     * @param deadline Deadline of Task.
     */
    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Alternative Constructor for Deadline Task
     *
     * @param name         Name of Task.
     * @param hasCompletedInt boolean to show if task has been completed.
     * @param deadline     Deadline of task.
     */
    public DeadlineTask(String name, int hasCompletedInt, LocalDateTime deadline) {
        super(name, TaskType.DEADLINE, hasCompletedInt);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeadlineTask that = (DeadlineTask) o;
        return Objects.equals(deadline, that.deadline);
    }

    /**
     * Formats task into a string for file saving
     *
     * @return formatted string representing the task.
     */
    public String getFormattedString() {
        return String.format(
                "D | %s | %s | %s\n",
                this.getHasCompletedInt(),
                this.getName(),
                this.getDeadline());
    }
}

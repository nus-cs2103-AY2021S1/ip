package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Default Constructor for Deadline Task
     * @param name Name of Task
     * @param deadline Deadline of Task
     */
    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Alternative Constructor for Deadline Task
     * @param name Name of Task
     * @param hasCompleted boolean to show if task has been completed
     * @param deadline Deadline of task
     */
    public DeadlineTask(String name, int hasCompleted, LocalDateTime deadline) {
        super(name, hasCompleted);
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
}

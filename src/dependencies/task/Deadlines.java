package dependencies.task;

import static dependencies.task.CompletionState.UNFINISHED;

public class Deadlines extends Task {
    private String deadline;

    Deadlines(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[Deadline][%s] %s (by: %s)", super.state == UNFINISHED ? "X"
                        : Character.toString((char)0x2713),
                super.task,
                deadline);
    }
}

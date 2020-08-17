package dependencies.task;

import static dependencies.task.CompletionState.UNFINISHED;

public class Events extends Task {
    private String date;
    Events(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format(
                "[Event][%s] %s (at: %s)", super.state == UNFINISHED ? "X"
                        : Character.toString((char)0x2713),
                super.task,
                date);
    }
}

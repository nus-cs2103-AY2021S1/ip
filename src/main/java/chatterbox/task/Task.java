package chatterbox.task;

import java.time.LocalDateTime;

/**
 * General task.
 */
public class Task {
    private static final String TICK = "\u2713";
    private static final String CROSS = "\u2717";

    protected LocalDateTime deadline;
    protected String prefix;
    protected String inputString;
    protected String contents;

    private boolean isDone = false;

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * Returns the original user input string used to create this task.
     *
     * @return Original user input string.
     */
    public String getInputString() {
        return inputString;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", prefix, (isDone ? TICK : CROSS), contents);
    }
}

package chatterbox.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * General task.
 */
public class Task {
    protected static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("MMM d yyyy HHmm'H'");
    protected LocalDateTime deadline;

    protected String inputString;
    private String contents;
    private boolean isDone = false;

    protected void setContents(String contents) {
        this.contents = contents;
    }

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
        return (isDone ? "[✓]" : "[✗]") + " " + contents;
    }
}

package duke.task;

import static duke.util.Keyword.CLOSE_BRACKET;
import static duke.util.Keyword.DEADLINE_BY;
import static duke.util.Keyword.DEADLINE_SYMBOL;
import static duke.util.Keyword.NIL;

import java.time.LocalDateTime;


public class Deadline extends Task {

    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE, NIL, deadline, false);
    }

    public Deadline(String description, boolean isDone, String timeFrame, LocalDateTime dateTime) {
        super(description, TaskType.DEADLINE, timeFrame, dateTime, isDone);
    }

    @Override
    public String toString() {
        return DEADLINE_SYMBOL + super.toString() + DEADLINE_BY + getTime() + CLOSE_BRACKET;
    }
}

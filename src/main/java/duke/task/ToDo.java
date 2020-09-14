package duke.task;

import java.time.LocalDateTime;

public class ToDo extends Task {

    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return done
                ? "[T][O] " + text
                : "[T][X] " + text;
    }

    @Override
    public String toCommand() {
        return done
                ? "done todo " + text
                : "todo " + text;
    }

    @Override
    public boolean compareTime(LocalDateTime givenDateTime, long hours) {
        return false;
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }
}

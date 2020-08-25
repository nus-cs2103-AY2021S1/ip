package duke.task;

import java.util.Objects;

public class Todo extends Task {
    public Todo(String desc) {
        super(desc, "todo");
    }

    @Override
    public String toSaveFormat() {
        return "todo " + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

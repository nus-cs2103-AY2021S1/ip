package duke.tasks;

import java.util.Objects;

/**
 * The class for todoo object.
 */
public class ToDo extends Task{

    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, String status) {
        super(task, Objects.equals(status, "1") ? true : false);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

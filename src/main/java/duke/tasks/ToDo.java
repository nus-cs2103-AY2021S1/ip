package duke.tasks;

import java.io.Serializable;

public class ToDo extends Task implements Serializable {

    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName();
    }
}

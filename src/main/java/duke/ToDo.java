package duke;

import java.io.Serializable;

public class ToDo extends Task implements Serializable {

    public ToDo(String s, Boolean b) {
        super(s, b);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName();
    }
}

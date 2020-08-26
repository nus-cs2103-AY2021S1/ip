package duke.task;

import duke.exception.DukeException;

public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
        if (this.desc.isBlank()) {
            throw new DukeException("The description cannot be empty");
        }
    }

    public String getSaveToFileString() {
        return "T`" + super.getSaveToFileString();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

package duke;

import java.time.LocalDateTime;

import duke.task.Task;

public class ToDoStub extends Task {
    public ToDoStub(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "test";
    }

    @Override
    public String toCommand() {
        return null;
    }

    @Override
    public boolean compareTime(LocalDateTime now, long hours) {
        return false;
    }
}

package duke.stub.task;

import java.time.LocalDate;

import duke.task.Task;

public class TodoStub extends Task {
    public TodoStub() {
        super("");
    }

    @Override
    public String toSaveString() {
        return (isDone ? "1" : "0") + "todo this is a todo stub";
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "This todo stub was " + (isDone ? "" : "not") + " marked as done";
    }
}

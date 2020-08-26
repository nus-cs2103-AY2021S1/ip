package duke.stub.task;

import duke.task.Task;

import java.time.LocalDate;

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
}

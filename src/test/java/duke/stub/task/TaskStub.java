package duke.stub.task;

import duke.task.Task;

import java.time.LocalDate;

public class TaskStub extends Task {
    public TaskStub() {
        super("");
    }

    @Override
    public String toSaveString() {
        return "";
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "This is a TaskStub.";
    }
}

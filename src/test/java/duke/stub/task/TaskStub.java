package duke.stub.task;

import duke.task.Task;

import java.time.LocalDate;

public class TaskStub extends Task {
    public TaskStub(String description) {
        super(description);
    }

    @Override
    public String toSaveString() {
        return "";
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return false;
    }
}

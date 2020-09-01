package duke.stub.task;

import java.time.LocalDate;

import duke.task.Task;

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

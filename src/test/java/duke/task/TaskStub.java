package duke.task;

import java.util.Optional;

public class TaskStub extends Task {
    TaskStub(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof TaskStub) {
            TaskStub otherTaskStub = (TaskStub) other;
            return this.isEqual(otherTaskStub);
        }
        return false;
    }

    @Override
    public String getTaskSymbol() {
        return "TS";
    }

    @Override
    public Optional<String> getTaskDatetime() {
        return Optional.empty();
    };
}

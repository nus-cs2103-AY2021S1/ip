package task;

import java.util.Objects;

public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name, TaskType.TODO);
    }

    public TodoTask(String name, int hasCompletedInt) {
        super(name, TaskType.TODO, hasCompletedInt);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TodoTask that = (TodoTask) o;
        return Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getHasCompletedInt(), that.getHasCompletedInt());
    }

    /**
     * Formats task into a string for file saving
     *
     * @return formatted string representing the task.
     */
    public String getFormattedString() {
        return String.format("T | %s | %s\n", this.getHasCompletedInt(), this.getName());
    }
}

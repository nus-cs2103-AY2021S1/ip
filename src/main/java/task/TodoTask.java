package task;

import command.DeadlineCommand;

import java.util.Objects;

public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }
    public TodoTask(String name, int hasCompleted) {
        super(name, hasCompleted);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    @Override
    public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TodoTask that = (TodoTask) o;
            return Objects.equals(this.getName(), that.getName()) &&
                    Objects.equals(this.getHasCompleted(), that.getHasCompleted());
    }
}

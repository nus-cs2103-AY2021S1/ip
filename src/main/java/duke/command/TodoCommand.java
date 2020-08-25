package duke.command;

import duke.task.Task;

import java.util.Objects;

public class TodoCommand extends AddAbstractTaskCommand {

    public TodoCommand(Task newTask) {
        super(newTask);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoCommand)) return false;
        TodoCommand cmd = (TodoCommand) o;
        return newTask == cmd.newTask;
    }

    @Override
    public int hashCode() {
        return Objects.hash(newTask);
    }
}

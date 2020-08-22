package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Arrays;

public class TaskListCommand extends Command {

    public TaskListCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof TaskListCommand) {
            TaskListCommand other = (TaskListCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}

package duke.command;

import duke.command.Command;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return (ui.showAllTasks(taskList) + ui.showNumberOfTasksLeft(taskList));
    }

    public boolean isExit() {
        return false;
    }
}

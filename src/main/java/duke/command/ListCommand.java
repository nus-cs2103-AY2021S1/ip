package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

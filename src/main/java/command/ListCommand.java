package main.java.duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.TaskList;
import main.java.duke.Ui;
import main.java.duke.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showAllTasks(taskList);
        ui.showNumberOfTasksLeft(taskList);
    }

    public boolean isExit() {
        return false;
    }
}

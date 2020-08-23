package main.command;

import main.task.TaskList;
import main.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(Ui ui, TaskList tasks) {
        ui.printTaskList(tasks);
    }

    @Override
    public boolean hasCommand() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}

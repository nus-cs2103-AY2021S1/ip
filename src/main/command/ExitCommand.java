package main.command;

import main.task.TaskList;
import main.ui.Ui;

public class ExitCommand implements Command {

    @Override
    public void execute(Ui ui, TaskList tasks) { }

    @Override
    public boolean hasCommand() {
        return false;
    }
}

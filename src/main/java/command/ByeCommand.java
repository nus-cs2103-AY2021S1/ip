package command;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(){}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

package main.java.seedu.duke.commands;

import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;
import main.java.seedu.duke.Storage;
import main.java.seedu.duke.DukeException;

public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.listTasks();
        // ui.showListMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

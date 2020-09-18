package main.java.duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.TaskList;
import main.java.duke.dukeexception.DukeException;
import main.java.duke.Ui;
import main.java.duke.Storage;

import java.io.IOException;

public class TerminationCommand extends Command {
    public TerminationCommand() {}

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            storage.save(taskList);
            return ui.showGoodbye();
        } catch (IOException e) {
            throw new DukeException("Something went wrong saving the tasks to data file");
        }
    }

    public boolean isExit() {
        return true;
    }
}

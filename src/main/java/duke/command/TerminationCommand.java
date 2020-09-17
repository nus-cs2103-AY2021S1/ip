package main.java.duke.command;

import main.java.duke.task.TaskList;
import main.java.duke.dukeexception.DukeException;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

public class TerminationCommand extends Command {
    public TerminationCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            storage.save(taskList);
            ui.showGoodbye();
        } catch (IOException e) {
            throw new DukeException("Something went wrong saving the tasks to data file");
        }
    }

    public boolean isExit() {
        return true;
    }
}

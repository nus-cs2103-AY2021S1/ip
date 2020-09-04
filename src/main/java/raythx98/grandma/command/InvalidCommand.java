package raythx98.grandma.command;

import raythx98.grandma.exception.DukeException;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

public class InvalidCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("lol");
    }
}

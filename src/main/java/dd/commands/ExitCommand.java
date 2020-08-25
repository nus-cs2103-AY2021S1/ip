package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String command, String item) {
        super(command, item);
    }

    @Override
    public void execute(TaskList tasks, Ui u, DataStorage ds) throws DukeException {
        u.exit();
        ds.writeData(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

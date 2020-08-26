package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command, String item) {
        super(command, item);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage ds) throws DukeException {
        int delNum = 0;

        try {
            delNum = Integer.parseInt(item);
        } catch (NumberFormatException ignored) {
        }

        if (delNum > 0 && delNum <= tasks.getTaskSize()) {
            ui.printDeletedTask(tasks.getTask(delNum-1));
            tasks.deleteTask(delNum-1);

            ui.printTasksSize(tasks.getTaskSize());
        } else {
            throw new DukeException().invalidTaskNumber();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

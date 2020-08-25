package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String command, String item) {
        super(command, item);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage ds) throws DukeException {
        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(item);
        }
        catch (NumberFormatException ignored) {
        }

        if (taskNum > 0 && taskNum <= tasks.getTaskSize()) {
            tasks.getTask(taskNum - 1).markAsDone();
            ui.printDoneTask(tasks.getTask(taskNum - 1));
        }
        else {
            throw new DukeException().invalidTaskNumber();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
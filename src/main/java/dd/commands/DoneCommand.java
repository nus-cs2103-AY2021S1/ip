package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

public class DoneCommand extends Command {

    /**
     * Class Constructor.
     *
     * @param command Command given.
     * @param item Details of item that is done.
     */
    public DoneCommand(String command, String item) {
        super(command, item);
    }

    /**
     * Marks the task given in item as done.
     *
     * @param tasks Current TaskList to modify.
     * @param ui Ui used to print statements.
     * @param ds DataStorage used to load or write data.
     * @throws DukeException If invalid task number is given in item.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage ds) throws DukeException {
        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(item);
        } catch (NumberFormatException ignored) {
        }

        if (taskNum > 0 && taskNum <= tasks.getTaskSize()) {
            tasks.getTask(taskNum - 1).markAsDone();
            ui.printDoneTask(tasks.getTask(taskNum - 1));
        } else {
            throw new DukeException().invalidTaskNumber();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
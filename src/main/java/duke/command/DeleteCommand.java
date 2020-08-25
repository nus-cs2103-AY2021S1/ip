package duke.command;

import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public final static String COMMAND = "delete";
    private int index;
    
    public DeleteCommand(String string) throws InvalidIndexException {
        try {
            index = Integer.parseInt(string) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        try {
            ui.showDeleteTask(tasks.remove(index), tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
}

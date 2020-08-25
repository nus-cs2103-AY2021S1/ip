package duke.command;

import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command{
    public final static String COMMAND = "done";
    
    private int index;

    public DoneCommand(String string) throws InvalidIndexException {
        try {
            index = Integer.parseInt(string) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        try {
            tasks.markAsDone(index);
            ui.showMarkAsDoneTask(tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
}

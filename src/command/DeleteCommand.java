package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exception.DukeException;
import exception.EmptyDeleteException;
import exception.NoSuchTaskException;

import task.Task;

import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a <code>Command</code> whose task is deleting a <code>Task</code> from the <code>TaskList</code>.
 * The <code>DeleteCommand</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String[] splitCommand) {
        super(splitCommand);
    }

    /**
     * Deletes the <code>Task</code> from <code>tasks</code> and <code>storage</code>.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     * @throws DukeException If failed to save to <code>storage</code>, no command argument provided in
     * <code>splitCommand</code>, or invalid command argument.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String oneBasedIndex = splitCommand[1];
            int zeroBasedIndex = Integer.parseInt(oneBasedIndex) - 1;
            Task toDelete = tasks.remove(zeroBasedIndex);

            ui.sayDeletedTask(toDelete, tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            ui.sayException(e);
        } catch (IndexOutOfBoundsException e) { // No description
            throw new EmptyDeleteException();
        } catch (NoSuchTaskException e) {
            ui.sayException(e);
        }
    }

    /**
     * Returns false to indicate not to exit the Duke.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}

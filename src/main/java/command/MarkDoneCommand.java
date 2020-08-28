package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exception.DukeException;
import exception.EmptyDoneException;
import exception.NoSuchTaskException;

import task.Task;

import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a <code>Command</code> whose task is marking a <code>Task</code> as done.
 * The <code>MarkDoneCommand</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public class MarkDoneCommand extends Command {

    public MarkDoneCommand(String[] splitCommand) {
        super(splitCommand);
    }

    /**
     * Marks the <code>Task</code> as done and save the changes to <code>tasks</code> and <code>storage</code>.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     * @throws DukeException If failed to save to <code>storage</code>, no command argument provided in
     * <code>splitCommand</code>, or invalid task index.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String oneBasedIndex = splitCommand[1];
            int zeroBasedIndex = Integer.parseInt(oneBasedIndex) - 1;
            Task toMark = tasks.get(zeroBasedIndex);

            toMark.markAsDone();
            ui.sayMarkedTask(toMark);
            storage.save(tasks);
        } catch (IOException e) {
            ui.sayException(e);
        } catch (IndexOutOfBoundsException e) { // No description
            throw new EmptyDoneException();
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
        } else if (o instanceof MarkDoneCommand) {
            MarkDoneCommand other = (MarkDoneCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}

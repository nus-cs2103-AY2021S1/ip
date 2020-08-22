package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.EmptyTodoException;
import task.Task;
import task.ToDo;

import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a <code>Command</code> whose task is adding a <code>ToDo</code> to the <code>TaskList</code>.
 * The <code>AddToDoCommand</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public class AddToDoCommand extends Command {

    public AddToDoCommand(String[] splitCommand) {
        super(splitCommand);
    }

    /**
     * Adds the <code>ToDo</code> to <code>tasks</code> and save the <code>ToDo</code> to
     * <code>storage</code>.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     * @throws DukeException If failed to save to <code>storage</code> or no description provided in
     * <code>splitCommand</code>.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String description = splitCommand[1];
            Task toAdd = new ToDo(description);
            tasks.add(toAdd);
            ui.sayAddedTask(toAdd, tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            ui.sayException(e);
        } catch (IndexOutOfBoundsException e) { // No description
            throw new EmptyTodoException();
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
        } else if (o instanceof AddToDoCommand) {
            AddToDoCommand other = (AddToDoCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}

package Command;

import main.java.*;

import Exception.DukeException;
import Exception.DeleteOutOfBoundException;
import Exception.DeleteUnknownException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a command to delete a task from the tasklist.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String[] command) {
        super(command);
    }

    /**
     * Delete a Task from the TaskList and save it to storage.
     * @param tasks the list of task saved.
     * @param ui deals with interaction with the user.
     * @param storage deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException if there are no value or it is not a number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Integer toBeDeleted= Integer.valueOf(command[1]);
            tasks.delete(toBeDeleted);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
            }
        } catch (DeleteOutOfBoundException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new DeleteUnknownException();
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteUnknownException();
        }

    }

    /**
     * Indicates to not exit the loop.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof DeleteCommand) {
            DeleteCommand cur = (DeleteCommand) o;
            if(Arrays.equals(this.command, cur.command)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

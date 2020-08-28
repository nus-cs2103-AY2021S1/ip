package command;

import java.io.IOException;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DoneOutOfBoundException;
import exception.DoneUnknownException;
import exception.DukeException;


/**
 * Represent a command to mark a task as done.
 */
public class DoneCommand extends Command {

    public DoneCommand(String[] command) {
        super(command);
    }

    /**
     * Marks the task as done and save it to the storage.
     * @param tasks the list of task saved.
     * @param ui deals with interaction with the user.
     * @param storage deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException if there are no value or it is not integer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Integer toBeChanged = Integer.valueOf(commands[1]);
            tasks.changeIsDone(toBeChanged);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (DoneOutOfBoundException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new DoneUnknownException();
        } catch (IndexOutOfBoundsException e) {
            throw new DoneUnknownException();
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
        } else if (o instanceof DoneCommand) {
            DoneCommand cur = (DoneCommand) o;
            if (Arrays.equals(this.commands, cur.commands)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

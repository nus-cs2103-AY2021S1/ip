package duke.command;

import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A command to mark a task as done. */
public class DoneCommand extends Command {

    /** The number of the task to be marked as done. */
    private Integer[] taskNumbers;

    /**
     * Constructs a DoneCommand.
     *
     * @param taskNumbers The number of the task to be marked as done.
     */
    public DoneCommand(Integer... taskNumbers) {
        this.taskNumbers = taskNumbers;
        isExit = false;
    }

    /**
     * Executes the command by marking the task with the given taskNo as done.
     *
     * @param taskList The task list that stores and modifies the list of saved tasks.
     * @param ui       The UI of the bot.
     * @param storage  The storage system of the bot.
     * @throws DukeException If the taskNo is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markDone(taskNumbers);
        storage.saveTasks(taskList.getTasks());
    }

    /**
     * Compares an object.
     *
     * @param o The object compared.
     * @return True if the object is of type DoneCommand and has the same taskNo.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof DoneCommand) {
            DoneCommand t = (DoneCommand) o;
            return Arrays.equals(t.taskNumbers, this.taskNumbers);
        } else {
            return false;
        }
    }
}

package duke.command;

import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A command to delete a task. */
public class DeleteCommand extends Command {

    /** The task number to be deleted. */
    private Integer[] taskNumbers;

    /**
     * Constructs a DeleteCommand.
     *
     * @param taskNumbers The task number to be deleted.
     */
    public DeleteCommand(Integer... taskNumbers) {
        this.taskNumbers = taskNumbers;
        isExit = false;
    }

    /**
     * Executes the command by deleting a task with the given taskNo.
     *
     * @param taskList The task list that stores and modifies the list of saved tasks.
     * @param ui       The UI of the bot.
     * @param storage  The storage system of the bot.
     * @throws DukeException If there is something wrong with the deleting process.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(taskNumbers);
        storage.saveTasks(taskList.getTasks());
    }

    /**
     * Compares with an object.
     *
     * @param o The object compared.
     * @return True if the object is of type DeleteCommand and has the same taskNo.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof DeleteCommand) {
            DeleteCommand t = (DeleteCommand) o;
            return Arrays.equals(t.taskNumbers, this.taskNumbers);
        } else {
            return false;
        }
    }
}

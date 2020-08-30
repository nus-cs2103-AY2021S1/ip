package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A command to delete a task. */
public class DeleteCommand extends Command {

    /** The task number to be deleted. */
    private int taskNo;

    /**
     * Constructs a DeleteCommand.
     *
     * @param taskNo The task number to be deleted.
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String deleteTask = taskList.deleteTask(taskNo - 1);
        storage.saveTasks(taskList.getTasks());
        return deleteTask;
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
            return t.taskNo == this.taskNo;
        } else {
            return false;
        }
    }
}

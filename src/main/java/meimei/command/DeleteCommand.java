package meimei.command;
import meimei.Storage;
import meimei.TaskList;
import meimei.Ui;
import meimei.botexception.BotException;
import meimei.botexception.WrongItemIndexException;
import meimei.task.Task;

/**
 * Command that deletes a task from the user's list when executed.
 */
public class DeleteCommand extends Command {
    /** A string representation of the number corresponding to the task to be deleted */
    private final String description;

    /**
     * Public constructor.
     * @param description String representation of the number
     *                    corresponding to the task to be deleted.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws BotException {
        try {
            int taskNum = Integer.parseInt(this.description);
            Task task = tasks.getTask(taskNum);
            tasks.deleteTask(taskNum, storage);

            return ui.returnReply(this, task, tasks);
        } catch (NumberFormatException e) {
            throw new WrongItemIndexException(CommandType.DELETE.toString().toLowerCase(),
                    tasks.getListLength());
        } catch (BotException botException) {
            throw botException;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

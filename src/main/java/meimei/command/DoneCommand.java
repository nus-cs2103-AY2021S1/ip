package meimei.command;

import meimei.Ui;
import meimei.Storage;
import meimei.TaskList;
import meimei.botexception.BotException;
import meimei.botexception.WrongItemIndexException;
import meimei.task.Task;

/**
 * Command that marks a task from the user's list as completed("done") when executed.
 */
public class DoneCommand extends Command {
    /** A string representation of the number corresponding to the task to be marked */
    private final String description;

    /**
     * Public constructor.
     * @param description String representation of the number
     *                    corresponding to the task to be marked.
     */
    public DoneCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws BotException {
        try {
            int taskNum = Integer.parseInt(this.description);
            assert taskNum > 0;
            Task task = tasks.getTask(taskNum);
            tasks.markDone(taskNum, storage);

            return ui.returnDoneReply(task);
        } catch (NumberFormatException e) {
            throw new WrongItemIndexException(CommandType.DONE.toString().toLowerCase(),
                    tasks.getListLength());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

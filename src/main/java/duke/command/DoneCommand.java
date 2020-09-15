package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Marks a task as done.
 */
public class DoneCommand extends Command {

    /**
     * Message to be printed when DoneCommand is executed successfully.
     */
    protected static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n%s";

    protected int index;

    /**
     * Constructs a new instance of a DoneCommand.
     * @param index Index of task to be mark as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     * @param taskList TaskList associated with command.
     * @param ui Ui associated with command.
     * @param storage Storage associated with command.
     * @throws DukeException If there is error during execution of command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task completedTask = taskList.get(index - 1);
        taskList.completeTask(index);
        storage.writeToFile(taskList);
        return ui.printReply(String.format(MESSAGE_SUCCESS, completedTask));
    }

    /**
     * Indicates whether Duke chatbot is still running.
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}

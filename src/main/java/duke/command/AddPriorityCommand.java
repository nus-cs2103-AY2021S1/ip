package duke.command;

import duke.Priority;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class AddPriorityCommand extends Command {
    /**
     * Message to be printed when AddPriorityCommand is executed successfully.
     */
    public static final String MESSAGE_SUCCESS = "Noted. I've added priority to this task:\n%s";

    protected int index;

    protected Priority priority;

    /**
     * Constructs a new instance of a AddPriorityCommand.
     * @param index Index of task to be deleted.
     * @param priority Priority of task.
     */
    public AddPriorityCommand(int index, Priority priority) {
        this.index = index;
        this.priority = priority;
    }

    /**
     * Executes the add priority command.
     * @param taskList TaskList associated with command.
     * @param ui Ui associated with command.
     * @param storage Storage associated with command.
     * @throws DukeException If there is error during execution of command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index - 1);
        task.addPriority(priority);
        storage.writeToFile(taskList);
        return ui.printReply(String.format(MESSAGE_SUCCESS, task));
    }

    /**
     * Indicates whether Duke chatbot is still running.
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}

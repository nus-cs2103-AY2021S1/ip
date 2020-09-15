package duke.command;

import duke.Action;
import duke.Priority;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class PriorityCommand extends Command {
    /**
     * Message to be printed when AddPriorityCommand is executed successfully.
     */
    protected static final String MESSAGE_SUCCESS = "Noted. I've %s priority to this task:\n%s";

    protected int index;

    protected Priority priority;

    protected Action action;

    /**
     * Constructs a new instance of a PriorityCommand.
     * @param action Action to priority
     * @param index Index of task to be deleted.
     */
    public PriorityCommand(Action action, int index) {
        this.action = action;
        this.index = index;
    }

    /**
     * Constructs a new instance of a PriorityCommand.
     * @param action Action of command
     * @param index Index of task to be deleted.
     * @param priority Priority of task.
     */
    public PriorityCommand(Action action, int index, Priority priority) {
        this.action = action;
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
        switch(this.action) {
        case ADD: {
            task.addPriority(priority);
            break;
        }
        case UPDATE: {
            task.updatePriority(priority);
            break;
        }
        case DELETE: {
            task.removePriority();
            break;
        }
        default:
            throw new DukeException("Invalid Action");
        }
        storage.writeToFile(taskList);
        return ui.printReply(String.format(MESSAGE_SUCCESS, action.toString().toLowerCase(), task));
    }

    /**
     * Indicates whether Duke chatbot is still running.
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}

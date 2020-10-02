package duke.command;

import duke.ImageType;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.NonExistentTaskException;
import duke.task.Task;

/**
 * Represents a DeleteTaskCommand.
 */
public class DeleteTaskCommand extends Command {

    /**
     * The number on the list of the task to be deleted.
     */
    protected int taskNum;

    /**
     * Creates a DeleteCommand object.
     * @param taskNum The number on the list of the task.
     */
    public DeleteTaskCommand(int taskNum) {
        super(CommandType.DELETETASK, ImageType.TICK);
        this.taskNum = taskNum;
    }

    /**
     * Executes a delete task command
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Acknowledgement of the task that was deleted.
     * @throws NonExistentTaskException For when the task number does not correspond to a task on the list.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws NonExistentTaskException {
        if (taskNum == 0 || taskNum > taskList.getTaskListSize()) {
            throw new NonExistentTaskException();
        }
        Task task = taskList.getTask(taskNum - 1);
        taskList.removeTask(taskNum - 1);
        Storage.saveTaskChanges(taskList);
        return ui.printDeleteAcknowledgement(taskList, task) + ui.printAdditionActionMessage();
    }
}

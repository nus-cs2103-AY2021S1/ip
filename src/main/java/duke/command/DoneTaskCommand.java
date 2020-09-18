package duke.command;

import duke.ImageType;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidDoneCommandException;
import duke.exception.NonExistentTaskException;

/**
 * Represents a DoneTaskCommand.
 */
public class DoneTaskCommand extends Command {

    /**
     * The number on the list of the task to be marked as done.
     */
    protected int taskNum;

    /**
     * Creates a DoneTaskCommand object.
     * @param taskNum The number on the list of the task.
     */
    public DoneTaskCommand(int taskNum) {
        super(CommandType.DONETASK, ImageType.TICK);
        this.taskNum = taskNum;
    }

    /**
     * Executes a mark as done command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Acknowledgement that the task has been marked as done.
     * @throws NonExistentTaskException For when the task number does not correspond to a task on the list.
     * @throws InvalidDoneCommandException For when the task has already been marked as done before.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws NonExistentTaskException,
            InvalidDoneCommandException {
        if (taskNum == 0 || taskNum > taskList.getTaskListSize()) {
            throw new NonExistentTaskException();
        } else if (taskList.getTask(taskNum - 1).isDone()) {
            throw new InvalidDoneCommandException();
        } else {
            taskList.getTask(taskNum - 1).markAsDone();
            Storage.saveTaskChanges(taskList);
            return ui.printDoneAcknowledgement(taskList, taskNum) + ui.printAdditionActionMessage();
        }
    }
}

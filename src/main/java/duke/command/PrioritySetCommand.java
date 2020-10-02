package duke.command;

import duke.ImageType;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.PriorityLevel;

/**
 * Represents a PrioritySetCommand.
 */
public class PrioritySetCommand extends Command {

    /**
     * Number on the list of the task to be updated.
     */
    protected int taskNum;

    /**
     * Priority level to be assigned.
     */
    protected PriorityLevel priorityLevel;

    /**
     * Creates a PrioritySetCommand.
     * @param taskNum Number on the list of the task.
     * @param priorityLevel Priority level.
     */
    public PrioritySetCommand(int taskNum, PriorityLevel priorityLevel) {
        super(CommandType.PRIORITYSET, ImageType.TICK);
        this.taskNum = taskNum;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Executes a set priority command
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Acknowledgement of the updated priority level of the task.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.setTaskPriorityLevel(this.taskNum - 1, this.priorityLevel);
        Storage.saveTaskChanges(taskList);
        return ui.printPrioritySetAcknowledgement(taskList, this.taskNum) + ui.printAdditionActionMessage();
    }
}

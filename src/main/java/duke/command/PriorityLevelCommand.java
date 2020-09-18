package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;
import duke.exception.NonExistentTaskException;

/**
 * Represents a PriorityLevelCommand.
 */
public class PriorityLevelCommand extends Command {

    /**
     * Number on the list of the task to be updated.
     */
    protected int taskNum;

    /**
     * Creates a PriorityLevelCommand object.
     * @param taskNum Number on the list of the task.
     */
    public PriorityLevelCommand(int taskNum) {
        super(CommandType.PRIORITYLEVEL, ImageType.PENDING);
        this.taskNum = taskNum;
    }

    /**
     * Executes a priority level command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Prommpt for user to enter the priority level.
     * @throws NonExistentTaskException For when the task number does not correspond to a task on the list.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws NonExistentTaskException {
        if (taskNum == 0 || taskNum > taskList.getTaskListSize()) {
            throw new NonExistentTaskException();
        }
        return ui.printPrompt("What Level do you want to set it as?\n"
                + " - High\n"
                + " - Medium\n"
                + " - Low\n");
    }

    public int getTaskNum() {
        return taskNum;
    }
}


package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.PriorityLevel;
import duke.task.Task;

public class PrioritySetCommand extends Command {
    protected int taskNum;
    protected PriorityLevel priorityLevel;

    public PrioritySetCommand(int taskNum, PriorityLevel priorityLevel) {
        super(CommandType.PRIORITYSET);
        this.taskNum = taskNum;
        this.priorityLevel = priorityLevel;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.setTaskPriorityLevel(this.taskNum - 1, this.priorityLevel);
        Storage.saveTaskChanges(taskList);
        return ui.printPrioritySetAcknowledgement(taskList, this.taskNum);
    }
}

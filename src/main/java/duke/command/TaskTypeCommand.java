package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.TaskType;

public class TaskTypeCommand extends Command {
    protected TaskType taskType;

    public TaskTypeCommand(TaskType taskType) {
        super(CommandType.TASKTYPE);
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        switch (taskType) {
        case TODO:
            return ui.printEnterTaskPrompt();
        case DEADLINE:
            return ui.printDeadlineExample();
        case EVENT:
            return ui.printEventExample();
        default:
        }
        Storage.saveTaskChanges(taskList);
        return ui.printAddAcknowledgement(taskList);
    }
}

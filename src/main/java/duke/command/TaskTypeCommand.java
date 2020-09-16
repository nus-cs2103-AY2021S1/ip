package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidTaskTypeException;
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
    public String execute(Ui ui, TaskList taskList) throws InvalidTaskTypeException {
        switch (taskType) {
        case TODO:
            return ui.printPrompt("Please enter the task");
        case DEADLINE:
            return ui.printPrompt("Please enter the task followed by the date and time of the deadline\n"
                    + "e.g., submit report, 11/10/2019 1700\n");
        case EVENT:
            return ui.printPrompt("Please enter the event followed by the date and time of the event\n"
                    + "e.g., team project meeting ,2/10/2019 1400-1600\n");
        default:
            throw new InvalidTaskTypeException();
        }
    }
}

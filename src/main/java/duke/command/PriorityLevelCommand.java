package duke.command;

import duke.ImageType;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.NonExistentTaskException;
import duke.task.Task;

public class PriorityLevelCommand extends Command {
    protected int taskNum;

    public PriorityLevelCommand(int taskNum) {
        super(CommandType.PRIORITYLEVEL, ImageType.PENDING);
        this.taskNum = taskNum;
    }

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


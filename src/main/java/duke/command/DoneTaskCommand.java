package duke.command;

import duke.ImageType;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidDoneCommandException;
import duke.exception.NonExistentTaskException;

public class DoneTaskCommand extends Command {
    protected int taskNum;

    public DoneTaskCommand(int taskNum) {
        super(CommandType.DONETASK, ImageType.TICK);
        this.taskNum = taskNum;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws NonExistentTaskException,
            InvalidDoneCommandException {
        if (taskNum > taskList.getTaskListSize()) {
            throw new NonExistentTaskException();
        } else if (taskList.getTask(taskNum - 1).isDone()) {
            throw new InvalidDoneCommandException();
        } else {
            taskList.getTask(taskNum - 1).markAsDone();
            Storage.saveTaskChanges(taskList);
            return ui.printDoneAcknowledgement(taskList, taskNum);
        }
    }
}

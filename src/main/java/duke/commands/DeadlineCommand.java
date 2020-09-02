package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeadlineCommand extends Command {
    private final boolean HAS_FINISHED = false;
    private String[] deadlineTaskDetails;

    public DeadlineCommand(String[] deadlineTaskDetails) {
        this.deadlineTaskDetails = deadlineTaskDetails;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addDeadline("0", deadlineTaskDetails[0], deadlineTaskDetails[1]);
        storage.writeToFile("D", "0", deadlineTaskDetails[0], deadlineTaskDetails[1]);
        int size = taskList.getSize();
        Task targetTask = taskList.get(size - 1);
        ui.printTaskAdded(targetTask.toString(), size);
        String messageAfterExecution = addTaskToString(targetTask, size);
        return new CommandResult(messageAfterExecution);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}

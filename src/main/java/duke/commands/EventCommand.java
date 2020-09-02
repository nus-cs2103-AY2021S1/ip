package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class EventCommand extends Command {
    private final boolean HAS_FINISHED = false;
    private String[] eventTaskDetails;

    public EventCommand(String[] eventTaskDetails) {
        this.eventTaskDetails = eventTaskDetails;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addEvent("0", eventTaskDetails[0], eventTaskDetails[1]);
        storage.writeToFile("E", "0", eventTaskDetails[0], eventTaskDetails[1]);
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

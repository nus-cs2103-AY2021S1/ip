package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class EventCommand extends Command {
    private final boolean HAS_FINISHED = false;
    private String[] eventTaskDetails;

    public EventCommand(String[] eventTaskDetails) {
        this.eventTaskDetails = eventTaskDetails;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addEvent("0", eventTaskDetails[0], eventTaskDetails[1]);
//        storage.writeToFile("E", "0", eventTaskDetails[0], eventTaskDetails[1]);
        storage.saveTasks(taskList);
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

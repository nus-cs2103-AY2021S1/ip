package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

/**
 * Encapsulates a command for Mrs Dino to add an Event task.
 */
public class EventCommand extends Command {

    /**
     * Whether this command is a terminal command.
     */
    private final boolean HAS_FINISHED = false;

    /**
     * Array containing details of the event task to add.
     * First element is the name of the task, second element is the date and time that task occurs.
     */
    private String[] eventTaskDetails;

    /**
     * Constructs a new EventCommand.
     *
     * @param eventTaskDetails Array containing details of the event task to add.
     */
    public EventCommand(String[] eventTaskDetails) {
        this.eventTaskDetails = eventTaskDetails;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addEvent("0", eventTaskDetails[0], eventTaskDetails[1]);
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

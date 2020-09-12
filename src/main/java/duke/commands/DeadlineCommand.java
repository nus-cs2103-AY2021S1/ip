package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

/**
 * Encapsulates a command for Mrs Dino to add a Deadline task.
 */
public class DeadlineCommand extends Command {

    /**
     * Whether this command is a terminal command.
     */
    private final boolean HAS_FINISHED = false;

    /**
     * Array containing details of the deadline task to add.
     * First element is the name of the task, second element is the date and time that task is due.
     */
    private String[] deadlineTaskDetails;

    /**
     * Constructs a new Deadline object.
     *
     * @param deadlineTaskDetails Array containing details of the deadline task to add.
     */
    public DeadlineCommand(String[] deadlineTaskDetails) {
        this.deadlineTaskDetails = deadlineTaskDetails;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addDeadline("0", deadlineTaskDetails[0], deadlineTaskDetails[1]);
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

package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskParser;
import duke.util.TaskList;

/**
 * Command that adds a new Task to the given TaskList and broadcasts the appropriate update.
 */
public class AddCommand extends Command {

    /** Default message to be sent when a Task is added */
    protected static final String ADDED_MESSAGE = "Got it. I've added this task: ";

    /** Task to be added when execute is performed */
    private Task task;

    /** Private constructor which is not able to handle String inputs directly. */
    private AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns an AddCommand made from the appropriate string put.
     * This is a static factory method which handles invalid formats as well.
     *
     * @param taskCommand String input of the form "add {task description}".
     * @return command object that adds the task to the task list when executed.
     * @throws DukeException if the description of the command is invalid.
     */
    public static AddCommand parse(String taskCommand) throws DukeException {
        return new AddCommand(TaskParser.parse(taskCommand));
    }

    /**
     * Adds the stored task to the taskList.
     * The method also broadcasts an update through the UI and updates the storage file.
     *
     * @param taskList List of Tasks to work with.
     * @param ui UI element to be used.
     * @param storage Storage element to be used.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
        ui.outputMessage(createAddMessage(task, taskList));
        storage.updateFile(taskList);
    }

    /**
     * Returns a formatted message for when a task is added.
     * Edit this to adjust the message sent when the command is executed.
     *
     * @param task task added.
     * @param taskList task list that the task was added to.
     * @return formatted String notifying of the update.
     */
    private String createAddMessage(Task task, TaskList taskList) {
        return ADDED_MESSAGE + '\n'
                + "   " + task + '\n'
                + taskList.createTaskNumberCountMessage();
    }

}

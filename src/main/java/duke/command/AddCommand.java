package duke.command;

import duke.storage.Storage;
import duke.util.TaskList;
import duke.task.TaskParser;
import duke.Ui;
import duke.task.Task;

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
     * Static factory method for creating the appropriate AddCommand from a String input.
     * @param taskCommand String input of the form "add {task description}"
     * @return command object that adds the task to the task list when executed
     */
    public static AddCommand parse(String taskCommand) {
        return new AddCommand(TaskParser.parse(taskCommand));
    }

    /**
     * Adds the stored task to the taskList.
     * The method also broadcasts an update through the UI and updates the storage file.
     * @param taskList List of Tasks to work with
     * @param ui UI element to be used
     * @param storage Storage element to be used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
        ui.outputMessage(createAddMessage(task, taskList));
        storage.updateFile(taskList);
    }

    /**
     * Standard String creator for the update.
     * Edit this to adjust the message sent when the command is executed.
     * @param task task added
     * @param taskList task list that the task was added to
     * @return formatted String notifying of the update
     */
    private String createAddMessage(Task task, TaskList taskList) {
        return ADDED_MESSAGE + '\n'
                + "   " + task + '\n'
                + taskList.createTaskNumberCountMessage();
    }

}

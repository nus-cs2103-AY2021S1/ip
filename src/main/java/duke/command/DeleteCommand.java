package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.tasks.Task;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE_DELETE_ACKNOWLEDGEMENT =
            "Nooo you can't take away what you've already given me...\n"
                    + "Okay fine. It's in my stomach tho... ASDFGUUVHHH!!\n"
                    + "The following has been removed: ";
    private static final String MESSAGE_DELETE_CONTINUED = "Now I'm feeling sick :( there's ";
    private static final String MESSAGE_DELETE_END = " thing(s) in my belly now...HUNGRY!";
    private int taskNum;

    /**
     * Creates a DeleteCommand instance
     *
     * @param taskNum Position of task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Remove task from current task list, edits the task file in hard disk
     * and displays acknowledgement message that task has been removed to user.
     *
     * @param tasks Task list representing current tasks.
     * @param ui User interface interacting with user.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @throws DukeException If unable to either remove task or edit task file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        Task task = tasks.removeTask(taskNum);
        storage.editTaskList("", taskNum, true);
        ui.printMessage(String.format("%s\n%s\n%s%d%s", MESSAGE_DELETE_ACKNOWLEDGEMENT,
                task.toString(), MESSAGE_DELETE_CONTINUED, tasks.getTaskCount(), MESSAGE_DELETE_END));

    }
}

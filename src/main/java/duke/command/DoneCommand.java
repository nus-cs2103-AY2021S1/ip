package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents a done command.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private static final String MESSAGE_DONE_ACKNOWLEDGEMENT = "Nice I've digested the following: ";
    private static final String MESSAGE_DONE_END = "Now I'm hungry again! FEED ME MORE :3";
    private int taskNum;

    /**
     * Creates a DoneCommand instance
     *
     * @param taskNum Position of task to be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks task in current task list done, edits the task file in hard disk
     * and displays acknowledgement message that task has been marked as done to user.
     *
     * @param tasks Task list representing current tasks.
     * @param ui User interface interacting with user.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @return A string representing Duke's response after executing command.
     * @throws DukeException If unable to edit task file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        Task task = tasks.doneTask(taskNum);
        storage.editTaskList(task.saveToString(), taskNum, false);
        return String.format("%s\n%s\n%s", MESSAGE_DONE_ACKNOWLEDGEMENT,
                task.toString(), MESSAGE_DONE_END);


    }

}

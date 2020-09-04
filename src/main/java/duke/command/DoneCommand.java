package duke.command;


import duke.TaskList;
import duke.UserInterface;
import duke.exception.DukeIndexException;

/**
 * DoneCommand class for when Done command is prompted by User
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Constructor for DoneCommand.
     *
     * @param index index of tasking in the list for which to be completed.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Execution command for Done.
     *
     * @param taskList list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @throws DukeIndexException When the input index does not match the list.
     * @return Message sent when the user completes a task.
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui) throws DukeIndexException {

        if (index > taskList.getTaskSize() - 1 || index < 0) {
            String errorMessage = "Wrong list number input. "
                    + "Please put a number between 1 and "
                    + taskList.getTaskSize();
            throw new DukeIndexException(errorMessage);
        }

        taskList.makeTaskDone(index);
        return ui.printDone(taskList.getTaskDetail(index).toString());

    }
}

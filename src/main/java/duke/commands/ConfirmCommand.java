package duke.commands;

import static duke.utils.Messages.MESSAGE_CONFIRM;

import duke.exceptions.NoSuchTaskException;
import duke.exceptions.NoSuchTentativeDateException;
import duke.tasklist.TaskList;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Represents the command that confirms a date for an event when executed.
 */
public class ConfirmCommand extends Command {

    private int taskIndex;
    private int tentativeIndex;

    /** Constructor.
     *
     * @param taskIndex The specified task index.
     * @param tentativeIndex The specified index of the date to be confirmed.
     */
    public ConfirmCommand(int taskIndex, int tentativeIndex) {
        this.taskIndex = taskIndex;
        this.tentativeIndex = tentativeIndex;
    }


    @Override
    public CommandResult execute(TaskList taskList) throws NoSuchTaskException, NoSuchTentativeDateException {
        Task task = taskList.getTaskAtIndex(taskIndex);
        try {
            Event event = (Event) task;
            event.confirm(tentativeIndex);
            return new CommandResult(String.format("%s\t\t%s",
                    MESSAGE_CONFIRM,
                    event.toString()
            ), false);
        } catch (ClassCastException e) {
            throw new NoSuchTaskException();
        }
    }
}

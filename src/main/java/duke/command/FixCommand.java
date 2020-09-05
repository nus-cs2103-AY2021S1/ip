package duke.command;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents a command for fixing events with tentative slots to one slot.
 */
public class FixCommand extends Command {

    public static final int FIX_COMMAND_VALID_LENGTH = 4;

    /**
     * Creates a fix command.
     * @param input the input command
     */
    public FixCommand(String input) {
        super(input);
    }

    /**
     * Executes the fix command.
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string telling the user about the result of the fix command
     * @throws InvalidCommandException if the input task index is invalid or the slot want to fix is not in the
     * tentative slots
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        assert input.startsWith(Parser.FIX_COMMAND_PREFIX) : "Fix command does not start with 'fix '";
        String[] inputs = input.split(Parser.SPACE_STRING);
        if (inputs.length != FIX_COMMAND_VALID_LENGTH) {
            throw new InvalidCommandException(Parser.FIX_COMMAND_FORMAT_EXCEPTION);
        }
        try {
            int n = getTaskIndex(list, inputs[1]);
            Task t = list.get(n);
            if (!(t instanceof Event)) {
                throw new InvalidCommandException(Parser.INVALID_EVENT_INDEX_EXCEPTION);
            } else {
                Event e = (Event) t;
                String output = e.fixSlot(inputs[2] + Parser.SPACE_STRING + inputs[3]);
                storage.reWrite(list);
                return output;
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Parser.NONNUMERIC_TASK_INDEX_EXCEPTION);
        }
    }

    private int getTaskIndex(TaskList list, String input) throws InvalidCommandException {
        int n = Integer.parseInt(input);
        if (n < 1) {
            throw new InvalidCommandException(Parser.NONPOSITIVE_TASK_INDEX_EXCEPTION);
        } else if (n > list.size()) {
            throw new InvalidCommandException(Parser.TASK_INDEX_OVERFLOW_EXCEPTION);
        }
        return n - 1;
    }

    /**
     * Checks whether the given command equals this one.
     * @param obj the object to compare
     * @return true if obj is a FindCommand and command input is the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof FindCommand) {
            return input.equals(((FindCommand) obj).input);
        } else {
            return false;
        }
    }
}

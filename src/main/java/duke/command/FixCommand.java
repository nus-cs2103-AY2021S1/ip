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
        assert input.startsWith("fix ") : "Fix command does not start with 'fix '";
        String[] inputs = input.split(Parser.SPACE_STRING);
        if (inputs.length != 3) {
            throw new InvalidCommandException(Parser.FIX_COMMAND_FORMAT_EXCEPTION);
        }
        try {
            int n = Integer.parseInt(inputs[1]);
            if (n < 1) {
                throw new InvalidCommandException(Parser.NONPOSITIVE_TASK_INDEX_EXCEPTION);
            } else if (n > list.size()) {
                throw new InvalidCommandException(Parser.TASK_INDEX_OVERFLOW_EXCEPTION);
            }
            Task t = list.get(n - 1);
            if (!(t instanceof Event)) {
                throw new InvalidCommandException(Parser.INVALID_EVENT_INDEX_EXCEPTION);
            } else {
                Event e = (Event) t;
                e.fixSlot(inputs[2]);
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Parser.NONNUMERIC_TASK_INDEX_EXCEPTION);
        }
        String toFind = input.substring(5);
        return ui.printList(list, t -> t.finds(toFind), String.format(Ui.FIND_LIST_NOTE_FORMAT, toFind));
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

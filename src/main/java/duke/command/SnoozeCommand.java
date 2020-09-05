package duke.command;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;
import duke.task.TimedTask;

/**
 * Represents a command for reschedule timed tasks.
 */
public class SnoozeCommand extends Command {

    public static final int SNOOZE_EVENT_COMMAND_LENGTH = 5;
    public static final int SNOOZE_DEADLINE_COMMAND_LENGTH = 4;

    /**
     * Creates a snooze command.
     * @param input the input command
     */
    public SnoozeCommand(String input) {
        super(input);
    }

    /**
     * Executes the snooze command.
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string telling the user about the result of the snoozing command
     * @throws InvalidCommandException if the input task index is invalid or the time input is invalid
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        assert input.startsWith(Parser.SNOOZE_COMMAND_PREFIX) : "Snooze command does not start with 'snooze '";
        String[] inputs = input.split(Parser.SPACE_STRING);
        if (inputs.length > SNOOZE_EVENT_COMMAND_LENGTH || inputs.length < SNOOZE_DEADLINE_COMMAND_LENGTH
                || inputs[3].equals(Parser.TO)) {
            throw new InvalidCommandException(Parser.SNOOZE_COMMAND_FORMAT_EXCEPTION);
        }
        try {
            int n = Parser.getTaskIndex(list, inputs[1]);
            Task t = list.get(n);
            if (!(t instanceof TimedTask)) {
                throw new InvalidCommandException(Parser.INVALID_TASK_TYPE_INDEX_EXCEPTION);
            } else {
                TimedTask tt = (TimedTask) t;
                String output = tt.snoozeTo(inputs);
                storage.reWrite(list);
                return output;
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Parser.NONNUMERIC_TASK_INDEX_EXCEPTION);
        }
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

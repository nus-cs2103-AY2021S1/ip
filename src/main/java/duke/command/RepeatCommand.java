package duke.command;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;
import duke.task.TimedTask;

/**
 * Represents a command for recurring tasks.
 */
public class RepeatCommand extends Command {

    public static final int REPEAT_COMMAND_CUSTOMIZED_LENGTH = 5;
    public static final int REPEAT_COMMAND_SHORTCUT_LENGTH = 3;

    /**
     * Creates a repeat command.
     * @param input the input command
     */
    public RepeatCommand(String input) {
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
        assert input.startsWith(Parser.REPEAT_COMMAND_PREFIX) : "Repeat command does not start with 'repeat '";
        String[] inputs = input.split(Parser.SPACE_STRING);
        if (inputs.length > REPEAT_COMMAND_CUSTOMIZED_LENGTH || inputs.length < REPEAT_COMMAND_SHORTCUT_LENGTH) {
            throw new InvalidCommandException(Parser.REPEAT_COMMAND_FORMAT_EXCEPTION);
        }
        try {
            int n = Parser.getTaskIndex(list, inputs[1]);
            Task t = list.get(n);
            if (!(t instanceof TimedTask)) {
                throw new InvalidCommandException(Parser.INVALID_TASK_TYPE_INDEX_EXCEPTION);
            } else {
                TimedTask tt = (TimedTask) t;
                int days = getNumberOfDays(inputs);
                String output = tt.repeat(days);
                storage.reWrite(list);
                return output;
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Parser.NONNUMERIC_TASK_INDEX_EXCEPTION);
        }
    }

    private int getNumberOfDays(String[] input) throws InvalidCommandException {
        if (input.length == RepeatCommand.REPEAT_COMMAND_SHORTCUT_LENGTH) {
            return handleShortCutRepeat(input[2]);
        } else if (input.length == REPEAT_COMMAND_CUSTOMIZED_LENGTH
                && input[2].equals(Parser.EVERY) && input[4].equals(Parser.DAYS)) {
            return handleCustomizedRepeat(input[3]);
        } else {
            throw new InvalidCommandException(Parser.REPEAT_COMMAND_FORMAT_EXCEPTION);
        }
    }

    private int handleCustomizedRepeat(String s) throws InvalidCommandException {
        try {
            int n = Integer.parseInt(s);
            if (n < 0) {
                throw new InvalidCommandException(Parser.NEGATIVE_DAYS_EXCEPTION);
            }
            return n;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Parser.NONNUMERIC_NUMBER_OF_DAYS_EXCEPTION);
        }
    }

    private int handleShortCutRepeat(String s) throws InvalidCommandException {
        if (s.equals(Parser.DAILY)) {
            return 1;
        } else if (s.equals(Parser.WEEKLY)) {
            return 7;
        } else {
            throw new InvalidCommandException(Parser.REPEAT_COMMAND_FORMAT_EXCEPTION);
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
        } else if (obj instanceof RepeatCommand) {
            return input.equals(((RepeatCommand) obj).input);
        } else {
            return false;
        }
    }
}

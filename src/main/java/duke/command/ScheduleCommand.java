package duke.command;

import duke.exceptions.DukeInvalidScheduleInputException;
import duke.messages.Output;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a command to show the schedule for a specific date.
 */
public class ScheduleCommand extends Command {

    /**
     * Class constructor.
     */
    public ScheduleCommand(String input) {
        super("schedule", input);
    }

    @Override
    public CommandResult execute(TaskList tasks, Output output, Storage storage)
            throws DukeInvalidScheduleInputException {
        return new CommandResult(output.printScheduleChatWindow(tasks.findScheduledTasks(input)));
    }

}

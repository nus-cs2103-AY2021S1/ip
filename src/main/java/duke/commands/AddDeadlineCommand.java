package duke.commands;

import java.time.LocalDateTime;

import duke.data.task.Deadline;

/**
 * Adds a Deadline to the task list.
 */
public class AddDeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to the task list.\n"
            + "\tParameters: DESCRIPTION /by DATE TIME\n"
            + "\tExample: " + COMMAND_WORD + " return book /by 23/09/2020 1800";

    protected final Deadline toAdd;

    public AddDeadlineCommand(String description, LocalDateTime deadline) {
        this.toAdd = new Deadline(description, deadline);
    }

    @Override
    public CommandResult execute() {
        taskList.add(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, taskList.size()));
    }

}

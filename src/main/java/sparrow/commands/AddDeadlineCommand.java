package sparrow.commands;

import sparrow.data.task.Deadline;

public class AddDeadlineCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "deadline";

    public static final String REQUIREMENT_MESSAGE =
            "A deadline must include a description and a date, separated by /by";

    public AddDeadlineCommand(Deadline toAdd) {
        super(toAdd);
    }
}

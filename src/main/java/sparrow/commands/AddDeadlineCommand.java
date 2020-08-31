package sparrow.commands;

import sparrow.data.task.Deadline;

public class AddDeadlineCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "deadline";

    public AddDeadlineCommand(Deadline toAdd) {
        super(toAdd);
    }
}

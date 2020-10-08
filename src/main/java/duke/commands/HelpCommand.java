package duke.commands;

import static duke.utils.Messages.MESSAGE_HELP;

import duke.tasklist.TaskList;

public class HelpCommand extends Command {
    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(MESSAGE_HELP, false);
    }
}

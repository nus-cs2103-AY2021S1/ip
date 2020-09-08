package duke.commands;

import duke.exceptions.DukeException;

import duke.task.TaskManager;

import duke.utils.Messages;

public class HelpCommand extends Command{
    @Override
    public CommandOutput executeCommand(TaskManager taskManger) throws DukeException {
        return new CommandOutput(Messages.HELP_MESSAGE, false);
    }
}

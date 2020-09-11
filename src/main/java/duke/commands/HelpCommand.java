package duke.commands;

import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * The command will output a help manual to aid the user and display all the various commands the user can use.
 */
public class HelpCommand extends Command {
    /**
     * Returns a {@code CommandOutput} object containing the help manual for users.
     *
     * @param taskManger the {@code TaskManager} which handles all the actions with regards to the {@code Task} object.
     * @return the output from executing the command.
     */
    @Override
    public CommandOutput executeCommand(TaskManager taskManger) {
        return new CommandOutput(Messages.HELP_MESSAGE, false);
    }
}

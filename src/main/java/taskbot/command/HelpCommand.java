package taskbot.command;

import taskbot.exceptions.TaskbotException;
import taskbot.task.TaskList;

import java.util.ArrayList;

/**
 * Encapsulates a command to display help information.
 */
public class HelpCommand extends Command {
    // The command to give help on.
    private String command;

    /**
     * Creates a HelpCommand.
     */
    public HelpCommand(String command) {
        super(false);
        this.command = command;
    }

    /**
     * Informs the user on how to use the help function
     * @return A string of instructions to use the command.
     */
    public static String getInstruction() throws TaskbotException {
        return "Use 'help' to learn about a specific command.\n"
                + "List of available commands: \n" + getCommandList()
                + "\nFormat: help [command]\n"
                + "command: One of the commands specified in the list, in lower case.";
    }

    /**
     * Produces the list of available commands.
     * @return a list of commands the user can use.
     */
    public static String getCommandList() throws TaskbotException {
        ArrayList<String> commands = new ArrayList<>();

        // Add every available command to the list.
        commands.add("DEADLINE");
        commands.add("DELETE");
        commands.add("DONE");
        commands.add("EVENT");
        commands.add("EXIT");
        commands.add("FIND");
        commands.add("LIST");
        commands.add("TODO");
        commands.add("UPCOMING");

        // Returns the string in format "[command1, command2, ...]"
        return commands.toString();
    }

    @Override
    public String execute(TaskList taskList) throws TaskbotException {
        return CommandHelp.getCommandInstruction(command);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        /* Check if obj is an instance of this class.
           All HelpCommand instances are equal.
         */
        return obj instanceof HelpCommand;
    }
}

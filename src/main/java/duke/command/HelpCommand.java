package duke.command;

import duke.parser.CommandFactory;

/**
 * List all available Commands
 */
public class HelpCommand implements Command {

    /**
     * List all available Commands
     */
    @Override
    public void execute() {
        System.out.println("Command list:");

        // CommandFactory is an enum of all available commands, simply print them
        for (CommandFactory cf : CommandFactory.values()) {
            System.out.print(" " + cf.toString().toLowerCase());
        }

        System.out.println();
    }

}

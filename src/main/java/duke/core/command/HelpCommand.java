package duke.core.command;

import java.util.logging.Logger;

import duke.core.parser.ParseToCommand;
import duke.designpattern.command.Executable;

/**
 * List all available Commands
 */
public class HelpCommand implements Executable {

    private static final Logger logger = Logger.getLogger(HelpCommand.class.getName());

    /**
     * List all available Commands
     */
    @Override
    public void execute() {

        logger.info(HelpCommand.class.getSimpleName() + ": Print command list");
        System.out.println("Command list:");

        // ParseToCommand is an enum of all available commands, simply print them
        for (ParseToCommand commandEnum : ParseToCommand.values()) {
            System.out.print(" " + commandEnum.toString().toLowerCase());
        }

        System.out.println();
    }

}

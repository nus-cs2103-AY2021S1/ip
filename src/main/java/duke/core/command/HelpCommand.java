package duke.core.command;

import duke.designpattern.command.Executable;
import duke.core.parser.ParseToCommand;

/**
 * List all available Commands
 */
public class HelpCommand implements Executable {

    /**
     * List all available Commands
     */
    @Override
    public void execute() {
        System.out.println("Command list:");

        // ParseToCommand is an enum of all available commands, simply print them
        for (ParseToCommand commandEnum : ParseToCommand.values()) {
            System.out.print(" " + commandEnum.toString().toLowerCase());
        }

        System.out.println();
    }

}

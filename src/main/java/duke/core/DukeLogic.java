package duke.core;

import java.util.logging.Logger;

import duke.core.parser.DukeParserException;
import duke.core.parser.ParseToCommand;
import duke.designpattern.command.CommandException;
import duke.designpattern.command.Executable;
import duke.designpattern.command.ReversibleExecutable;

/**
 * Provides the main logic for the core components of Duke
 */
public class DukeLogic {

    private static final Logger logger = Logger.getLogger(DukeLogic.class.getName());

    /**
     * Executes the user input on the data store.
     * Prints message to System.out if user input cannot be parsed.
     * @param dukeData An object which stores the data structures used by Duke
     * @param input The raw user input
     */
    public static void execute(DukeData dukeData, String input) {
        assert dukeData != null;

        logger.fine("User input: " + input);
        try {
            // Parse user input
            Executable command = ParseToCommand.parse(dukeData, input);

            // Execute command
            command.execute();

            // Add command to history
            if (command instanceof ReversibleExecutable) {
                dukeData.getHistory().add((ReversibleExecutable) command);
                logger.fine("Add " + command.getClass().getSimpleName() + " to history");
            }

        } catch (DukeParserException | CommandException e) {
            // DukeParserException: Input cannot be parsed
            // CommandException: Command cannot be executed
            System.err.println(e.getMessage());
            logger.fine("Duke error output: " + e.getMessage());
        }

    }

}

package duke.core;

import duke.core.parser.DukeParserException;
import duke.core.parser.Parser;
import duke.designpattern.command.Executable;
import duke.designpattern.command.ReversibleExecutable;

/**
 * Provides the main logic for the core components of Duke
 */
public class Logic {

    /**
     * Executes the user input.
     * First parses the user input into command, executes the command,
     * add command into history if applicable, and print message
     * to System.out if user input cannot be parsed
     * @param dataStore An object which stores all data structures used by Duke
     * @param input The raw user input
     */
    public static void execute(DataStore dataStore, String input) {
        try {
            // Parse user input
            Executable executable = Parser.parse(dataStore, input);

            // Execute command
            executable.execute();

            // Add command to history
            if (executable instanceof ReversibleExecutable) {
                dataStore.getHistory().add((ReversibleExecutable) executable);
            }

        } catch (DukeParserException e) {
            // Input cannot be parsed, notify user
            System.out.println(e.getMessage());
        }
    }

}

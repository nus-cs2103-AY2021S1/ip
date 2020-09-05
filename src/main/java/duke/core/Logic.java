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
     * Executes the user input on the data store.
     * Prints message to System.out if user input cannot be parsed.
     * @param dataStore An object which stores the data structures used by Duke
     * @param input The raw user input
     */
    public static void execute(DataStore dataStore, String input) {
        try {
            // Parse user input
            Executable command = Parser.parse(dataStore, input);

            // Execute command
            command.execute();

            // Add command to history
            if (command instanceof ReversibleExecutable) {
                ReversibleExecutable reversibleCommand = (ReversibleExecutable) command;
                dataStore.getHistory().add(reversibleCommand);
            }

        } catch (DukeParserException e) { // Input cannot be parsed, notify user
            System.out.println(e.getMessage());
        }
    }

}

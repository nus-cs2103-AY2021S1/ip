package duke.cmd;

import duke.core.DataStore;
import duke.core.command.ExitCommand;
import duke.core.parser.DukeParserException;
import duke.core.parser.Parser;
import duke.designpattern.command.Executable;
import duke.designpattern.command.ReversibleExecutable;

import java.util.Scanner;

/**
 * Command line application for Duke (Command line UI)
 */
public class Duke {

    private static final String LOGO = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke\nWhat can i do for you?";
    private static final String ENDING_GREETING = "Bye. Hope to see you again soon!";

    private final DataStore dataStore;
    private final Scanner scanner;

    /**
     * Initialize Duke cmd with default dataStore and default input/output
     */
    public Duke() {
        this.dataStore = new DataStore();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main execution point for Duke commandline
     * Deals with main logic and IO
     */
    public void run() {

        // Print greetings
        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETING);

        // Loop until 'bye' input received
        while (true) {
            Executable executable;

            // Prompt for input
            String input = this.scanner.nextLine();
            if (input.isBlank()) {
                continue;
            }

            try {
                // Attempt to parse input
                executable = Parser.parse(this.dataStore, input);
                if (executable instanceof ReversibleExecutable) {
                    this.dataStore.getHistory().add((ReversibleExecutable) executable);
                }
            } catch (DukeParserException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // Check for exit command
            if (executable instanceof ExitCommand) {
                break;
            }

            // Execute command
            executable.execute();

        }

        // Print ending greetings
        System.out.println(ENDING_GREETING);
    }

}

package duke.cmd;

import java.util.Scanner;

import duke.core.DukeData;
import duke.core.DukeLogic;

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

    private final DukeData dukeData;
    private final Scanner scanner;

    /**
     * Initialize Duke cmd with default dataStore and default input/output
     */
    public Duke() {
        this.dukeData = new DukeData();
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

        while (true) {
            // Get input
            String input = this.scanner.nextLine();

            // Execute logic
            DukeLogic.execute(this.dukeData, input);

            // Post processing
            // Nothing to do
        }

    }

}

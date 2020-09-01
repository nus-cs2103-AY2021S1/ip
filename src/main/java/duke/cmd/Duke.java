package duke.cmd;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.parser.DukeParserException;
import duke.parser.Parser;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
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

    private final List<Task> taskList;
    private final Scanner scanner;

    /**
     * Initialize Duke cmd with default array size of 100 and default input/output
     */
    public Duke() {
        this.taskList = new ArrayList<>(100);
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
            Command command;

            // Prompt for input
            String input = scanner.nextLine();
            if (input.isBlank()) {
                continue;
            }

            try {
                // Attempt to parse input
                command = Parser.parse(taskList, input);
            } catch (DukeParserException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // Check for exit command
            if (command instanceof ExitCommand) {
                break;
            }

            // Execute command
            command.execute();

        }

        // Print ending greetings
        System.out.println(ENDING_GREETING);
    }

}

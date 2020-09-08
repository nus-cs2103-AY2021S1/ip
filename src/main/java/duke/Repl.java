package duke;

import java.util.Scanner;

import duke.enums.Command;
import duke.exceptions.DukeException;
import duke.messages.DukeResponse;
import duke.utils.PrettyPrinter;
import duke.utils.ResourceHandler;

/**
 * A read-eval-print loop (REPL) that reads in a command from the user, executes it, and prints out the result.
 */
public class Repl {
    // Formatting
    /** Number of spaces to prefix each line with. */
    private static final int LEFT_PADDING_SIZE = 4;
    /** Number of underscores each divider should be made up of. */
    private static final int DIVIDER_LENGTH = 60;

    /** {@code Scanner} object which reads in user input. */
    private static final Scanner scanner = new Scanner(System.in);
    /** {@code PrettyPrinter} object for formatting the REPL output. */
    private static final PrettyPrinter prettyPrinter = new PrettyPrinter(LEFT_PADDING_SIZE, DIVIDER_LENGTH);

    /**
     * Runs the REPL.
     */
    public static void run() {
        prettyPrinter.print(ResourceHandler.getString("repl.greeting"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            DukeResponse dukeResponse = getResponse(line);
            prettyPrinter.print(dukeResponse.toString());
            if (dukeResponse.shouldExit()) {
                break;
            }
        }
    }

    /**
     * Processes the user's input and returns a {@code DukeResponse}.
     *
     * @param input the user's input.
     * @return a {@code DukeResponse}.
     */
    public static DukeResponse getResponse(String input) {
        String firstToken = input.split(" ")[0];
        DukeResponse dukeResponse;
        try {
            Command command = Command.valueOf(firstToken.toUpperCase());
            // Check that the user input is of the correct format for the command.
            command.validate(input);
            // Execute the command.
            dukeResponse = command.execute(input);
        } catch (DukeException e) {
            dukeResponse = new DukeResponse(e.getMessage());
        } catch (IllegalArgumentException e) {
            dukeResponse = new DukeResponse(ResourceHandler.getString("repl.unknownCommand"));
        }
        return dukeResponse;
    }
}

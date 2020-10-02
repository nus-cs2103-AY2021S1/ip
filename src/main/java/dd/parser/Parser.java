package dd.parser;

import dd.commands.AddCommand;
import dd.commands.Command;
import dd.commands.DeleteCommand;
import dd.commands.DoneCommand;
import dd.commands.ExitCommand;
import dd.commands.HelpCommand;
import dd.commands.ListCommand;
import dd.exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * A parser makes sense of the user input and
 * executes the next appropriate command.
 */
public class Parser {

    /**
     * Parses and returns a appropriate command based on input given by user.
     *
     * @param input Input given by user.
     * @return A Command based on the input given.
     * @throws DukeException If an empty item description is given,
     * or if an invalid command is given.
     */
    public static Command parse(String input) throws DukeException {
        Command c;

        boolean isQueryInput = input.startsWith("check") || input.startsWith("find");
        boolean isListCommand = input.equals("list") || isQueryInput;

        boolean isAddTodo = input.startsWith("todo");
        boolean isAddDeadline = input.startsWith("deadline");
        boolean isAddEvent = input.startsWith("event");
        boolean isAddCommand = isAddTodo || isAddDeadline || isAddEvent;

        if (isListCommand) {
            c = parseListInput(input);
        } else if (isAddCommand) {
            c = parseAddInput(input);
        } else if (input.startsWith("done")) {
            c = parseDoneInput(input);
        } else if (input.startsWith("delete")) {
            c = parseDeleteInput(input);
        } else if (input.equals("bye")) {
            c = parseExit();
        } else if (input.equals("help")) {
            c = new HelpCommand("help", "");
        } else {
            throw new DukeException("Invalid command, I don't understand :(\n"
                    + "Type 'help' for a list of possible commands.");
        }
        return c;
    }

    private static Command parseListInput(String input) throws DukeException {
        Command c = null;

        if (input.equals("list")) {
            // list command
            c = new ListCommand("list", "");
        } else if (input.startsWith("check")) {
            // check list command
            if (input.length() < 6) {
                throw new DukeException("Query date cannot be empty!");
            } else {
                c = new ListCommand("check", input.substring(6));
            }
        } else if (input.startsWith("find")) {
            // list find command
            if (input.length() < 5) {
                throw new DukeException("Query description cannot be empty!");
            } else {
                c = new ListCommand("find", input.substring(5));
            }
        }
        return c;
    }

    private static Command parseAddInput(String input) throws DukeException {
        Command c = null;

        // Check for commas to prevent load file bug
        if (input.contains(",")) {
            throw new DukeException("Item name cannot contain commas( , )!");
        } else if (input.startsWith("todo")) {
            if (input.length() < 5) {
                throw new DukeException("To-do item cannot be empty! e.g. todo borrow book");
            } else {
                c = new AddCommand("todo", input.substring(5));
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() < 9) {
                throw new DukeException("Deadline item cannot be empty!");
            } else {
                c = new AddCommand("deadline", input.substring(9));
            }
        } else if (input.startsWith("event")) {
            if (input.length() < 6) {
                throw new DukeException("Event item cannot be empty!");
            } else {
                c = new AddCommand("event", input.substring(6));
            }
        }
        return c;
    }

    private static Command parseDoneInput(String input) throws DukeException {
        Command c = null;

        if (input.length() < 5) {
            throw new DukeException("Item number cannot be empty!");
        } else {
            c = new DoneCommand("done", input.substring(5));
        }
        return c;
    }

    private static Command parseDeleteInput(String input) throws DukeException {
        Command c = null;

        if (input.length() < 7) {
            throw new DukeException("Item number cannot be empty!");
        } else {
            c = new DeleteCommand("delete", input.substring(7));
        }
        return c;
    }

    private static Command parseExit() {
        Command c = null;
        c = new ExitCommand("exit", "");

        // delay 2 seconds before closing application to display bye greeting first
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return c;
    }

}

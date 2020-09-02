package duke.main;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

import duke.exception.DeadlineIncompleteException;
import duke.exception.DeleteIncompleteException;
import duke.exception.DeleteOutOfListException;
import duke.exception.DoneIncompleteException;
import duke.exception.DoneOutOfListException;
import duke.exception.DukeException;
import duke.exception.EventIncompleteException;
import duke.exception.FindIncompleteException;
import duke.exception.NoInputException;
import duke.exception.TodoIncompleteException;
import duke.exception.UnknownInputException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parser is used to make sense the input from the user and convert it to executable command.
 */
public class Parser {

    public static String[] commands = {"list", "done", "deadline", "event", "todo", "delete", "bye", "find"};
    public static ArrayList<String> VALID_COMMAND = new ArrayList<>(Arrays.asList(commands));

    /**
     * Checks the correctness of the input by the user.
     *
     * @param input The input from the user.
     * @throws DukeException If user's input is invalid.
     */
    public static void checkInput(String ... input) throws DukeException {
        String command = input[0];
        System.out.println(command);
        if (command.equals("")) {
            throw new NoInputException();
        } else if (!VALID_COMMAND.contains(input[0])) {
            throw new UnknownInputException();
        } else if (input.length == 1) {
            switch (command) {
                case "done":
                    throw new DoneIncompleteException();
                case "deadline":
                    throw new DeadlineIncompleteException();
                case "event":
                    throw new EventIncompleteException();
                case "todo":
                    throw new TodoIncompleteException();
                case "delete":
                    throw new DeleteIncompleteException();
                case "find":
                    throw new FindIncompleteException();
            }
        } else if (command.equals("done")) {
            if (Integer.parseInt(input[1]) < 1) {
                throw new DoneOutOfListException();
            }
        } else if (command.equals("delete")) {
            if (Integer.parseInt(input[1]) < 1) {
                throw new DeleteOutOfListException();
            }
        }
    }

    /**
     * Translates user input into executable Command.
     *
     * @param c The input from the user.
     * @return Command that will be executed.
     * @throws DukeException If user's input is invalid.
     */
    public static Command parse(String ... c) throws DukeException {
        // Check command input
        checkInput(c);
        String commandType = c[0];
        switch (commandType) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done": {
            String taskNumber = c[1];
            return new DoneCommand(taskNumber);
        }
        case "delete": {
            String taskNumber = c[1];
            return new DeleteCommand(taskNumber);
        }
        case "todo": {
            StringBuilder description = new StringBuilder();
            for (int i = 1; i < c.length; i++) {
                if (description.length() > 0) {
                    description.append(" ");
                }
                description.append(c[i]);
            }
            return new TodoCommand(description.toString());
        }
        case "deadline": {
            StringBuilder description = new StringBuilder();
            StringBuilder date = new StringBuilder();
            boolean shouldTake = false;
            for (int i = 1; i < c.length; i++) {
                if (c[i].equals("/by")) {
                    shouldTake = true;
                    continue;
                }
                if (shouldTake) {
                    date.append(c[i]);
                } else {
                    if (description.length() > 0) {
                        description.append(" ");
                    }
                    description.append(c[i]);
                }
            }
            return new DeadlineCommand(description.toString(), date.toString());
        }
        case "find": {
            return new FindCommand(c[1]);
        }
        default: {
            StringBuilder description = new StringBuilder();
            StringBuilder date = new StringBuilder();
            boolean shouldTake = false;
            for (int i = 1; i < c.length; i++) {
                if (c[i].equals("/at")) {
                    shouldTake = true;
                    continue;
                }
                if (shouldTake) {
                    date.append(c[i]);
                } else {
                    if (description.length() > 0) {
                        description.append(" ");
                    }
                    description.append(c[i]);
                }
            }
            return new EventCommand(description.toString(), date.toString());
        }
        }
    }
}

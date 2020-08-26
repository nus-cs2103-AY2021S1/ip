package main.java;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    public static String[] commands = {"list", "done", "deadline", "event", "todo", "delete", "bye"};
    public static ArrayList<String> VALID_COMMAND = new ArrayList<>(Arrays.asList(commands));

    // Method to check user input
    public static void checkInput(String line) throws DukeException {
        String[] input = line.split(" ");
        String command = input[0];
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

    public static Command parse(String c) throws DukeException {
        // Check command input
        checkInput(c);
        String[] s = c.split(" ");
        String commandType = s[0];
        switch (commandType) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done": {
            String taskNumber = s[1];
            return new DoneCommand(taskNumber);
        }
        case "delete": {
            String taskNumber = s[1];
            return new DeleteCommand(taskNumber);
        }
        case "todo":
            String taskDescription = c.substring(5);
            return new TodoCommand(taskDescription);
        case "deadline": {
            // Split string to get date
            String[] str = c.split(" /by ");
            // Ignore task type
            String description = str[0].substring(9);
            String date = str[1];
            return new DeadlineCommand(description, date);
        }
        default: {
            // Split string to get date
            String[] str = c.split(" /at ");
            // Ignore task type
            String description = str[0].substring(6);
            String date = str[1];
            return new EventCommand(description, date);
            }
        }
    }
}

package duke.util;

import duke.commands.*;

import java.util.Scanner;

/**
 * Handles the user input and determine the correct commands.Command to activate.
 * Contains helper methods to help out with this parsing process.
 */
public class Parser {

    /**
     * Parse the user input and determine the correct commands.Command to activate.
     * @param inputLine User input line string
     * @return commands.Command that correspond to the input.
     * @throws DukeException If the input is not recognised.
     */
    public static Command parse(String inputLine) throws DukeException {
        Scanner scanner = new Scanner(inputLine);
        String action = scanner.next();
        int index;
        String remainingString;
        String[] parsedStrings;

        switch (action) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "todo":
            case "deadline":
            case "event":
                remainingString = scanner.nextLine();
                parsedStrings = parseAddCommandString(action, remainingString);
                return new AddCommand(parsedStrings);
            case "priority":
                remainingString = scanner.nextLine();
                Object[] parsedObjects = parsePriorityCommandString(remainingString);
                int taskIndex = (int) parsedObjects[0];
                String priorityLevel = (String) parsedObjects[1];
                return new PriorityCommand(taskIndex, priorityLevel);

            case "find":
                remainingString = scanner.nextLine();
                return new FindCommand(remainingString);
            case "done":
                index = scanner.nextInt() - 1;
                return new DoneCommand(index);
            case "delete":
                index = scanner.nextInt() - 1;
                return new DeleteCommand(index);
            default:
                throw new DukeException("Sorry, I'm not sure what that means :(");
        }
    }

    /**
     * Helper method to parse the remaining string from the input line for new Task.
     * @param taskType Type of task.
     * @param remainingString String that contains the description (and due).
     * @throws DukeException
     */
    public static String[] parseAddCommandString(String taskType, String remainingString) throws DukeException {
        if (remainingString.length() == 0) {
            throw new DukeException("The description can't be empty.");
        }

        String description;
        String due;
        String[] splitStrings;

        switch (taskType) {
            case "todo":
                description = remainingString.trim();
                return new String[] {"todo", description};
            case "deadline":
                splitStrings = remainingString.split("/by");
                description = splitStrings[0].trim();
                due = splitStrings[1].trim();
                if (description.length() == 0 || due.length() == 0) {
                    throw new DukeException("Description or due date is empty.");
                }
                return new String[] {"deadline", description, due};
            case "event":
                splitStrings = remainingString.split("/at");
                description = splitStrings[0].trim();
                due = splitStrings[1].trim();
                if (description.length() == 0 || due.length() == 0) {
                    throw new DukeException("Description or due date is empty.");
                }
                return new String[] {"event", description, due};
            default:
                throw new DukeException("I don't know what type of task this is :(");

        }
    }


    public static Object[] parsePriorityCommandString(String remainingString) throws DukeException {
        String[] splitStrings = remainingString.trim().split(" ");

        try {
            int taskIndex = Integer.valueOf(splitStrings[0]) - 1;
            String priorityString = splitStrings[1];
            return new Object[] {taskIndex, priorityString};
        } catch (NumberFormatException e) {
            throw new DukeException("I don't know which task index are you referring to :(");
        }
    }

}

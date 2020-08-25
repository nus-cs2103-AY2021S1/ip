package main.java.duke;

import main.java.duke.command.Command;
import main.java.duke.command.CompleteTaskCommand;
import main.java.duke.command.CreateDeadlineCommand;
import main.java.duke.command.CreateEventCommand;
import main.java.duke.command.CreateTodoCommand;
import main.java.duke.command.DeleteTaskCommand;
import main.java.duke.command.ExitCommand;
import main.java.duke.command.InvalidInputCommand;
import main.java.duke.command.ListTasksCommand;

import java.time.LocalDate;

/**
 * Encapsulates a parser to read user inputs in duke and determine the subsequent actions
 */
class Parser {

    /**
     * Symbol used to separate values in a date
     * "-" is used by LocalDate.toString()
     */
    private static String dateSeparator = "-";

    /**
     * Parses the given input from the user
     * @param input Input from user
     * @return Command corresponding to the input
     */
    static Command parse(String input) {

        String[] parsedCommand = input.split(" ", 2);
        String prefix = parsedCommand[0];
        String body = null;
        if (parsedCommand.length == 2) {
            body = parsedCommand[1];
        }

        switch(prefix) {
        case("bye"):
            return new ExitCommand();
        case("deadline"):
            try {
                String[] parsedDeadline = body.split(" /by ", 2);
                LocalDate date = Parser.genDate(parsedDeadline[1]);
                return new CreateDeadlineCommand(parsedDeadline[0], false, date);
            } catch (NullPointerException | NumberFormatException ignored) {
                // return invalid command
            }
            break;
        case("delete"):
            try {
                int taskIndex = Integer.parseInt(body);
                return new DeleteTaskCommand(taskIndex - 1);
            } catch (NumberFormatException ignored) {
                // return invalid command
            }
            break;
        case("done"):
            try {
                int taskIndex = Integer.parseInt(body);
                return new CompleteTaskCommand(taskIndex - 1);
            } catch (NumberFormatException ignored) {
                // return invalid command
            }
            break;
        case("event"):
            try {
                String[] parsedEvent = body.split(" /at ", 2);
                LocalDate date = Parser.genDate(parsedEvent[1]);
                return new CreateEventCommand(parsedEvent[0], false, date);
            } catch (NullPointerException | NumberFormatException ignored) {
                // return invalid command
            }
            break;
        case("list"):
            return new ListTasksCommand();
        case("todo"):
            return new CreateTodoCommand(body, false);
        }

        return new InvalidInputCommand();
    }

    /**
     * Converts a string representation of a date to a LocalDate
     * @param input String representation of a date
     * @return LocalDate of the date
     * @throws NumberFormatException Exception thrown if the string cannot be parsed into integers
     */
    static LocalDate genDate(String input) throws NumberFormatException {
        String[] strings = input.split(Parser.dateSeparator, 3);
        int[] ints = new int[3];
        for(int i = 0; i < 3; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return LocalDate.of(ints[0], ints[1], ints[2]);
    }
}

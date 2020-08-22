package main.java.duke.core;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import main.java.duke.command.Command;
import main.java.duke.command.SearchCommand;
import main.java.duke.command.AddCommand;
import main.java.duke.command.DeleteCommand;
import main.java.duke.command.DoneCommand;
import main.java.duke.command.ExitCommand;
import main.java.duke.command.ListCommand;
import main.java.duke.command.ListDateCommand;
import main.java.duke.command.CommandType;
import main.java.duke.task.*;
import main.java.duke.handle.*;

/**
 * The Parser class parses the command entered by
 * the user and returns the parsed command with
 * the corresponding command type and parameters.
 */
public class Parser {

    /**
     * Parses the command by reading the type and calling
     * the corresponding method to read the parameters of
     * the command, and returns the parsed command.
     *
     * @param command The command to be parsed
     * @return The parsed command with the corresponding type and parameter.
     * @throws CommandNotFoundException If the command cannot be parsed.
     */
    public static Command parseCommand(String command) throws CommandNotFoundException {
        Command parsedCommand;
        //HashMap<String, Object> map = new HashMap<>();
        //map.put("type", "add");
        command = command.strip();
        if (command.equals("bye")) {
            parsedCommand = new ExitCommand();
        } else if (command.equals("list")) {
            parsedCommand = new ListCommand();
        } else if (command.split(" ")[0].equals("list")) {
            parsedCommand = evaluateListDate(command);
        } else if (command.split(" ")[0].equals("done")) {
            //evaluateCompleteOrDelete(map, command, Command.DONE);
            parsedCommand = evaluateCompleteOrDelete(command, CommandType.DONE);
        } else if (command.split(" ")[0].equals("delete")) {
            parsedCommand = evaluateCompleteOrDelete(command, CommandType.DELETE);
        }  else if (command.split(" ")[0].equals("todo")) {
            parsedCommand = evaluateAddCommand("", command, CommandType.ADD_TODO);
        } else if (command.split(" ")[0].equals("deadline")) {
            parsedCommand = evaluateAddCommand(" /by ", command, CommandType.ADD_DEADLINE);
        } else if (command.split(" ")[0].equals("event")) {
            parsedCommand = evaluateAddCommand(" /at ", command, CommandType.ADD_EVENT);
        } else if (command.split(" ")[0].equals("find")) {
            parsedCommand = evaluateSearchCommand(command);
        } else {
            throw new CommandNotFoundException("The command is not found");
        }
        return parsedCommand;
    }

    /**
     * Parses the add command.
     *
     * @param string The time notation used to separate the description from the time.
     * @param command The command to be parsed.
     * @param commandType The type of the command.
     * @return The parsed command.
     * @throws CommandNotFoundException If the command cannot be parsed.
     */
    public static Command evaluateAddCommand(String string, String command, CommandType commandType) throws CommandNotFoundException {
        if(commandType == CommandType.ADD_TODO) {

            if (command.strip().split(" ").length == 1) {
                throw new CommandNotFoundException("The description for todo should not be empty");
            } else {
                return new AddCommand(new ToDo(command.split("\\s+", 2)[1]));
            }
        } else if (command.split(" ").length == 1) {
            throw new CommandNotFoundException("The description should not be empty");
        } else if (command.split(" ")[0].equals("event") || command.split(" ")[0].equals("deadline")) {
            String content = (command.split("\\s+", 2)[1]);

            if (content.strip().split(string).length <= 1) {
                throw new CommandNotFoundException("The description should be in the format:\n" + command.split(" ")[0] + " description" + string + "time");
            } else {
                try {
                    String time;
                    String description = content.split(string)[0];
                    time = content.strip().split(string, 2)[1];
                    LocalDate localDate = LocalDate.parse(time.strip());

                    if(commandType == CommandType.ADD_DEADLINE) {
                        return new AddCommand(new Deadline(description, localDate));
                    } else if (commandType == CommandType.ADD_EVENT) {
                        return new AddCommand(new Event(description, localDate));
                    } else {
                        throw new CommandNotFoundException("The command is not found");
                    }
                    //map.put("time", localDate);
                } catch (DateTimeParseException dateTimeParseExeption) {
                    throw new CommandNotFoundException("the time should be in the format yyyy-MM-dd");
                }
            }
        } else {
            throw new CommandNotFoundException("The command is not found");
        }
    }

    /**
     * Parses the complete command or the delete command.
     *
     * @param command The command to be parsed.
     * @param commandType The type of the command.
     * @return The parsed command.
     * @throws CommandNotFoundException If the command cannot be parsed.
     */
    public static Command evaluateCompleteOrDelete(String command, CommandType commandType) throws CommandNotFoundException {
        String type = command.split(" ")[0];
        //System.out.println(type);
        try {

            if(command.split(type).length == 0) {
                throw new CommandNotFoundException("The " + type + " command needs to contain the number label of the task to be completed");
            }
            int number = Integer.parseInt(command.split("\\s+", 2)[1]);

            if(commandType == CommandType.DONE) {
                return new DoneCommand(number);
            } else {
                return new DeleteCommand(number);
            }
        } catch (NumberFormatException exception) {
            throw new CommandNotFoundException("The " + type + " command should be in the format:\n" + type + " number label of the task to be completed");
        }
    }

    /**
     * Parses the list date command.
     *
     * @param command The command to be parsed.
     * @return The parsed command.
     * @throws CommandNotFoundException If the command cannot be parsed.
     */
    public static Command evaluateListDate(String command) throws CommandNotFoundException {
        try {
            if (command.split("\\s+").length != 2) {
                throw new CommandNotFoundException("Type list to list all the tasks. Type list yyyy-MM-dd to list the tasks on a specific date");
            } else {
                LocalDate localDate = LocalDate.parse(command.split("\\s+")[1]);
                return new ListDateCommand(localDate);
            }
        } catch (DateTimeParseException dateTimeParseException) {
            throw new CommandNotFoundException("Type list yyyy-MM-dd to list the tasks on a specific date");
        }
    }

    /**
     * Parses the search command.
     *
     * @param command The command to be parsed.
     * @return The parsed command.
     * @throws CommandNotFoundException If the command cannot be parsed.
     */
    public static Command evaluateSearchCommand(String command) {
        String key = command.split("\\s+", 2)[1];
        return new SearchCommand(key);
    }
}

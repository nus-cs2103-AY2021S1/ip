package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import command.ByeCommand;
import command.Command;
import command.CreateDeadLineTaskCommand;
import command.CreateEventTaskCommand;
import command.CreateNewAliasCommand;
import command.CreateTodoTaskCommand;
import command.DelAliasCommand;
import command.DelTaskCommand;
import command.FindTaskByDateCommand;
import command.FindTaskByKeywordCommand;
import command.GetTaskListCommand;
import command.InvalidCommand;
import command.MarkTaskDoneCommand;

/**
 * The Parser class to handle the parsing of user inputs to the appropriate commands.
 *
 * @author  Ryan Lim
 */
public class Parser {
    private final HashMap<String, String> aliasToCommandMap;

    /**
     * Constructor for parser with custom alias as commands
     * @param aliases the file containing the alias to command mapping
     * @throws IOException
     */
    public Parser(File aliases) throws IOException {
        this.aliasToCommandMap = new HashMap<>();
        FileReader fr = new FileReader(aliases); //reads the file
        BufferedReader br = new BufferedReader(fr); //creates a buffering character input stream
        String line = br.readLine();
        while (line != null) {
            populateMap(line);
            line = br.readLine();
        }
        fr.close();
    }

    /**
     * Constructor for parser with no existing aliases
     */
    public Parser() {
        this.aliasToCommandMap = new HashMap<>();
    }

    /**
     * Loads any pre-existing aliases stored in the hard drive.
     * @param aliasCommandPair The alias command pair obtained from the hard drive.
     */
    private void populateMap(String aliasCommandPair) {
        String[] aliasAndCommand = aliasCommandPair.split("\\|");
        String alias = aliasAndCommand[0];
        String command = aliasAndCommand[1];
        aliasToCommandMap.put(alias, command);
    }

    private boolean aliasDoesNotExist(String alias) {
        return this.aliasToCommandMap.get(alias) == null;
    }

    /**
     * Creates a new alias to command mapping
     *
     * @param parameters The alias and the command to map to
     * @return the alias to command mapping in string format
     * @throws DukeExceptions.AliasAlreadyExistException
     */
    public String createNewAlias(String ...parameters) throws DukeExceptions.AliasAlreadyExistException {
        String alias = parameters[0].toLowerCase();
        String command = parameters[1].toLowerCase();
        if (aliasDoesNotExist(alias)) {
            this.aliasToCommandMap.put(alias, command);
            return alias + " -> " + command;
        } else {
            throw new DukeExceptions.AliasAlreadyExistException(alias);
        }
    }

    /**
     * Deletes the specified alias to command mapping based on the alias.
     *
     * @param parameters The alias to delete.
     * @return The alias itself when it is deleted successfully
     * @throws DukeExceptions.AliasDoesNotExistException
     */
    public String deleteAlias(String ...parameters) throws DukeExceptions.AliasDoesNotExistException {
        String alias = parameters[0].toLowerCase();
        if (aliasDoesNotExist(alias)) {
            throw new DukeExceptions.AliasDoesNotExistException(alias);
        } else {
            this.aliasToCommandMap.remove(alias);
            return alias;
        }
    }

    /**
     * To map the alias to a command if any.
     *
     * @param alias The alias input by the user.
     * @return the string representing the command, the alias itself if there is no command mapping to the alias
     */
    private String getCommandFrom(String alias) {
        String command = aliasToCommandMap.get(alias);
        return command == null ? alias : command;
    }

    public HashMap<String, String> getAliasToCommandMap() {
        return this.aliasToCommandMap;
    }

    /**
     *  returns the command (and its parameters) based on the user input that has been parsed
     *
     * @param input user input
     * @return Command based on user input
     * @throws IllegalArgumentException if user input does not match the appropriate commands
     */
    public Command parse(String input) {
        assert input.length() > 0 : "no input given";
        Scanner sc = new Scanner(input);
        String alias = sc.next();
        String parameters = input.replace(alias, "");
        switch (this.getCommandFrom(alias.toLowerCase())) {
        case "list":
            return new GetTaskListCommand();
        case "date":
            return new FindTaskByDateCommand(parameters.strip());
        case "done":
            return new MarkTaskDoneCommand(parameters.strip());
        case "delete":
            return new DelTaskCommand(parameters.strip());
        case "event":
            return new CreateEventTaskCommand(parameters.strip().split("/at"));
        case "deadline" :
            return new CreateDeadLineTaskCommand(parameters.strip().split("/by"));
        case "todo":
            return new CreateTodoTaskCommand(parameters);
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindTaskByKeywordCommand(parameters.strip());
        case "alias":
            return new CreateNewAliasCommand(parameters.strip().split(" "));
        case "deletealias":
            return new DelAliasCommand(parameters.strip().split(" "));
        default:
            return new InvalidCommand();
        }
    }

}

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
import command.CreateTodoTaskCommand;
import command.DelTaskCommand;
import command.FindTaskByDateCommand;
import command.FindTaskByKeywordCommand;
import command.GetTaskListCommand;
import command.MarkTaskDoneCommand;

/**
 * The Parser class to handle the parsing of user inputs to the appropriate commands.
 *
 * @author  Ryan Lim
 */
public class Parser {
    private HashMap<String, String> aliasToCommandMap;

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
            String[] aliasAndCommand = line.split("\\|");
            String alias = aliasAndCommand[0];
            String command = aliasAndCommand[1];
            line = br.readLine();
            aliasToCommandMap.put(alias, command);
        }
        fr.close();
    }

    public Parser() {
        this.aliasToCommandMap = new HashMap<>();
    }

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
    public Command parse(String input) throws IllegalArgumentException {
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
            return new CreateEventTaskCommand(parameters.split("/at"));
        case "deadline" :
            return new CreateDeadLineTaskCommand(parameters.split("/by"));
        case "todo":
            return new CreateTodoTaskCommand(parameters);
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindTaskByKeywordCommand(sc.nextLine().strip());
        default:
            throw new IllegalArgumentException();
        }
    }

}

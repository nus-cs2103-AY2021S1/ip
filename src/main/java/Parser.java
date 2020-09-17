package main.java.duke;

import main.java.duke.command.Command;
import main.java.duke.command.ListCommand;
import main.java.duke.command.DoneCommand;
import main.java.duke.command.TodoCommand;
import main.java.duke.command.EventCommand;
import main.java.duke.command.DeadlineCommand;
import main.java.duke.command.DeleteCommand;
import main.java.duke.command.UpdateCommand;
import main.java.duke.command.TerminationCommand;
import main.java.duke.dukeexception.InvalidTaskException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class Parser {
    static final String LIST = "list";
    static final String DONE = "done";
    static final String TODO = "todo";
    static final String DEADLINE = "deadline";
    static final String EVENT = "event";
    static final String DELETE = "delete";
    static final String UPDATE = "update";
    static final Set<String> terminationCommands = new HashSet<String>(
            Arrays.asList("bye", "toodles", "farewell", "sayonara"));
    static final Set<String> nonTerminationCommands = new HashSet<String>(
            Arrays.asList(LIST, DONE, TODO, DEADLINE, EVENT, DELETE, UPDATE));

    public Parser() {}

    private static String getCommandType(String fullCommand) {
        return fullCommand.contains(" ")
                ? fullCommand.split(" ")[0]
                : fullCommand;  // list
    }

    public static Command parse(String fullCommand) throws InvalidTaskException, main.java.duke.dukeexception.InvalidInputException {
        fullCommand = fullCommand.trim();
        String commandType = getCommandType(fullCommand);

        if (commandType.equals(LIST)) {
            // list is the only command that takes only one word and nothing after a space
            return new ListCommand();
        }
        if (terminationCommands.contains(commandType)) {
            return new TerminationCommand();
        }
        if (!nonTerminationCommands.contains(commandType)) {
            throw new InvalidTaskException();
        }

        // valid command that is not list
        if (!fullCommand.contains(" ")) {
            throw new main.java.duke.dukeexception.InvalidInputException("Did you put your task info after a space?");
        }
        // take the part of the command without commandType
        String info = fullCommand.split(" ", 2)[1];

        switch (commandType) {
            case DONE:
                try {
                    int taskNumber = parseInt(info) - 1;
                    return new DoneCommand(taskNumber);
                } catch (Exception e) {
                    throw new main.java.duke.dukeexception.InvalidInputException("Specify the task number correctly.");
                }
            case TODO:
                try {
                    return new TodoCommand(info);
                } catch (Exception e) {
                    throw new main.java.duke.dukeexception.InvalidInputException("Did you put your task after a space?");
                }
            case EVENT:
                try {
                    String[] descAndDate = info.split(" /at ");
                    return new EventCommand(descAndDate[0], LocalDate.parse(descAndDate[1]));
                } catch (Exception e) {
                    throw new main.java.duke.dukeexception.InvalidInputException("Format for dates is yyyy-mm-dd. " +
                            "Also, did you put a task before and date after ' /at '?");
                }
            case DEADLINE:
                try {
                    String[] descAndDate = info.split(" /by ");
                    return new DeadlineCommand(descAndDate[0], LocalDate.parse(descAndDate[1]));
                } catch (Exception e) {
                    throw new main.java.duke.dukeexception.InvalidInputException("Format for dates is yyyy-mm-dd. " +
                            "Also, did you put a task before and deadline after ' /by '?");
                }
            case DELETE:
                try {
                    int taskNumber = parseInt(info) - 1;
                    return new DeleteCommand(taskNumber);
                } catch (Exception e) {
                    throw new main.java.duke.dukeexception.InvalidInputException("Specify the task number correctly.");
                }
            case UPDATE:
                try {
                    int taskNumber = parseInt(info) - 1;
                    String newTaskDesc = new main.java.duke.Ui().readUpdateDesc();
                    return new UpdateCommand(taskNumber, newTaskDesc);
                } catch (Exception e) {
                    throw new main.java.duke.dukeexception.InvalidInputException("Specify the task number correctly.");
                }
            default:
                throw new InvalidTaskException();
        }
    }
}

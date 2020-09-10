package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandList;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListByPriorityCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.util.HashMap;
import java.util.function.Function;

//TODO: change exceptions to throw from creation instead of checking.

/**
 * This class will translate the user's input into command.
 */
public class Parser {
    private static HashMap<CommandList, Function<String, Command>> funcMapper = new HashMap<>();
    static {
        funcMapper.put(CommandList.bye, x -> new ExitCommand());
        funcMapper.put(CommandList.exit, x -> new ExitCommand());
        funcMapper.put(CommandList.list, x -> processList(x));
        funcMapper.put(CommandList.todo, x -> new AddCommand(todo(x)));
        funcMapper.put(CommandList.deadline, x -> new AddCommand(deadline(x)));
        funcMapper.put(CommandList.event, x -> new AddCommand(event(x)));
        funcMapper.put(CommandList.done, x -> new DoneCommand(tryParseInt(x) - 1));
        funcMapper.put(CommandList.delete, x -> new DeleteCommand(tryParseInt(x) - 1));
        funcMapper.put(CommandList.find, x -> new FindCommand(x));
    }

    /**
     * Returns the command type of the user's input.
     *
     * @param cmd User's input in String.
     * @return Command object that is related to user's input.
     * @throws DukeException if the input string does not contain a valid command.
     */
    public static Command parse(String cmd) throws DukeException {
        CommandList commandList;
        int indexOfFirstSpace = cmd.indexOf(' ');
        String commandString, contentString;

        if (indexOfFirstSpace < 0) {
            commandString = cmd;
            contentString = null;
        } else {
            commandString = cmd.substring(0, indexOfFirstSpace);
            contentString = cmd.substring(indexOfFirstSpace + 1).strip();
        }

        try {
            commandList = CommandList.valueOf(commandString);
        } catch (IllegalArgumentException iae) {
            throw new DukeException("This is not in my command list");
        }

        return funcMapper.get(commandList).apply(contentString);
    }

    //TODO: to be removed since ToDo checks for description_blank
    private static ToDo todo(String string) {
        if (string.isBlank()) {
            throw new DukeException("The description cannot be empty");
        }
        return new ToDo(string);
    }

    private static Deadline deadline(String string) {
        String[] split = string.split("/by");

        if (split.length == 1) {
            throw new DukeException("I can't find the \"/by\" keyword...");
        }

        if (split[0].isBlank() || split[1].isBlank()) { //TODO: can be removed since check is done in constructor
            throw new DukeException("The description or deadline of \"deadline\" cannot be empty");
        }
        return new Deadline(split[0].strip(), Util.convertStringToDateTime(split[1].strip()));
    }

    private static Event event(String string) {
        String[] split = string.split("/at");

        if (split.length == 1) {
            throw new DukeException("I can't find the \"/at\" keyword...");
        }

        if (split[0].isBlank() || split[1].isBlank()) {//TODO: can be removed since check is done in constructor
            throw new DukeException("The description or date of \"event\" cannot be empty");
        }

        return new Event(split[0].strip(), Util.convertStringToDateTime(split[1].strip()));
    }

    private static Command processList(String input) {
        //Possible types of list command:
        //list (default list command to show all the task in the list)
        //list -priority (shows list command with priority from HIGHEST to LOWEST)
        //list -priority:rev (shows list command with priority from LOWEST to HIGHEST)

        if (input == null || input.isBlank()) {
            return new ListCommand();
        }

        if (input.contains("priority")) {
            boolean isReversedPriority = false;
            if (input.contains("priority:rev")) {
                isReversedPriority = true;
            }
            return new ListByPriorityCommand(isReversedPriority);
        }

        return new ListCommand(); //no one should reach here. All invalid settings will display the default list.
    }

    private static int tryParseInt(String contentString) {
        try {
            int selected = Integer.parseInt(contentString);
            return selected;
        } catch (NumberFormatException nfe) {
            throw new DukeException("This is not a number for \"done\" command: " + contentString);
        }
    }
}

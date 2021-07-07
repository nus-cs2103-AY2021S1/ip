package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandList;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.util.HashMap;
import java.util.function.Function;

/**
 * This class will translate the user's input into command.
 */
public class Parser {
    private static HashMap<CommandList, Function<String, Command>> funcMapper = new HashMap<>();
    static {
        funcMapper.put(CommandList.bye, x -> new ExitCommand());
        funcMapper.put(CommandList.exit, x -> new ExitCommand());
        funcMapper.put(CommandList.list, x -> ListCommand.getListCommand(x));
        funcMapper.put(CommandList.todo, x -> new AddCommand(new ToDo(x)));
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
            contentString = "";
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

    private static Deadline deadline(String string) {
        String[] split = string.split("/by");

        if (split.length == 1) {
            throw new DukeException("I can't find the \"/by\" keyword...");
        }

        return new Deadline(split[0].strip(), Util.convertStringToDateTime(split[1].strip()));
    }

    private static Event event(String string) {
        String[] split = string.split("/at");

        if (split.length == 1) {
            throw new DukeException("I can't find the \"/at\" keyword...");
        }

        return new Event(split[0].strip(), Util.convertStringToDateTime(split[1].strip()));
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

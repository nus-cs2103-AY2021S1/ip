package pandabot.parser;


import pandabot.commands.AddCommand;
import pandabot.commands.ByeCommand;
import pandabot.commands.Command;
import pandabot.commands.DeleteCommand;
import pandabot.commands.DoneCommand;
import pandabot.commands.FindCommand;
import pandabot.commands.ListCommand;
import pandabot.exceptions.PandaBotException;
import pandabot.exceptions.PandaBotInsufficientArgumentException;
import pandabot.exceptions.PandaBotInvalidArgumentFormatException;
import pandabot.exceptions.PandaBotInvalidCommandException;
import pandabot.tasks.Deadline;
import pandabot.tasks.Event;
import pandabot.tasks.ToDo;

/**
 * Represents a parser that is able to make sense of the user input commands.
 */
public class Parser {

    /**
     * Parses a String command into actual commands that PandaBot can perform.
     *
     * @param input the input command to be parsed
     * @return a Command that the program understands
     * @throws PandaBotException If the input cannot be understood or is invalid
     */
    public static Command parse(String input) throws PandaBotException {
        String[] cmd = input.split(" ", 2); // obtain first word and the rest of the string
        switch(cmd[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            verifyArguments(cmd, 2);
            try {
                int i = Integer.parseInt(cmd[1]) - 1;
                return new DoneCommand(i);
            } catch (NumberFormatException e) {
                throw new PandaBotInvalidArgumentFormatException(input);
            }
        case "delete":
            verifyArguments(cmd, 2);
            try {
                int j = Integer.parseInt(cmd[1]) - 1;
                return new DeleteCommand(j);
            } catch (NumberFormatException e) {
                throw new PandaBotInvalidArgumentFormatException(input);
            }
        case "todo":
            verifyArguments(cmd, 2);
            return new AddCommand(new ToDo(cmd[1]));
        case "deadline":
            verifyArguments(cmd, 2);
            String[] deadlineDes = obtainDescription(cmd[1], "/by ");
            return new AddCommand(new Deadline(deadlineDes[0], deadlineDes[1]));
        case "event":
            verifyArguments(cmd, 2);
            String[] eventDes = obtainDescription(cmd[1], "/at ");
            return new AddCommand(new Event(eventDes[0], eventDes[1]));
        case "find":
            verifyArguments(cmd, 2);
            return new FindCommand(cmd[1]);
        default:
            throw new PandaBotInvalidCommandException();
        }
    }

    private static void verifyArguments(String[] cmd, int len) throws PandaBotInsufficientArgumentException {
        if (cmd.length < len || cmd[cmd.length - 1].length() == 0) {
            throw new PandaBotInsufficientArgumentException();
        }
        assert cmd[cmd.length - 1].length() > 0 : "Command arguments should not be empty.";
        assert cmd.length == len : "Command arguments should match the number of arguments required.";
    }

    private static String[] obtainDescription(String des, String separator) throws PandaBotInsufficientArgumentException {
        String[] res = des.split(separator, 2);
        verifyArguments(res, 2);
        return res;
    }
}

package ultron;

import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ultron.commands.ByeCommand;
import ultron.commands.Command;
import ultron.commands.DeleteCommand;
import ultron.commands.DoneCommand;
import ultron.commands.FindCommand;
import ultron.commands.HelpCommand;
import ultron.commands.ListCommand;
import ultron.commands.TaskAllocator;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;


public final class Parser {

    /** The regex pattern for checking the commands and inputs. */
    private static final Pattern PATTERN =
        Pattern.compile("(^\\s?\\w+\\b) ?(.*)?$");
    private static final HashMap<String, Function<String, Command>> mp;

    //Initialise the commands into a hashmap.
    static {
        mp = new HashMap<>();
        Parser.mp.put("bye", ByeCommand::new);
        Parser.mp.put("find", FindCommand::new);
        Parser.mp.put("list", ListCommand::new);
        Parser.mp.put("help", HelpCommand::new);
        Parser.mp.put("done", DoneCommand::new);
        Parser.mp.put("delete", DeleteCommand::new);
    }

    private Parser() {
    }

    /**
     * Check the input string and arguments.
     *
     * @param command   Input command of the user.
     * @param arguments Arguments provided by the user.
     * @return Command corresponding to the input given by user.
     * @throws UltronException if there are errors in getting the command.
     */
    private static Command checkInput(final String command, final String arguments) throws UltronException {
        Function<String, Command> cmd = mp.getOrDefault(command, null);
        if (cmd == null) {
            return new TaskAllocator(command, arguments);
        }
        return cmd.apply(arguments);
    }

    /**
     * Parse the integer provided by the user to an index in list.
     *
     * @param args User arguments to be converted to index.
     * @return int parsedInteger.
     * @throws UltronException if there is an error parsing the integer.
     */
    public static int parseInteger(final String args) throws UltronException {
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new UltronException(args, ExceptionType.INVALID_NUMBER);
        }
    }

    /**
     * Parsing commands.
     *
     * @param input raw input of the user.
     * @return Command corresponding to the raw input.
     * @throws UltronException when there is an invalid command.
     */
    public static Command parseCommand(final String input)
        throws UltronException {
        Matcher inputs = PATTERN.matcher(input);
        if (!inputs.find()) {
            throw new UltronException(input, ExceptionType.INVALID_COMMAND);
        }
        String inputCommand = inputs.group(1);
        String arguments = inputs.group(2);
        return checkInput(inputCommand, arguments);
    }
}

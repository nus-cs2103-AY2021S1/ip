package ultron;

import ultron.commands.Command;
import ultron.commands.FindCommand;
import ultron.commands.TaskAllocator;
import ultron.commands.ByeCommand;
import ultron.commands.ListCommand;
import ultron.commands.HelpCommand;
import ultron.commands.DeleteCommand;
import ultron.commands.DoneCommand;
import ultron.exceptions.UltronException;
import ultron.exceptions.ExceptionType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    //Get the pattern for the regex for parsing the command
    private static final Pattern pattern = Pattern.compile("(^\\s?\\w+\\b) ?(.*)?$");

    private static Command checkInput(final String input, final String arguments) throws UltronException {

        //Switch case to process the commands
        switch (input) {
        //If the user keys in bye
        case "bye":
            return new ByeCommand(arguments);
            
        case "find":
            return new FindCommand(arguments);

        //If the user keys in list
        case "list":
            return new ListCommand(arguments);

        //Check if the user is asking for help
        case "help":
            return new HelpCommand(arguments);

        //Check if the user is deleting some of the items
        case "delete":
            return new DeleteCommand(arguments);

        //Check if the user is done with any task
        case "done":
            return new DoneCommand(arguments);

        //Otherwise it will be a task to be added
        default:
            return new TaskAllocator(input, arguments);
        }

    }

    public static int parseInteger(final String args) throws UltronException {
        //Catch any errors in the number
        try {

            //Get the index of the items
            return Integer.parseInt(args) - 1;

        } catch (NumberFormatException e) {

            //Throw a new Ultron exception
            throw new UltronException(args, ExceptionType.INVALID_NUMBER);
        }
    }

    public static Command parseCommand(String input) throws UltronException{
        //Use regex to get the grp
        Matcher inputs = pattern.matcher(input);

        //Find the items in the group
        if (!inputs.find()) {

            //Throw an invalid command error if it is unable to find any matches
            throw new UltronException(input, ExceptionType.INVALID_COMMAND);
        }

        //Get the command
        String inputCommand = inputs.group(1);

        //Get the other arguments
        String arguments = inputs.group(2);

        return checkInput(inputCommand, arguments);
    }

}

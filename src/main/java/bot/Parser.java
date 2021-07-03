package bot;
import bot.command.AddCommand;
import bot.command.Command;
import bot.command.DeleteCommand;
import bot.command.DoneCommand;
import bot.command.ExitCommand;
import bot.command.FindCommand;
import bot.command.ListCommand;
import bot.util.InvalidCommandException;
import bot.util.InvalidInputException;


/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns the command based on the first character of user's input.
     * If the command given is invalid, InvalidInputException is thrown.
     *
     * @param input user's input
     * @return Command enum class
     * @throws InvalidInputException command's argument is invalid
     * @throws InvalidCommandException command is invalid
     */
    public Command parseInput(String input) throws InvalidInputException,
            InvalidCommandException {
        if (input.length() == 0) {
            throw new InvalidInputException("Please input something");
        }
        try {
            String[] words = input.split(" ");
            String[] args = new String[2];
            switch(words[0]) {
            case "list":
                return new ListCommand("list");
            case "todo":
                return new AddCommand("todo", parseSingleArg(input));
            case "deadline":
                args = parseDeadline(input);
                return new AddCommand("deadline", args[0], args[1]);
            case "event":
                args = parseEvent(input);
                return new AddCommand("event", args[0], args[1]);
            case "done":
                return new DoneCommand("done", parseIndex(input));
            case "delete":
                return new DeleteCommand("delete", parseIndex(input));
            case "find":
                return new FindCommand("find", parseSingleArg(input));
            case "bye":
                return new ExitCommand("exit");
            default:
                throw new InvalidCommandException("What's that again? I can't understand.");
            }
        } catch (InvalidCommandException | InvalidInputException e) {
            throw e;
        }
    }

    /**
     * Returns the one and only argument assuming the the input is the command with only 1 arg.
     * @param input user's input
     * @return user's argument
     * @throws InvalidInputException command's argument is invalid
     */
    public String parseSingleArg(String input) throws InvalidInputException {
        String[] words = input.split(" ");
        StringBuilder name = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            name.append(words[i]);
            if (i != words.length - 1) {
                name.append(" ");
            }
        }
        if (name.length() == 0) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input."
                    + " Thank you.");
        }
        return name.toString();
    }

    /**
     * Returns the name and date assuming the the input is the command for Deadline.
     *
     * @param input user's input.
     * @return an array of 2 String, first String is the name of deadline.
     * while second is the deadline's date.
     * @throws InvalidInputException command's argument is invalid.
     */
    public String[] parseDeadline(String input) throws InvalidInputException {
        String[] output = new String[2];
        String[] words = input.split(" ");
        StringBuilder name = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        int indexDateDelimiter = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by")) {
                indexDateDelimiter = i;
            }
        }
        try {
            for (int i = 1; i < indexDateDelimiter; i++) {
                name.append(words[i]);
                name.append(" ");
            }
            for (int i = indexDateDelimiter + 1; i < words.length; i++) {
                deadline.append(words[i]);
                deadline.append(" ");
            }
            if (indexDateDelimiter == -1 || name.length() == 0 || deadline.length() == 0) {
                throw new InvalidInputException("Sorry, do what? Please give me a valid input."
                        + " Thank you.");
            }
        } catch (InvalidInputException e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input."
                    + " Thank you.");
        }
        name.deleteCharAt(name.length() - 1);
        deadline.deleteCharAt(deadline.length() - 1);
        output[0] = name.toString();
        output[1] = deadline.toString();
        return output;
    }

    /**
    * Returns the name and date assuming the the input is the command for Event.
    *
    * @param input user's input
    * @return an array of 2 String, first String is the name of event * while second is the
    *     event's date
    * @throws InvalidInputException command's arugument is invalid
    */
    public String[] parseEvent(String input) throws InvalidInputException {
        String[] output = new String[2];
        String[] words = input.split(" ");
        StringBuilder name = new StringBuilder();
        StringBuilder deadline = new StringBuilder();

        int indexDateDelimiter = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/at")) {
                indexDateDelimiter = i;
            }
        }
        try {
            for (int i = 1; i < indexDateDelimiter; i++) {
                name.append(words[i]);
                name.append(" ");
            }
            for (int i = indexDateDelimiter + 1; i < words.length; i++) {
                deadline.append(words[i]);
                deadline.append(" ");
            }
            if (indexDateDelimiter == -1 || name.length() == 0 || deadline.length() == 0) {
                throw new InvalidInputException("Sorry, do what? Please give me a valid input."
                        + " Thank you.");
            }
        } catch (InvalidInputException e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input."
                    + " Thank you.");
        }
        name.deleteCharAt(name.length() - 1);
        deadline.deleteCharAt(deadline.length() - 1);
        output[0] = name.toString();
        output[1] = deadline.toString();
        return output;
    }

    /**
     * Returns the index assuming the input is for remove/delete command.
     * @param input user's input
     * @return the index
     * @throws InvalidInputException command's argument is invalid
     */
    public int parseIndex(String input) throws InvalidInputException {
        String[] words = input.split(" ");
        if (words.length != 2) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input."
                    + " Thank you.");
        }
        return Integer.parseInt(words[1]);
    }
}

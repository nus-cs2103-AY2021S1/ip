/**
 * Parser object processes and makes sense ofuser input to be executed.
 * Input validation is also done as inputs are parsed to ensure command
 * Strings follow a specific format before they can be executed.
 *
 * @author Hakiem Rasid
 */
public class Parser {

    public static final String[] COMMANDS = {"todo", "deadline", "event", "list", "done", "bye", "delete",
        "clear", "unknown"};

    /**
     * Returns Command object by processing user input.
     *
     * @param input User inputs as String.
     * @return Command object
     * @throws InvalidInputException If inputs are of incorrect format
     * and cannot be parsed
     */
    public Command parseCommand(String input) throws InvalidInputException {
        String[] strings = input.split(" ");
        Command command;

        if (input.trim().toLowerCase().equals(Parser.COMMANDS[5])) {
            // returns BYE Command
            command = new Command(CommandType.BYE, input);
        } else if (input.trim().toLowerCase().equals(Parser.COMMANDS[7])) {
            // returns CLEAR Command
            command = new Command(CommandType.CLEAR, input);
        } else if (input.trim().toLowerCase().equals(Parser.COMMANDS[3])) {
            // return LIST Command
            command = new Command(CommandType.LIST, input);
        } else if (strings[0].toLowerCase().equals(Parser.COMMANDS[4])) {
            // returns DONE Command
            command = new Command(CommandType.DONE, input);
        } else if (strings[0].toLowerCase().equals(Parser.COMMANDS[6])) {
            // returns DELETE Command
            command = new Command(CommandType.DELETE, input);
        } else if (strings[0].toLowerCase().equals(Parser.COMMANDS[0])) {
            // returns TODO Command
            command = new Command(CommandType.TODO, input);
        } else if (strings[0].toLowerCase().equals(Parser.COMMANDS[1])) {
            // returns DEADLINE Command
            command = new Command(CommandType.DEADLINE, input);
        } else if (strings[0].toLowerCase().equals(Parser.COMMANDS[2])) {
            // returns EVENT Command
            command = new Command(CommandType.EVENT, input);
        } else {
            // returns UNKNOWN Command
            command = new Command(CommandType.UNKNOWN, "Sorry, I don't understand!");
        }
        return validateCommand(command);
    }

    /**
     * Returns Command object after validating its contents.
     *
     * @param cmd Command object to be validated.
     * @return Valid Command object that can be executed.
     * @throws InvalidInputException If description of input Command
     * is of incorrect format and cannot be parsed.
     */
    public Command validateCommand(Command cmd) throws InvalidInputException {
        Command validCommand = new Command(CommandType.UNKNOWN, "Sorry, I don't understand!");
        switch(cmd.getType()) {
            case BYE:
            case CLEAR:
            case LIST:
            case UNKNOWN:
                // no need to validate these Command types
                validCommand = cmd;
                break;
            case DONE:
                validCommand = checkDoneAndDeleteValidity(cmd);
                break;
            case DELETE:
                validCommand = checkDoneAndDeleteValidity(cmd);
                break;
            case TODO:
                validCommand = checkToDoValidity(cmd);
                break;
            case DEADLINE:
            case EVENT:
                validCommand = checkDeadlineAndEventValidity(cmd);
                break;
        }
        return validCommand;
    }

    /**
     * Returns valid Command object after input validity checks of
     * DEADLINE and EVENT Commands.
     *
     * @param cmd Command object with CommandType DEADLINE or EVENT.
     * @return Input Command object if description passes input validity checks.
     * @throws InvalidInputException If description of Command object is of
     * incorrect format.
     */
    public Command checkDeadlineAndEventValidity(Command cmd) throws InvalidInputException {
        String cmdIdentifier;

        String description = cmd.getDescription();
        if (cmd.getType().equals(CommandType.DEADLINE)) {
            // case: DEADLINE
            cmdIdentifier = "by";
        } else {
            // case: EVENT
            cmdIdentifier = "at";
        }

        if (!description.contains(" /" + cmdIdentifier)) {
            // throws exception if invalid input format: does not contain "/by" or "/at"
            // must have space before /by or /at keywords
            throw new InvalidInputException("Command is missing \"/" + cmdIdentifier +
                    "\" keyword");
        }

        if (description.split(" /")[0].toLowerCase().equals("deadline") ||
                description.split(" /")[0].toLowerCase().equals("event")) {

            // throws exception if invalid input format: "deadline /by taskDeadline"
            // throws exception if invalid input format: "event /by eventDate"
            throw new InvalidInputException("Missing task description");
        }

        if (description.split(" ")[(description.split(" ").length - 1)].equals("/" + cmdIdentifier)) {
            // throws exception if invalid input format: "deadline taskName /by"
            throw new InvalidInputException("Missing task deadline/time");
        }
        return cmd;
    }

    /**
     * Returns valid Command object after input validity checks of TODO Command.
     *
     * @param cmd Command object with CommandType TODO.
     * @return Input Command object if description passes input validity checks.
     * @throws InvalidInputException If description of Command object is of incorrect format.
     */
    public Command checkToDoValidity(Command cmd) throws InvalidInputException {
        if (cmd.getDescription().split(" ").length == 1) {
            // throws exception if invalid input format: "todo" (missing task name)
            throw new InvalidInputException("Todo command incomplete");
        }
        return cmd;
    }

    /**
     * Returns valid Command object after input validity checks of DONE or
     * DELETE Commands.
     *
     * @param cmd Command object with CommandType DONE or DELETE.
     * @return Input Command object if description passes input validity checks.
     * @throws InvalidInputException If description of Command object is of
     * incorrect format.
     */
    public Command checkDoneAndDeleteValidity(Command cmd) throws InvalidInputException {
        String description = cmd.getDescription();
        if (description.split(" ").length == 1) {
            // throws exception if invalid input format: "done"/"delete" (missing index)
            throw new InvalidInputException("Task index not specified");
        }

        if (description.split(" ").length > 2) {
            // throws exception if invalid input format: > 2 strings separated by " "
            // e.g "done/delete 1 2 3", "done/delete 12 text"
            throw new InvalidInputException("Sorry, command unclear! Please specify only one index");
        }

        int index;
        try {
            // parse int for index of task to be marked as done/deleted
            index = Integer.valueOf(description.split(" ")[1]);
        } catch (NumberFormatException e) {
            // throws exception if invalid input format: Invalid integer
            // e.g "done/delete abc"
            throw new InvalidInputException("Please enter a valid integer");
        }

        return cmd;
    }

}
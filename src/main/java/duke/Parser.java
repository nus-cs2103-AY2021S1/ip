package duke;

/**
 * Class used for parsing input from user command.
 */
class Parser {

    /**
     * Parses input, and calls corresponding functions.
     *
     * @param input String input from user.
     * @return Corresponding Command with respect to input.
     * @throws DukeException  When date is in wrong format, or task lacks description.
     */
    static Command parse(String input) throws DukeException {
        String[] partsOfCommand = splitCommandAndFormatAction(input);
        String action = partsOfCommand[0];

        CommandName commandName = checkCommandNameEnum(action);
        return handleCommand(commandName, partsOfCommand);
    }

    private static String[] splitCommandAndFormatAction(String input) {
        String[] partsOfCommand = input.split(" ", 2);
        String action = partsOfCommand[0].toUpperCase();

        if (partsOfCommand.length > 1) {
            assert partsOfCommand.length == 2 : "Length of parts of command should be 2";
            String secondPartOfCommand = partsOfCommand[1];
            return new String[]{action, secondPartOfCommand};
        } else {
            return new String[]{action};
        }
    }

    private static Command handleCommand(CommandName commandName, String[] partsOfCommand) throws DukeException {
        int index;
        String secondPartOfCommand;

        switch(commandName) {
        case LIST:
            return new ListCommand();
        case DONE:
            secondPartOfCommand = partsOfCommand[1];
            index = Integer.parseInt(secondPartOfCommand);
            return new DoneCommand(index);
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            return handleTask(partsOfCommand, commandName);
        case DELETE:
            secondPartOfCommand = partsOfCommand[1];
            index = Integer.parseInt(secondPartOfCommand);
            return new DeleteCommand(index);
        case FIND:
            secondPartOfCommand = partsOfCommand[1];
            return new FindCommand(secondPartOfCommand);
        case SORT:
            return handleSort(partsOfCommand);
        case BYE:
            return new ExitCommand();
        default:
            assert false : "There should only be a fixed number of commands, should not reach here";
            return new ExitCommand();
        }
    }

    private static Command handleSort(String[] inputs) throws DukeException {
        try {
            String sortOrderOption = inputs[1].toUpperCase();
            SortOrderOption sortOrderOptionEnum = checkSortOrderOptionEnum(sortOrderOption);
            return new SortCommand(sortOrderOptionEnum);
        } catch (ArrayIndexOutOfBoundsException e) {
            String missingSortOptionsErrorMessage = "OOPS!!! Please add in sort order.";
            throw new DukeException(missingSortOptionsErrorMessage);
        }
    }

    /**
     * Takes in task info and command and calls respective handler (e.g. handle event).
     *
     * @param inputs Task info, after removing first word from user input.
     * @param commandName CommandName (e.g. Event), first word from user input.
     * @throws DukeException If deadline date not input for deadline, or event date not input for event.
     */
    private static Command handleTask(String[] inputs, CommandName commandName) throws DukeException {
        try {
            String taskInfo = inputs[1];
            String[] taskInfoParts;
            String description;
            String date;
            //TODO: in switch/if-else statement, okay to define early if we use the same variable name?

            switch (commandName) {
            case TODO:
                return new AddCommand(commandName, taskInfo);
            case DEADLINE:
                taskInfoParts = taskInfo.split(" /by ", 2);
                description = taskInfoParts[0];
                date = taskInfoParts[1];
                return new AddCommand(commandName, description, date);
            case EVENT:
                taskInfoParts = taskInfo.split(" /at ", 2);
                description = taskInfoParts[0];
                date = taskInfoParts[1];
                return new AddCommand(commandName, description, date);
            default:
                assert false : "There should only be 3 types of tasks, should not reach here";
                return new AddCommand(commandName, "");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            String emptyDescriptionErrorMessage = "OOPS!!! The description of a " + commandName.toString().toLowerCase()
                + " cannot be empty.";
            throw new DukeException(emptyDescriptionErrorMessage);
        }
    }

    private static CommandName checkCommandNameEnum(String action) throws DukeException {
        try {
            return CommandName.valueOf(action);
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static SortOrderOption checkSortOrderOptionEnum(String orderOption) throws DukeException {
        try {
            return SortOrderOption.valueOf(orderOption);
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! Please enter either asc or desc.");
        }
    }
}

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
        switch(commandName) {
        case LIST:
            return new ListCommand();
        case DONE:
            return handleDone(partsOfCommand);
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            return handleTask(partsOfCommand, commandName);
        case DELETE:
            return handleDelete(partsOfCommand);
        case FIND:
            return handleFind(partsOfCommand);
        case SORT:
            return handleSort(partsOfCommand);
        case BYE:
            return new ExitCommand();
        default:
            assert false : "There should only be a fixed number of commands, should not reach here";
            return new ExitCommand();
        }
    }

    private static String tryGetSecondElement(String[] inputs, String errorMessage) throws DukeException {
        try {
            return inputs[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(errorMessage);
        }
    }

    private static Command handleDone(String[] inputs) throws DukeException {
        String indexString = tryGetSecondElement(inputs, "OOPS!!! Please add index to be done.");
        int index = Integer.parseInt(indexString);
        return new DoneCommand(index);
    }

    private static Command handleDelete(String[] inputs) throws DukeException {
        String indexString = tryGetSecondElement(inputs, "OOPS!!! Please add index to be deleted.");
        int index = Integer.parseInt(indexString);
        return new DeleteCommand(index);
    }

    private static Command handleFind(String[] inputs) throws DukeException {
        String findString = tryGetSecondElement(inputs, "OOPS!!! Please add what you want to find.");
        return new FindCommand(findString);
    }

    private static Command handleSort(String[] inputs) throws DukeException {
        String sortOrderOption = tryGetSecondElement(inputs,
                "OOPS!!! Please add in sort order.").toUpperCase();
        SortOrderOption sortOrderOptionEnum = checkSortOrderOptionEnum(sortOrderOption);
        return new SortCommand(sortOrderOptionEnum);
    }

    /**
     * Takes in task info and command and calls respective handler (e.g. handle event).
     *
     * @param inputs Task info, after removing first word from user input.
     * @param commandName CommandName (e.g. Event), first word from user input.
     * @throws DukeException If deadline date not input for deadline, or event date not input for event.
     */
    private static Command handleTask(String[] inputs, CommandName commandName) throws DukeException {
        String taskInfo = tryGetSecondElement(inputs,
                "OOPS!!! The description of a "
                + commandName.toString().toLowerCase()
                + " cannot be empty.");

        switch (commandName) {
        case TODO:
            return new AddCommand(commandName, taskInfo);
        case DEADLINE:
            return handleDeadlineAndEvent(taskInfo, " /by ", commandName);
        case EVENT:
            return handleDeadlineAndEvent(taskInfo, " /at ", commandName);
        default:
            assert false : "There should only be 3 types of tasks, should not reach here";
            return new AddCommand(commandName, "");
        }
    }

    private static Command handleDeadlineAndEvent(
            String taskInfo, String toSplitBy, CommandName commandName) throws DukeException {

        String [] taskInfoParts = taskInfo.split(toSplitBy, 2);
        String description = taskInfoParts[0];
        String date = tryGetSecondElement(taskInfoParts,
                "OOPS!!! The date of a "
                        + commandName.toString().toLowerCase()
                        + " cannot be empty.");
        return new AddCommand(commandName, description, date);
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

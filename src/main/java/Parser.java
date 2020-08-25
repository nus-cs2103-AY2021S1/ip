/**
 * Takes in the input from the user and tries to make sense of it.
 * Throws error if input is incorrect
 */
public class Parser {

    /**
     * The method that parses the input from the user
     * @param fullCommand User input.
     * @return Command object that will execute the corresponding command.
     * @throws InvalidInputException If input is gibberish.
     */
    public static Command parse(String fullCommand) throws InvalidInputException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand(fullCommand);
        } else if (fullCommand.equals("list")) {
            return new ListCommand(fullCommand);
        } else if (fullCommand.startsWith("done")) {
            return new DoneCommand(fullCommand);
        } else if (fullCommand.startsWith("todo")) {
            return new ToDoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return new DeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return new EventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            return new FindCommand(fullCommand);
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

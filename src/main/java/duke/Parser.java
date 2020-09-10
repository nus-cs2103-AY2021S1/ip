package duke;

public class Parser {
    /**
     * Parses user input and returns the correct command to execute
     *
     * @param input User input
     * @return Correct command for Duke to execute
     * @throws DukeException if exception encountered
     */
    public static Command parse(String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else if (input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("done")) {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            return new MarkDoneCommand(taskIndex);
        } else if (input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("find")) {
            String search = input.substring(5);
            return new FindCommand(search);
        } else if (input.length() >= 6 && input.substring(0, 6).equalsIgnoreCase("delete")) {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            return new DeleteCommand(taskIndex);
        } else if (input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("todo")) {
            return new AddCommand(input, ActionType.ADD_TODO);
        } else if (input.length() >= 5 && input.substring(0, 5).equalsIgnoreCase("event")) {
            return new AddCommand(input, ActionType.ADD_EVENT);
        } else if (input.length() >= 8 && input.substring(0, 8).equalsIgnoreCase("deadline")) {
            return new AddCommand(input, ActionType.ADD_DEADLINE);
        } else {
            throw new DukeException("Sorry but I think you enter a wrong command...");
        }
    }
}

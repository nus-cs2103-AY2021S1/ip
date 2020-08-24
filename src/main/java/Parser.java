public class Parser {
    public static Command parse(String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("done")) {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            return new MarkDoneCommand(taskIndex);
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
            throw new DukeException("I have no idea what that means ¯\\_(ツ)_/¯");
        }
    }
}

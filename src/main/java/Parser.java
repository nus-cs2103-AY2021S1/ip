public class Parser {

    public static Command parseInput(String input) {

        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.contains("done")) {
            return new DoneCommand(input);
        } else if (input.contains("delete")) {
            return new DeleteCommand(input);
        } else if (input.contains("find")) {
            return new FindCommand(input);
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            return new AddCommand(input);
        } else if (input.equals("priority")) {
            return new PriorityCommand();
        } else if (input.contains("priority")) {
            return new PriorityCommand(input);
        } else {
            return null;
        }
    }
}

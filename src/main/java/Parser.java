public class Parser {
    public static Command parseInput(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("commands")) {
            return Command.COMMANDS;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.matches("delete \\d+")) {
            return Command.DELETE;
        } else if (input.matches("done \\d+")) {
            return Command.DONE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else {
            return Command.UNKNOWN;
        }
    }
}

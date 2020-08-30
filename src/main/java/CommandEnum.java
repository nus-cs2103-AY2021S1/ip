public class CommandEnum {
    public enum Command {
        BYE, LIST, DONE, DELETE, TODO, EVENT, DEADLINE, FIND, INVALID
    }

    /**
     * Take the first keyword from the user's input, and return
     * an enum corresponding to the right command.
     *
     * @param input
     * @return
     */
    public static Command getCommand(String input) {
        String lowerCaseInput = input.toLowerCase();
        switch (lowerCaseInput) {
            case "bye":
                return Command.BYE;
            case "list":
                return Command.LIST;
            case "done":
                return Command.DONE;
            case "delete":
                return Command.DELETE;
            case "todo":
                return Command.TODO;
            case "event":
                return Command.EVENT;
            case "deadline":
                return Command.DEADLINE;
            case "find":
                return Command.FIND;
            default:
                return Command.INVALID;
        }
    }
}

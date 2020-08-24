public class Parser {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_COMPLETE = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";

    public static Command parse(String fullCommand) throws DukeUnknownCommandException {
        StringBuilder commandInput = new StringBuilder();
        StringBuilder argsInput = new StringBuilder();
        boolean commandFound = false;
        for (int i = 0; i < fullCommand.length(); i++) {
            if (commandFound) {
                argsInput.append(fullCommand.charAt(i));
            } else if (fullCommand.charAt(i) == ' ') {
                commandFound = true;
            } else {
                commandInput.append(fullCommand.charAt(i));
            }
        }

        String command = commandInput.toString();
        String args = argsInput.toString();

        if (command.equals(COMMAND_ADD_DEADLINE)) {
            return new AddCommand(args, TaskType.DEADLINE);
        } else if (command.equals(COMMAND_ADD_EVENT)) {
            return new AddCommand(args, TaskType.EVENT);
        } else if (command.equals(COMMAND_ADD_TODO)) {
            return new AddCommand(args, TaskType.TODO);
        } else if (command.equals(COMMAND_COMPLETE)) {
            return new CompleteCommand(args);
        } else if (command.equals(COMMAND_DELETE)) {
            return new DeleteCommand(args);
        } else if (command.equals(COMMAND_EXIT)) {
            return new ExitCommand(args);
        } else if (command.equals(COMMAND_LIST)) {
            return new ListCommand(args);
        } else {
            throw new DukeUnknownCommandException("Unknown command");
        }
    }
}

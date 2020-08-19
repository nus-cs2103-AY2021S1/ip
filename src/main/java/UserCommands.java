public class UserCommands {
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";

    public static UserCommandType parseUserCommand(String command) throws InvalidCommandException {
        if (command.equals(LIST_COMMAND)) {
            return UserCommandType.LIST;
        } else if (command.equals(EXIT_COMMAND)) {
            return UserCommandType.EXIT;
        } else if (command.startsWith(DONE_COMMAND)) {
            return UserCommandType.DONE;
        } else if (!command.isBlank()) {
            return UserCommandType.TODO;
        } else {
            throw new InvalidCommandException();
        }
    }

    public static int getDoneIndex(String command) throws InvalidCommandException {
        String[] components = command.split(" ");
        if (components.length != 2) {
            throw new InvalidCommandException("Done command should have 2 components");
        }
        return Integer.parseInt(components[1]) - 1;
    }

    public static class InvalidCommandException extends Exception {
        public InvalidCommandException(String errorMessage) {
            super("Invalid command: " + errorMessage);
        }

        public InvalidCommandException() {
            super("Invalid command");
        }
    }
}

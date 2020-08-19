public class UserCommands {
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";

    public static boolean isListCommand(String command) {
        return command.equals(LIST_COMMAND);
    }

    public static boolean isDoneCommand(String command) {
        return command.startsWith(DONE_COMMAND);
    }

    public static int getDoneIndex(String command) throws InvalidCommandException {
        String[] components = command.split(" ");
        if (components.length != 2) {
            throw new InvalidCommandException("Done command should have 2 components");
        }
        return Integer.parseInt(components[1]) - 1;
    }

    public static boolean isExitCommand(String command) {
        return command.equals(EXIT_COMMAND);
    }

    public static class InvalidCommandException extends Exception {
        public InvalidCommandException(String errorMessage) {
            super("Invalid done command: " + errorMessage);
        }

        public InvalidCommandException() {
            super("Invalid done command");
        }
    }
}

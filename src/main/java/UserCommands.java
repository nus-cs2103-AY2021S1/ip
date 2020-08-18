public class UserCommands {
    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";

    public static boolean isListCommand(String command) {
        return command.equals(LIST_COMMAND);
    }

    public static boolean isExitCommand(String command) {
        return command.equals(EXIT_COMMAND);
    }
}

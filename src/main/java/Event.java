public class Event extends Task {
    private String at;
    protected static final String FORMAT = "event <event> /at <time>";

    Event(String command) {
        super(extractNameFromCommand(command));
        int index = command.indexOf("/at ") + 4;
        at = command.substring(index);
    }

    static String extractNameFromCommand(String command) {
        int index = command.indexOf("/at ");
        return command.substring(6, index);
    }

    @Override
    public String toString() {
        return String.format("[%c] [%s] %s (at: %s)", 'E', getStatusIcon(), description, at);
    }
}
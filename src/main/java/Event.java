public class Event extends Task {
    private String at;
    protected static final char TYPE_CODE = 'E';
    protected static final String FORMAT = "event <event> /at <time>";

    Event(String command) {
        super(TYPE_CODE, extractNameFromCommand(command));
        int index = command.indexOf("/at ") + 4;
        at = command.substring(index);
    }

    Event(String description, boolean isDone, String at) {
        super(TYPE_CODE, description, isDone);
        this.at = at;
    }

    static String extractNameFromCommand(String command) {
        int index = command.indexOf(" /at ");
        return command.substring(6, index);
    }
    
    @Override
    String getAt() {
        return at;
    }
    
    @Override
    String getBy() {
        return null;
    }
    
    @Override
    public String toString() {
        return String.format("[%c] [%s] %s (at: %s)", TYPE_CODE, getStatusIcon(), description, at);
    }
}
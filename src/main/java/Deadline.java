public class Deadline extends Task {
    private String by;
    protected static final String FORMAT = "deadline <task> /by <deadline>";

    Deadline(String command) {
        super(extractNameFromCommand(command));
        int index = command.indexOf("/by ") + 4;
        by = command.substring(index);
    }

    static String extractNameFromCommand(String command) {
        int index = command.indexOf("/by ");
        return command.substring(9, index);
    }

    @Override
    public String toString() {
        return String.format("[%c] [%s] %s (by: %s)", 'D', getStatusIcon(), name, by);
    }
}
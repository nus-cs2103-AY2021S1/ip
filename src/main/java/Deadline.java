public class Deadline extends Task {
    private String by;
    protected static final char TYPE_CODE = 'D';
    protected static final String FORMAT = "deadline <task> /by <deadline>";

    Deadline(String command) {
        super(TYPE_CODE, extractNameFromCommand(command));
        int index = command.indexOf("/by ") + 4;
        by = command.substring(index);
    }

    Deadline(String description, boolean isDone, String by) {
        super(TYPE_CODE, description, isDone);
        this.by = by;
    }

    static String extractNameFromCommand(String command) {
        int index = command.indexOf(" /by ");
        return command.substring(9, index);
    }

    @Override
    String getAt() {
        return null;
    }

    @Override
    String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[%c] [%s] %s (by: %s)", TYPE_CODE, getStatusIcon(), description, by);
    }
}
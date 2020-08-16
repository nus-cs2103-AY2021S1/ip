public class Todo extends Task {
    Todo(String command) {
        super(extractNameFromCommand(command));
    }

    static String extractNameFromCommand(String command) {
        return command.substring(5);
    }

    @Override
    public String toString() {
        return String.format("[%c] [%s] %s", 'T', getStatusIcon(), name);
    }
}
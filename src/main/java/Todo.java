public class Todo extends Task {
    protected static final char TYPE_CODE = 'T';
    
    Todo(String command) {
        super(TYPE_CODE, extractNameFromCommand(command));
    }
    
    Todo(String description, boolean isDone) {
        super(TYPE_CODE, description, isDone);
    }

    static String extractNameFromCommand(String command) {
        return command.substring(5);
    }

    @Override
    String getAt() {
        return null;
    }

    @Override
    String getBy() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%c] [%s] %s", TYPE_CODE, getStatusIcon(), description);
    }
}
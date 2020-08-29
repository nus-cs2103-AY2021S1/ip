package botbot.tasks;

public class Todo extends Task {
    public static final char TYPE_CODE = 'T';
    
    public Todo(String command) {
        super(TYPE_CODE, extractNameFromCommand(command));
    }
    
    public Todo(String description, boolean isDone) {
        super(TYPE_CODE, description, isDone);
    }

    static String extractNameFromCommand(String command) {
        return command.substring(5);
    }

    @Override
    public String getAt() {
        return null;
    }

    @Override
    public String getBy() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%c] [%s] %s", TYPE_CODE, getStatusIcon(), description);
    }
}
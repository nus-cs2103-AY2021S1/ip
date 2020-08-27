public class Todo extends Task {
    Todo(String message) {
        super(message);
    }

    @Override
    public String getTypeLetter() {
        // dummy value
        return "[T]";
    }
}
package ChatbotPkg;

public class Todo extends Task {

    public Todo(String description) {
        super(description, "T");
    }

    public static Todo newTodo(String text) throws ChatbotException {
        if (text.length() == 0) {
            throw new ChatbotException("Ooopsss (>.>) Todo task cannot be empty!!");
        }
        return new Todo(text);
    }

    private Todo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    @Override
    public Todo markDone() {
        return new Todo(this.description, true);
    }
}

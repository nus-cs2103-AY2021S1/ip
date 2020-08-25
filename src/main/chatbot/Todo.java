public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, "T", isDone, null);
    }

    public static Todo newTodo(String body) throws ChatbotException {
        if (body.length() == 0) {
            throw new ChatbotException("Ooopsss (>.>) Todo task cannot be empty!!");
        }
        return new Todo(body, false);
    }

    @Override
    public Todo markDone() {
        return new Todo(this.description, true);
    }
}

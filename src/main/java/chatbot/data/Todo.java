package chatbot.data;

import chatbot.common.Message;
import chatbot.exception.ChatbotException;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, "T", isDone, null);
    }

    public static Todo newTodo(String body) throws ChatbotException {
        if (body.length() == 0) {
            throw new ChatbotException(Message.TASK_EMPTY);
        }

        return new Todo(body, false);
    }

    @Override
    public Todo markDone() {
        return new Todo(this.description, true);
    }
}

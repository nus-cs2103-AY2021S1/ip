package chatbot.exception;

import chatbot.common.Message;

public class ParseException extends ChatbotException {

    public ParseException() {
        super(Message.MESSAGE_UNKNOWN_COMMAND);
    }

    public ParseException(String message) {
        super(message);
    }
}

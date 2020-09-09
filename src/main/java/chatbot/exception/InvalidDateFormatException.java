package chatbot.exception;

import chatbot.common.Message;

public class InvalidDateFormatException extends ChatbotException {

    public InvalidDateFormatException() {
        super(Message.MESSAGE_INVALID_DATE_FORMAT);
    }

    public InvalidDateFormatException(String message) {
        super(message);
    }
}

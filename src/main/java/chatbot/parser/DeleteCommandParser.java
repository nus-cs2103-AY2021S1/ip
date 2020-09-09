package chatbot.parser;

import chatbot.commands.Command;
import chatbot.commands.DeleteCommand;

import chatbot.common.Message;

import chatbot.exception.ChatbotException;

public class DeleteCommandParser implements Parser {
    @Override
    public Command parse(String args) throws ChatbotException {
        try {
            int index = Integer.parseInt(args) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new ChatbotException(Message.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

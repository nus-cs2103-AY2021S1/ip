package chatbot.parser;

import chatbot.commands.Command;
import chatbot.commands.DoneCommand;

import chatbot.common.Message;

import chatbot.exception.ChatbotException;

public class DoneCommandParser implements Parser {
    @Override
    public Command parse(String args) throws ChatbotException {
        try {
            int index = Integer.parseInt(args) - 1;
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new ChatbotException(Message.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

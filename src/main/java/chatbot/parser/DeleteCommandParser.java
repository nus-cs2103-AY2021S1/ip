package chatbot.parser;

import chatbot.commands.Command;
import chatbot.commands.DeleteCommand;
import chatbot.exception.ChatbotException;
import chatbot.exception.ParseException;

public class DeleteCommandParser implements Parser {
    @Override
    public Command parse(String args) throws ChatbotException {
        try {
            int index = Integer.parseInt(args) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
    }
}

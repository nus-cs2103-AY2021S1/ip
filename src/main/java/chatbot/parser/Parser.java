package chatbot.parser;

import chatbot.commands.Command;
import chatbot.exception.ChatbotException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */

public interface Parser {
    Command parse(String args) throws ChatbotException;
}

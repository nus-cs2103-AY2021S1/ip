package chatbot.parser;

import chatbot.commands.AddCommand;
import chatbot.commands.Command;

import chatbot.common.Message;
import chatbot.exception.ChatbotException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseCommand_validCommand_success() throws ChatbotException {
        Command command = ChatbotParser.parseCommand("todo read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        try {
            ChatbotParser.parseCommand("Some unrecognized command");
            fail();
        } catch (ChatbotException e) {
            assertEquals(e.getMessage(), Message.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

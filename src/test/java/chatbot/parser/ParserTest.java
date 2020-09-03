package chatbot.parser;

import chatbot.commands.AddCommand;
import chatbot.commands.Command;

import chatbot.commands.InvalidCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommand_validCommand_success() {
        Command command = Parser.parse("todo read book");
        assertEquals(command instanceof AddCommand, true);
    }

    @Test
    public void parseCommand_invalidCommand_displayError() {
        Command command = Parser.parse("Some unrecognized command");
        assertEquals(command instanceof InvalidCommand, true);
    }

}

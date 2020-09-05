package nekochan.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nekochan.command.AddCommand;
import nekochan.command.Command;
import nekochan.command.CompleteCommand;
import nekochan.command.DeleteAllCommand;
import nekochan.command.DeleteCommand;
import nekochan.command.ExitCommand;
import nekochan.command.SearchCommand;
import nekochan.exceptions.NekoException;
import nekochan.exceptions.ParseNekoCommandException;
import nekochan.util.Messages;

public class ParserTest {

    @Test
    public void parse_lowercase_success() {
        String commandString = "exit";
        Command c = Parser.parse(commandString);
        assertNotNull(c);
    }

    @Test
    public void parse_uppercase_success() {
        String commandString = "EXIT";
        Command c = Parser.parse(commandString);
        assertNotNull(c);
    }

    @Test
    public void parse_sentenceCase_success() {
        String commandString = "Exit";
        Command c = Parser.parse(commandString);
        assertNotNull(c);
    }

    @Test
    public void parse_exit_success() {
        String commandString = "exit";
        Command c = Parser.parse(commandString);
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    public void parse_complete_success() {
        String commandString = "complete 1";
        Command c = Parser.parse(commandString);
        assertTrue(c instanceof CompleteCommand);
    }

    @Test
    public void parse_completeNoIndex_throwsException() {
        NekoException thrown = assertThrows(ParseNekoCommandException.class, () -> {
            String commandString = "complete";
            Command c = Parser.parse(commandString);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_COMPLETE_MISSING_ARGUMENT));
    }

    @Test
    public void parse_completeNonInteger_throwsException() {
        NekoException thrown = assertThrows(ParseNekoCommandException.class, () -> {
            String commandString = "complete 1.2";
            Command c = Parser.parse(commandString);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_COMPLETE_MISSING_ARGUMENT));
    }

    @Test
    public void parse_deleteSingle_success() {
        String commandString = "delete 1";
        Command c = Parser.parse(commandString);
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void parse_deleteAll_success() {
        String commandString = "delete all";
        Command c = Parser.parse(commandString);
        assertTrue(c instanceof DeleteAllCommand);
    }

    @Test
    public void parse_deleteAllCaseInsensitive_success() {
        String commandString = "delete AlL";
        Command c = Parser.parse(commandString);
        assertTrue(c instanceof DeleteAllCommand);
    }

    @Test
    public void parse_deleteNoIndex_throwsException() {
        NekoException thrown = assertThrows(ParseNekoCommandException.class, () -> {
            String commandString = "delete";
            Command c = Parser.parse(commandString);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_DELETE_MISSING_ARGUMENT));
    }

    @Test
    public void parse_deleteNonInteger_throwsException() {
        NekoException thrown = assertThrows(ParseNekoCommandException.class, () -> {
            String commandString = "delete 1.1";
            Command c = Parser.parse(commandString);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_DELETE_MISSING_ARGUMENT));
    }

    @Test
    public void parse_deadline_success() {
        String commandString = "deadline description by 1/2/2020";
        Command c = Parser.parse(commandString);
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parse_deadlineNoDetails_throwsException() {
        NekoException thrown = assertThrows(ParseNekoCommandException.class, () -> {
            String commandString = "deadline";
            Command c = Parser.parse(commandString);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_DEADLINE_MISSING_ARGUMENT));
    }

    @Test
    public void parse_todo_success() {
        String commandString = "todo description";
        Command c = Parser.parse(commandString);
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parse_todoNoDetails_throwsException() {
        NekoException thrown = assertThrows(ParseNekoCommandException.class, () -> {
            String commandString = "todo";
            Command c = Parser.parse(commandString);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_TODO_MISSING_ARGUMENT));
    }

    @Test
    public void parse_event_success() {
        String commandString = "event description from 2/2/2020 for all day";
        Command c = Parser.parse(commandString);
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parse_eventNoDetails_throwsException() {
        NekoException thrown = assertThrows(ParseNekoCommandException.class, () -> {
            String commandString = "event";
            Command c = Parser.parse(commandString);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_EVENT_MISSING_ARGUMENT));
    }

    @Test
    public void parse_search_success() {
        String commandString = "search something";
        Command c = Parser.parse(commandString);
        assertTrue(c instanceof SearchCommand);
    }

    @Test
    public void parse_searchNoParameter_throwsException() {
        NekoException thrown = assertThrows(ParseNekoCommandException.class, () -> {
            String commandString = "search";
            Command c = Parser.parse(commandString);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_SEARCH_MISSING_ARGUMENT));
    }
}

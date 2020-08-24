import commands.Command;
import data.exception.DukeIllegalCommandException;
import data.task.TaskList;
import org.junit.jupiter.api.Test;
import parser.Parser;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {

    @Test
    public void parse_emptyUserInput_returnsIllegalCommand() throws Exception {
        Parser parser = createDummyParser();
        assertThrows(DukeIllegalCommandException.class, () -> parser.parseCommand(""));
    }

    @Test
    public void parse_blankUserInput_returnsIllegalCommand() throws Exception {
        Parser parser = createDummyParser();
        assertThrows(DukeIllegalCommandException.class, () -> parser.parseCommand(" "));
    }

    @Test
    public void parse_unknownCommand_returnsIllegalCommand() throws Exception {
        Parser parser = createDummyParser();
        assertThrows(DukeIllegalCommandException.class, () -> parser.parseCommand("unknown blah blah"));
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws Exception {
        Parser parser = createDummyParser();
        Command output = parser.parseCommand("list");
        assertEquals("ListCommand", output.toString());
    }

    @Test
    public void parse_byeCommand_returnsByeCommand() throws Exception {
        Parser parser = createDummyParser();
        Command output = parser.parseCommand("bye");
        assertEquals("ByeCommand", output.toString());
    }

    @Test
    public void parse_toDoCommand_returnsToDoCommand() throws Exception {
        Parser parser = createDummyParser();
        Command output = parser.parseCommand("todo arguments");
        assertEquals("ToDoCommand", output.toString());
    }

    @Test
    public void parse_deadlineCommand_returnsDeadlineCommand() throws Exception {
        Parser parser = createDummyParser();
        Command output = parser.parseCommand("deadline arguments /by 2020-20-20 2020");
        assertEquals("DeadlineCommand", output.toString());
    }

    @Test
    public void parse_eventCommand_returnsEventCommand() throws Exception {
        Parser parser = createDummyParser();
        Command output = parser.parseCommand("event arguments /at 2020-20-20 2020-2020");
        assertEquals("EventCommand", output.toString());
    }

    @Test
    public void parse_doneCommand_returnsDoneCommand() throws Exception {
        Parser parser = createDummyParser();
        Command output = parser.parseCommand("done 1");
        assertEquals("DoneCommand", output.toString());
    }

    @Test
    public void parse_deleteCommand_returnsDeleteCommand() throws Exception {
        Parser parser = createDummyParser();
        Command output = parser.parseCommand("delete 1");
        assertEquals("DeleteCommand", output.toString());
    }

    @Test
    public void parse_unknownFollowUpCommand_returnsCommandKeyword() {
        String output = Parser.parseFollowUpCommand("unknowncommand");
        assertEquals("unknowncommand", output);
    }

    @Test
    public void parse_atFollowUpCommand_returnsCommandKeyword() {
        String output = Parser.parseFollowUpCommand("at");
        assertEquals("at", output);
    }

    @Test
    public void parse_byFollowUpCommand_returnsCommandKeyword() {
        String output = Parser.parseFollowUpCommand("by");
        assertEquals("by", output);
    }

    public Parser createDummyParser() {
        return new Parser(new TaskList(new ArrayList<>(), new Ui()), new Storage("data/dummyText.txt"), new Ui());
    }
}

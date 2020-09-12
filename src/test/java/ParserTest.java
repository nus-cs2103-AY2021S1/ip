import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.command.ByeCommand;
import main.java.com.jacob.duke.command.Command;
import main.java.com.jacob.duke.command.DeadlineCommand;
import main.java.com.jacob.duke.command.DeleteNoteCommand;
import main.java.com.jacob.duke.command.DoneCommand;
import main.java.com.jacob.duke.command.EventCommand;
import main.java.com.jacob.duke.command.FindCommand;
import main.java.com.jacob.duke.command.NoteCommand;
import main.java.com.jacob.duke.command.PrintFilteredListDateTimeCommand;
import main.java.com.jacob.duke.command.PrintListCommand;
import main.java.com.jacob.duke.command.PrintNoteListCommand;
import main.java.com.jacob.duke.command.TodoCommand;

import main.java.com.jacob.duke.io.Parser;



public class ParserTest {
    private static final Parser p = new Parser();
    //whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    @Test
    public void parse_returnBye_success() {
        Command byeC = new ByeCommand();
        try {
            Command bCParsed = p.parse("bye");
            assertEquals(byeC.getClass(), bCParsed.getClass());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnDeadline_success() {
        try {
            Command dLCommandParsed = p.parse("deadline return book /by 2019-10-15 1800");
            assertEquals(DeadlineCommand.class, dLCommandParsed.getClass());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnEvent_success() {
        try {
            Command eCommandParsed = p.parse("event return book /at 2019-10-15 1800");
            assertEquals(EventCommand.class, eCommandParsed.getClass());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnTodo_success() {
        try {
            Command tDCommandParsed = p.parse("todo 2312");
            assertEquals(TodoCommand.class, tDCommandParsed.getClass());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnList_success() {
        try {
            Command pCommandParsed = p.parse("list");
            assertEquals(PrintListCommand.class, pCommandParsed.getClass());

        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnListDue_success() {
        try {
            Command pFCommandParsed = p.parse("list-due 2019-10-15 1800");
            assertEquals(PrintFilteredListDateTimeCommand.class, pFCommandParsed.getClass());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnFind_success() {
        try {
            Command fCommandParsed = p.parse("find book");
            assertEquals(FindCommand.class, fCommandParsed.getClass());

        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnDone_success() {
        try {
            Command donCommandParsed = p.parse("done 3");
            assertEquals(DoneCommand.class, donCommandParsed.getClass());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnNote_success() {
        try {
            Command noteCommandParsed = p.parse("note What is the meaning of life? 42.");
            assertEquals(NoteCommand.class, noteCommandParsed.getClass());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnNoteDelete_success() {
        try {
            Command delNoteCommandParsed = p.parse("note-delete 3");
            assertEquals(DeleteNoteCommand.class, delNoteCommandParsed.getClass());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
    @Test
    public void parse_returnNoteList_success() {
        try {
            Command noteListCommandParsed = p.parse("note-list");
            assertEquals(PrintNoteListCommand.class, noteListCommandParsed.getClass());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
}

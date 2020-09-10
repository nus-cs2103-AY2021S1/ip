import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



public class ParserTest {

    @Test
    public void parseNotesCommand_addNewNote1() {
        try {
            Command addNoteCommand = Parser.parseNotesCommand("add t/hehe d/it's week 5 p/low");
            Command expectedNoteCommand = new AddNoteCommand("hehe", "it's week 5", Priority.LOW);
            assertEquals(expectedNoteCommand, addNoteCommand);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void parseNotesCommand_addNewNote2() {
        try {
            Command addNoteCommand = Parser.parseNotesCommand("add t/it's week 5.. d/cry :( priority/high");
            Command expectedNoteCommand = new AddNoteCommand("it's week 5..", "cry :(", Priority.HIGH);
            assertEquals(expectedNoteCommand, addNoteCommand);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void parseNotesCommand_listNotes() {
        try {
            Command listNotesCommand = Parser.parseNotesCommand("list");
            Command expectedNoteCommand = new ListNotesCommand();
            assertEquals(expectedNoteCommand, listNotesCommand);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void parseNotesCommand_invalidParameterName_exceptionThrown() {
        Executable code = () -> Parser.parseNotesCommand("add t/??? m/HAHAHAH p/high");
        assertThrows(Exception.class, code);
    }

    @Test
    public void parseNotesCommand_missingParameters_exceptionThrown() {
        Executable code = () -> Parser.parseNotesCommand("add t/HAHAHAH p/high");
        assertThrows(Exception.class, code);
    }

    @Test
    public void parseNotesCommand_wrongPriority_exceptionThrown() {
        Executable code = () -> Parser.parseNotesCommand("add t/HAHAHAH priority/veryHigh");
        assertThrows(Exception.class, code);
    }
}

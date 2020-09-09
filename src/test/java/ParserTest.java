import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseNotesCommand_addNewNote() {
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
}

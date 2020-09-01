import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.Parser;
import main.java.com.jacob.duke.command.*;


public class ParserTest {
    //whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    @Test
    public void parse_returnCorrectCommandType_success() {
        Parser p = new Parser();
        Command byeC = new ByeCommand();
        Command dLCommand = new DeadlineCommand("deadline");
        Command delCommand = new DeleteCommand("delete");
        Command tDCommand = new TodoCommand("todo");
        Command eCommand = new EventCommand("event");
        Command pCommand = new PrintListCommand();
        Command pFCommand = new PrintFilteredListDateTimeCommand("list-due");
        Command fCommand = new FindCommand("find");
        Command donCommand = new DoneCommand("done");
        try {
            Command bCParsed = p.parse("bye");
            assertEquals(byeC.getClass(), bCParsed.getClass());

            Command dLCommandParsed = p.parse("deadline");
            assertEquals(dLCommand.getClass(), dLCommandParsed.getClass());

            Command delCommandParsed = p.parse("delete");
            assertEquals(delCommand.getClass(), delCommandParsed.getClass());

            Command tDCommandParsed = p.parse("todo");
            assertEquals(tDCommand.getClass(), tDCommandParsed.getClass());

            Command eCommandParsed = p.parse("event");
            assertEquals(eCommand.getClass(), eCommandParsed.getClass());

            Command pCommandParsed = p.parse("list");
            assertEquals(pCommand.getClass(), pCommandParsed.getClass());

            Command pFCommandParsed = p.parse("list-due");
            assertEquals(pFCommand.getClass(), pFCommandParsed.getClass());

            Command fCommandParsed = p.parse("find");
            assertEquals(fCommand.getClass(), fCommandParsed.getClass());

            Command donCommandParsed = p.parse("done");
            assertEquals(donCommand.getClass(), donCommandParsed.getClass());

        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }


}

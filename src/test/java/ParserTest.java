import juke.Parser;
import juke.Ui;
import juke.command.TaskCommand;
import org.junit.jupiter.api.Test;
import juke.task.Event;
import juke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testTodoParsing() {
        Ui ui = new Ui();
        assertEquals(new TaskCommand(new Todo("read book")),
                ui.parseCommand("todo read book").toString());
    }

//    @Test
//    public void testByeParsing() {
//        Parser parser = new Parser();
//        assertEquals(new Command("bye").toString(), parser.commandHandler("bye").toString());
//    }
//
//    @Test
//    public void testEventParsing() {
//        Parser parser = new Parser();
//        assertEquals(new Command("event", new Event("Computing Day ", "2020-09-02")).toString(),
//                parser.commandHandler("event Computing Day /at 2020-09-02").toString());
//    }
//
//    @Test
//    public void testEmptyDescriptionErrorParsing() {
//        Parser parser = new Parser();
//        assertEquals(new Command("error", "Mate, you've gotta let me know what you're gonna be doing.").toString(),
//                parser.commandHandler("event").toString());
//    }

}

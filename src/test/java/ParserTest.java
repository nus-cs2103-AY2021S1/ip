import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testTodoParsing() {
        Parser parser = new Parser();
        assertEquals(new Command("todo", new Todo("read book")).toString(), parser.commandHandler("todo read book").toString());
    }

    @Test
    public void testByeParsing() {
        Parser parser = new Parser();
        assertEquals(new Command("bye").toString(), parser.commandHandler("bye").toString());
    }

    @Test
    public void testEventParsing() {
        Parser parser = new Parser();
        assertEquals(new Command("event", new Event("Computing Day ", "2020-09-02")).toString(),
                parser.commandHandler("event Computing Day /at 2020-09-02").toString());
    }

    @Test
    public void testEmptyDescriptionErrorParsing() {
        Parser parser = new Parser();
        assertEquals(new Command("error", "Mate, you've gotta let me know what you're gonna be doing.").toString(),
                parser.commandHandler("event").toString());
    }

}

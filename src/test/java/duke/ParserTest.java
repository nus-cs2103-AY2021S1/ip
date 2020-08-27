package duke;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void processInput_inputBye_parserExits() {
        Parser parser = new Parser(new TaskList(), new Storage(null), new Ui());
        try {
            assert !parser.processInput("bye");
        } catch (Exception ex) {
            assert false;
        }
    }
}

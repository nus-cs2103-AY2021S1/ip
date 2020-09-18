package duke;

import duke.commands.ExitCommand;
import duke.util.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void processInput_inputBye_parserExits() {
        Parser parser = new Parser();
        try {
            assert (parser.processInput("bye") instanceof ExitCommand);
        } catch (Exception ex) {
            assert false;
        }
    }
}

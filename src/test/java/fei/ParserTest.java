package fei;

import fei.exception.FeiException;
import fei.task.Deadline;
import fei.tool.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parserFormatTest() throws FeiException {
        String fullCmd = "D | false | research OP1 | today";
        Deadline ddl = new Deadline("research OP1", "today");
        assertEquals(Parser.parseTask(fullCmd).toString(), ddl.toString());
    }

}

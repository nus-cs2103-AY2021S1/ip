import org.junit.jupiter.api.Test;
import duke.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {




    @Test
    public void parserTest() {
        try {
            String[] arr = duke.Parser.parseInput("todo eat food");
            assertEquals("todo",
                    arr[0]);
            assertEquals("eat food",
                    arr[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

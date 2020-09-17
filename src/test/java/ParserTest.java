import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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

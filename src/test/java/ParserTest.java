import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void getSplit_todoCommand_splitQuery(){
        String[] splitActual = Parser.getSplit("todo someTask");
        String[] splitExpected = new String[]{"todo", "someTask"};
        assertArrayEquals(splitExpected,splitActual);
    }

    @Test
    public void getSplit_deadlineCommand_splitQuery(){
        String[] splitActual = Parser.getSplit("deadline A /by 12/05/2020 15:30");
        String[] splitExpected = new String[]{"deadline", "A", "/by", "12/05/2020", "15:30"};
        assertArrayEquals(splitExpected,splitActual);
    }

    @Test
    public void getSplit_eventCommand_splitQuery(){
        String[] splitActual = Parser.getSplit("event B /at 12/05/2020 15:30");
        String[] splitExpected = new String[]{"event", "B", "/at", "12/05/2020", "15:30"};
        assertArrayEquals(splitExpected,splitActual);
    }

    @Test
    public void getCommand_todoCommand_todoCommandExtracted(){
        String commandActual = Parser.getCommand(new String[]{"todo", "someTask"});
        String commandExpected = "todo";
        assertEquals(commandExpected, commandActual);
    }

    @Test
    public void removeCommandString_todoCommand_todoCommandRemoved(){
        String[] splitActual = Parser.removeCommandString(new String[]{"todo", "someTask"});
        String[] splitExpected = new String[]{"", "someTask"};
        assertArrayEquals(splitExpected,splitActual);
    }

    @Test
    public void concatenateStrArr_multipleStrings_noCommandString(){
        String concatActual = Parser.concatenateStrArr(new String[]{"", "someTask","that","is","nice"});
        String concatExpected = "someTask that is nice";
        assertEquals(concatExpected,concatActual);
    }
}

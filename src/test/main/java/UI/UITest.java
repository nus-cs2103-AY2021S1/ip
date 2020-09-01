package main.java.UI;

import Duke.UI.UI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITest {
    @Test
    public void enumTest(){
        assertEquals(UI.getMessage("GOODBYE_MSG"), "BARK BARK WOOF! (Welcome! Tell me your tasks and I'll he" +
                "lp you keep track of them!)");
    }
}
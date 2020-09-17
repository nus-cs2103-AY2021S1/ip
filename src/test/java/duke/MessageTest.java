package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MessageTest {

    @Test
    public void welcomeTest() {
        String actual = Message.getWelcome().getText();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String expected = "Hello from\n" + logo;
        assertEquals(expected, actual);
    }

    @Test
    public void showGoodbye() {
        String actual = Message.getGoodbye().getText();
        String expected = "Have a nice day.\n";
        assertEquals(expected, actual);
    }
}

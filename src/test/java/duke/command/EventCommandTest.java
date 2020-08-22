package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {
    @Test
    public void isExit_eventCommand_false() {
        EventCommand eventCommand = new EventCommand("test", "2020-08-22");
        assertEquals(eventCommand.isExit(), false);
    }
}

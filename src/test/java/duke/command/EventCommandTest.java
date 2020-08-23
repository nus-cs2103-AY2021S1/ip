package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventCommandTest {

    @Test
    public void isExit_eventCommand_false() {
        EventCommand eventCommand = new EventCommand("test", "2020-08-22");
        assertEquals(false, eventCommand.isExit());
    }
}

package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class EventCommandTest {

    @Test
    public void isExit_eventCommand_false() {
        EventCommand eventCommand = new EventCommand("test", "2020-08-22");
        assertFalse(eventCommand.isExit());
    }
}

package duke.command;

import main.java.duke.command.DeadlineCommand;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    void isExit_false() {
        assertEquals(false, new DeadlineCommand("deadline1", LocalDate.parse("2020-09-23")).isExit());
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import command.AddToListCommand;
import exception.DukeException;

public class AddToListCommandTest {
    @Test
    public void processDate_validDate_success() throws DukeException {
        AddToListCommand command = new AddToListCommand();
        LocalDateTime test = command.processDate("5/2/2020 1821");
        assertEquals(LocalDateTime.of(2020, 2, 5, 18, 21), test);
    }
}

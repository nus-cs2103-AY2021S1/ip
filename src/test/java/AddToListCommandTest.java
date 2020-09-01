import command.AddToListCommand;
import exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddToListCommandTest {
    @Test
    public void processDate_validDate_success() throws DukeException {
        AddToListCommand command = new AddToListCommand();
        LocalDateTime test = command.processDate("5/2/2020 1821");
        assertEquals(LocalDateTime.of(2020, 2, 5, 18, 21), test);
    }
}

import command.DeadlineCommand;
import command.TodoCommand;
import duke.Parser;
import exception.DukeException;
import exception.InvalidDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseDateTest1() throws InvalidDateException {
        assertEquals(Parser.parseDate("12/12/2019 0910"),  LocalDateTime.of(2019, 12, 12, 9, 10));
    }

    @Test
    public void parseDateTest2() throws InvalidDateException {
        assertEquals(Parser.parseDate("2/5/2020 1700"),  LocalDateTime.of(2020, 5, 2, 17, 0));
    }

    @Test
    public void parseTest1() throws DukeException {
        assertEquals(Parser.parse("todo Eat lunch"), new TodoCommand("Eat lunch"));
    }

    @Test
    public void parseTest2() throws DukeException {
        assertEquals(Parser.parse("deadline finish assignment /by 12/12/2019 0910"),
                new DeadlineCommand("finish assignment", LocalDateTime.of(2019, 12, 12, 9, 10)));
    }



}

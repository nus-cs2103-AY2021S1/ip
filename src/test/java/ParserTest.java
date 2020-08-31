import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import command.DeadlineCommand;
import command.TodoCommand;
import duke.Parser;
import exception.DukeException;
import exception.InvalidDateException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class ParserTest {

  @Test
  public void testDate1_validDate() throws InvalidDateException {
    assertEquals(Parser.parseDate("12/12/2019 0910"), LocalDateTime.of(2019, 12, 12, 9, 10));
  }

  @Test
  public void testDate2_validDate() throws InvalidDateException {
    assertEquals(Parser.parseDate("2/5/2020 1700"), LocalDateTime.of(2020, 5, 2, 17, 0));
  }

  @Test
  public void testDate_exceptionThrown() throws InvalidDateException {
    assertThrows(InvalidDateException.class, () -> {Parser.parseDate("2/14/2020 1700");});
  }

  @Test
  public void testParseCommand_validCommand() throws DukeException {
    assertEquals(Parser.parse("todo Eat lunch"), new TodoCommand("Eat lunch"));
  }

  @Test
  public void testParseCommand2_validCommand() throws DukeException {
    assertEquals(
        Parser.parse("deadline finish assignment /by 12/12/2019 0910"),
        new DeadlineCommand("finish assignment", LocalDateTime.of(2019, 12, 12, 9, 10)));
  }
}

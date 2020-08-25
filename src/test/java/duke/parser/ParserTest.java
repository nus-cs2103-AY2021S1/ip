package duke.parser;

import duke.dukeException.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
  @Test
  public void parseDetailsTest_correctStringWithTime_success() {
    String[] s;
    try {
      s = Parser.parseDetails("concert /at 02/04/20 19:30");
      assertArrayEquals(Parser.parseDetails("concert /at 02/04/20 19:30"), s);
    } catch (DukeException e) {
      fail();
    }
  }

  @Test
  public void parseDetailsTest_correctStringWithoutTime_success() {
    String[] s;
    try {
      s = Parser.parseDetails("concert /at 02/04/20");
      assertArrayEquals(Parser.parseDetails("concert /at 02/04/20"), s);
    } catch (DukeException e) {
      fail();
    }
  }

  @Test
  public void parseDetailsTest_missingDateTime_exceptionThrown() {
    String[] s;
    try {
      s = Parser.parseDetails("concert /at");
    } catch (DukeException e) {
      assertEquals("Yo! Details/Time are missing.", e.getMessage());
    }
  }

  @Test
  public void parseDetailsTest_wrongSyntax_exceptionThrown() {
    String[] s;
    try {
      s = Parser.parseDetails("concert");
    } catch (DukeException e) {
      assertEquals(
          "Yo! Command Syntax Error. '<Details> /by or /at <dd/MM/yy [HH:MM]>'", e.getMessage());
    }
  }
}

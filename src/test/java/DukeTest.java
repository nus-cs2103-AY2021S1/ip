

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
  @Test
  public void testGetStringAfterCharacter() {
    String output = Duke.getStringAfterCharacter("abc def", ' ');
    assertEquals("def", output);
  }

  @Test
  public void getStringAfterCharacter_testNoCharacter_emptyString() {
    String output = Duke.getStringAfterCharacter("abc", ' ');
    assertEquals("", output);
  }

  @Test
  public void testGetStringBeforeCharacter() {
    String output = Duke.getStringBeforeCharacter("abc def", ' ');
    assertEquals("abc", output);
  }

  @Test
  public void getStringBeforeCharacter_testNoCharacter_emptyString() {
    String output = Duke.getStringAfterCharacter("abc", ' ');
    assertEquals("", output);
  }
}

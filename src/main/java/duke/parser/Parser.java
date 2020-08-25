package duke.parser;

import duke.dukeException.DukeException;

import java.util.Arrays;
import java.util.Collections;

public class Parser {

  public static String[] parseDetails(String str) throws DukeException {
    String[] s;
    if (str.contains("/by")) {
      s = str.split(" /by ");
      if (s.length <= 1) {
        throw new DukeException("Yo! Details/Time are missing.");
      }
      return s;
    } else if (str.contains("/at")) {
      s = str.split(" /at ");
      if (s.length <= 1) {
        throw new DukeException("Yo! Details/Time are missing.");
      }
      return s;
    } else {
      throw new DukeException(
          "Yo! Command Syntax Error. '<Details> /by or /at <dd/MM/yy [HH:MM]>'");
    }
  }

  public static int[] parse(String str) throws DukeException {
    int[] intArray;
    if (str.isBlank()) {
      throw new DukeException("Yo! Enter the task numbers(s).");
    }
    intArray =
        Arrays.stream(str.split(" "))
            .sorted(Collections.reverseOrder())
            .mapToInt(Integer::parseInt)
            .toArray();
    return intArray;
  }
}

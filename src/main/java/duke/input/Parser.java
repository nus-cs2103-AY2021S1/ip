package duke.input;

import duke.duke.Duke;
import java.util.List;

/**
 * duke.input.Parser is the class that handles input parsing.
 * Given a string, it should determine what command to execute and execute it.
 */
public class Parser {

  public static final String CMD_TERMINATE = "bye";
  public static final String CMD_TASK_LIST = "list";

  /**
   * @param s command supplied
   * @return true if it's an exit command, ignoring case
   */
  public static boolean isExitCommand(String s) {
    return s.toLowerCase().equals(CMD_TERMINATE);
  }

  /**
   * @param s command supplied
   * @return a list of string (tasks) corresponding to command execution output
   */
  public static List<String> runCommand(String s) {
    String[] args = s.split("\\s+", 2);

    switch (args[0].toLowerCase()) {
      case CMD_TASK_LIST:
        return Duke.getInstance().getTasks();
      default:
        return Duke.getInstance().addTask(s);
    }
  }
}

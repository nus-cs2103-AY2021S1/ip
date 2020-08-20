package duke.input;

import duke.duke.Duke;
import java.util.List;

public class Parser {

  public static final String CMD_TERMINATE = "bye";
  public static final String CMD_TASK_LIST = "list";

  public static boolean isExitCommand(String s) {
    return s.toLowerCase().equals(CMD_TERMINATE);
  }

  public static List<String> runCommand(String s) {
    String[] args = s.split("\\s+", 2);

    switch (args[0].toLowerCase()) {
      case CMD_TASK_LIST:
        return Duke.getInstance().getTasks();
      default:
        return Duke.getInstance().addTask(args[1]);
    }
  }
}

package duke.input;

import duke.duke.Duke;
import duke.task.Task;
import java.util.List;

/**
 * duke.input.Parser is the class that handles input parsing. Given a string, it should determine
 * what command to execute and execute it.
 */
public class Parser {

  public static final String CMD_TERMINATE = "bye";
  public static final String CMD_TASK_LIST = "list";
  public static final String CMD_TASK_SET_DONE = "done";

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
  public static String runCommand(String s) {
    String[] args = s.split("\\s+", 2);

    List<Task> res;
    StringBuilder sb = new StringBuilder();

    switch (args[0].toLowerCase()) {
      case CMD_TASK_LIST: {
        res = Duke.getInstance().getTasks();
        sb.append("Here are the tasks in your list:\n");
        break;
      }
      case CMD_TASK_SET_DONE: {
        res = Duke.getInstance().editTask(Integer.parseInt(args[1]), true);
        sb.append("Nice! I've marked this task as done:\n");
        break;
      }
      default: {
        res = Duke.getInstance().addTask(s);
        sb.append("You've added task:\n");
      }
    }

    for (int i = 0; i < res.size(); ++i) {
      sb.append(String
          .format("[%s] %s", res.get(i).isDone() ? "DONE" : "NOT DONE",
              res.get(i).getDescription()));
      if (args[0].toLowerCase().equals(CMD_TASK_LIST)) {
        sb.append(String.format(" [id=%d]", i));
      }
      sb.append('\n');
    }

    return sb.toString();
  }
}

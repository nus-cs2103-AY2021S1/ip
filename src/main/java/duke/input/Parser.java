package duke.input;

import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ListTasksCommand;
import duke.command.TodoCommand;
import duke.exception.ParserException;

/**
 * duke.input.Parser is the class that handles input parsing. Given a string, it should determine
 * what command to execute and execute it.
 */
public class Parser {

  public static final String CMD_TERMINATE = "bye";
  public static final String CMD_TASK_LIST = "list";
  public static final String CMD_TASK_SET_DONE = "done";
  public static final String CMD_TASK_ADD_TODO = "todo";
  public static final String CMD_TASK_ADD_EVENT = "event";
  public static final String CMD_TASK_ADD_DEADLINE = "deadline";

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
  public static Command parseCommand(String s) throws ParserException {
    String[] args = s.split("\\s+", 2);

    switch (args[0].toLowerCase()) {
      case CMD_TASK_LIST:
        return new ListTasksCommand();
      case CMD_TASK_ADD_TODO: {
        String todo = parseTodo(args[1]);
        return new TodoCommand(todo);
      }
      case CMD_TASK_ADD_EVENT: {
        String[] inputArr = parseEvent(args[1]);
        return new EventCommand(inputArr[0], inputArr[1]);
      }
      case CMD_TASK_ADD_DEADLINE: {
        String[] inputArr = parseDeadline(args[1]);
        return new DeadlineCommand(inputArr[0], inputArr[1]);
      }
      case CMD_TASK_SET_DONE: {
        int[] taskIdList = parseMarkDone(args[1]);
        return new CompleteTaskCommand(taskIdList[0]);
//        for (int taskId : taskIdList) {
//          Command command = new CompleteTaskCommand(taskId);
//          command.execute(Duke.getInstance());
//        }
      }
      default:
        throw new ParserException("No such command!");
    }
  }

  public static String parseTodo(String s) throws ParserException {
    if (s.length() <= 0) {
      throw new ParserException("Todo format error.");
    }
    return s;
  }

  public static String[] parseEvent(String s) throws ParserException {
    String[] arr = s.split(" /at ");
    if (arr.length <= 1) {
      throw new ParserException("Event format error.");
    }
    return arr;
  }

  public static String[] parseDeadline(String s) throws ParserException {
    String[] arr = s.split(" /by ");
    if (arr.length <= 1) {
      throw new ParserException("Deadline format error.");
    }
    return arr;
  }

  public static int[] parseMarkDone(String s) throws ParserException {
    String[] arr = s.split(" ");
    if (arr.length <= 0) {
      throw new ParserException("Mark tasks done format error.");
    }
    int[] taskIdList = new int[arr.length];
    for (int i = 0; i < arr.length; ++i) {
      taskIdList[i] = Integer.parseInt(arr[i]);
    }
    return taskIdList;
  }
}

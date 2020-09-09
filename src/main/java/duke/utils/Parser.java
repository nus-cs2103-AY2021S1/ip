package duke.utils;

import duke.command.Command;
import duke.command.CommandEnum;
import duke.command.CompleteTaskCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EventCommand;
import duke.command.ListTasksCommand;
import duke.command.TodoCommand;
import duke.exception.ParserException;

/**
 * duke.utils.Parser is the class that handles input parsing. Given a string, it should determine
 * what command to execute and execute it.
 */
public class Parser {
  /**
   * @param s command supplied
   * @return true if it's an exit command, ignoring case
   */
  public static boolean isTerminate(String s) {
    return CommandEnum.valueOf(s.toUpperCase()).equals(CommandEnum.TERMINATE);
  }

  /**
   * @param s command supplied
   * @return a list of string (tasks) corresponding to command execution output
   */
  public static Command parseCommand(String s) throws ParserException {
    String[] args = s.split("\\s+", 2);
    CommandEnum cmdEnum;
    try {
      cmdEnum = CommandEnum.valueOf(args[0].toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new ParserException("Invalid command.");
    }

    switch (cmdEnum) {
      case TASK_LIST:
        return new ListTasksCommand();
      case TASK_ADD_TODO: {
        String todo = parseTodo(args[1]);
        return new TodoCommand(todo);
      }
      case TASK_ADD_EVENT: {
        String[] inputArr = parseEvent(args[1]);
        return new EventCommand(inputArr[0], inputArr[1]);
      }
      case TASK_ADD_DEADLINE: {
        String[] inputArr = parseDeadline(args[1]);
        return new DeadlineCommand(inputArr[0], inputArr[1]);
      }
      case TASK_SET_DONE: {
        int[] taskIdList = parseTaskIds(args[1]);
        return new CompleteTaskCommand(taskIdList[0]);
//        for (int taskId : taskIdList) {
//          Command command = new CompleteTaskCommand(taskId);
//          command.execute(Duke.getInstance());
//        }
      }
      case TASK_DELETE: {
        int[] taskIdList = parseTaskIds(args[1]);
        return new DeleteTaskCommand(taskIdList[0]);
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

  public static int[] parseTaskIds(String s) throws ParserException {
    String[] arr = s.split(" ");
    if (arr.length <= 0) {
      throw new ParserException("Task numbers format error.");
    }
    int[] taskIdList = new int[arr.length];
    for (int i = 0; i < arr.length; ++i) {
      taskIdList[i] = Integer.parseInt(arr[i]);
    }
    return taskIdList;
  }
}

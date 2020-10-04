package duke.utils;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandEnum;
import duke.command.CompleteTaskCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EventCommand;
import duke.command.ListTasksCommand;
import duke.command.TodoCommand;
import duke.exception.ParserException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * duke.utils.Parser is the class that handles input parsing. Given a string, it should determine
 * what command to execute and execute it.
 */
public class Parser {

  private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

  /**
   * @param s command supplied
   * @return a list of string (tasks) corresponding to command execution output
   */
  public static Command parseCommand(String s) throws ParserException {
    String[] args = s.trim().split("\\s+", 2);
    CommandEnum cmdEnum;
    try {
      cmdEnum = CommandEnum.valueOf(args[0].toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new ParserException("Invalid command.");
    }

    switch (cmdEnum) {
      case BYE:
        return new ByeCommand();
      case LIST:
        return new ListTasksCommand();
      case TODO: {
        String todo = parseTodo(args[1]);
        return new TodoCommand(todo);
      }
      case EVENT: {
        String[] inputArr = parseEvent(args[1]);
        return new EventCommand(inputArr[0], inputArr[1]);
      }
      case DEADLINE: {
        String[] inputArr = parseDeadline(args[1]);
        return new DeadlineCommand(inputArr[0], inputArr[1]);
      } case DONE: {
        int[] taskIdList = parseTaskIds(args[1]);
        return new CompleteTaskCommand(taskIdList[0]);
      }
      case DELETE: {
        int[] taskIdList = parseTaskIds(args[1]);
        return new DeleteTaskCommand(taskIdList[0]);
      }
      default:
        throw new ParserException("No such command!");
    }
  }

  public static LocalDateTime parseDateTime(String dateTimeString) throws ParserException {
    try {
      return LocalDateTime.parse(dateTimeString, DT_FORMAT);
    } catch (DateTimeParseException e) {
      throw new ParserException("DateTime format error.");
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

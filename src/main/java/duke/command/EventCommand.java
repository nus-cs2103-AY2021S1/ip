package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Event command type. Create event task and add into task arraylist.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class EventCommand extends Command {

  private final String fullCommand;

  /**
   * Class constructor. Extract task details from full command.
   *
   * @param fullCommand full command input by user.
   */
  public EventCommand(String fullCommand) {
    this.fullCommand = fullCommand;
  }

  /**
   * Create event task and add to task arraylist. Convert details like data time to dateTime *
   * format. Write to file.
   *
   * @param taskList arraylist of task.
   * @param ui ui class for print.
   * @param storage storage for read, write to file.
   * @throws DukeException Exception for wrong date or time format.
   */
  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
    ui.showLine();
    if (!fullCommand.contains(" ")) {
      throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
    }
    String details = fullCommand.substring(fullCommand.indexOf(" ")).trim();
    if (!fullCommand.contains("/at")) {
      throw new DukeException("☹ OOPS!!! The date & time of a event cannot be empty.");
    }
    String description = details.substring(0, details.indexOf("/at")).trim();
    String at = details.substring(details.indexOf("/at") + 3).trim();

    String[] inputDateTime = at.split(" ");
    String[] date = inputDateTime[0].split("[/\\\\]|-");
    LocalDate localDate = dateToLocalDate(date);

    LocalTime startLocalTime = LocalTime.of(0, 0);
    LocalTime endLocalTime = LocalTime.of(23, 59);
    if (inputDateTime.length > 1) {
      String[] timeArr = inputDateTime[1].split("-");
      if (timeArr.length > 1) {
        endLocalTime = timeToLocalTime(timeArr[1]);
      }
      startLocalTime = timeToLocalTime(timeArr[0]);
    }
    LocalDateTime startLocalDateTime = LocalDateTime.of(localDate, startLocalTime);
    LocalDateTime endLocalDateTime = LocalDateTime.of(localDate, endLocalTime);

    System.out.println("Got it. I've added this task:");
    taskList.addTask(new Event(description, startLocalDateTime, endLocalDateTime));
    System.out.println("\t" + taskList.retrieveTask(taskList.sizeOfList() - 1));
    System.out.printf("Now you have %o tasks in list.\n", taskList.sizeOfList());
    ui.showLine();

    storage.write(taskList);
  }

  private static LocalDate dateToLocalDate(String[] date) throws DukeException {
    LocalDate localDate;
    if (date.length == 3) {
      int day = Integer.parseInt(date[0]);
      int month = Integer.parseInt(date[1]);
      int year = Integer.parseInt(date[2]);
      localDate = LocalDate.of(year, month, day);
    } else {
      throw new DukeException("Error with input date!");
    }
    return localDate;
  }

  private static LocalTime timeToLocalTime(String time) throws DukeException {
    int timeLength = time.length();
    LocalTime localTime;
    if (timeLength == 4) {
      int hour = Integer.parseInt(time.substring(0, 2));
      int minute = Integer.parseInt(time.substring(2, 4));
      localTime = LocalTime.of(hour, minute);
    } else if (timeLength == 3) {
      int hour = Integer.parseInt(String.valueOf(time.charAt(0)));
      int minute = Integer.parseInt(time.substring(1, 3));
      localTime = LocalTime.of(hour, minute);
    } else {
      throw new DukeException("Error with input time");
    }
    return localTime;
  }

  /**
   * Indicator for application to end.
   *
   * @return false.
   */
  @Override
  public boolean isExit() {
    return false;
  }
}

package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Deadline;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Deadline command type. Create deadline task and add into task arraylist.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class DeadlineCommand extends Command {

  private final String fullCommand;

  /**
   * Class constructor. Extract task details from full command.
   *
   * @param fullCommand full command input by user.
   */
  public DeadlineCommand(String fullCommand) {
    this.fullCommand = fullCommand;
  }

  /**
   * Create deadline task and add to task arraylist. Convert details like data time to dateTime
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
      throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
    }
    String details = fullCommand.substring(fullCommand.indexOf(" ")).trim();
    if (!fullCommand.contains("/by")) {
      throw new DukeException("☹ OOPS!!! The date & time of a deadline cannot be empty.");
    }
    String description = details.substring(0, details.indexOf("/by")).trim();
    String by = details.substring(details.indexOf("/by") + 3).trim();

    String[] inputDateTime = by.split(" ");
    String[] date = inputDateTime[0].split("[/\\\\]|-");
    LocalDate localDate = dateToLocalDate(date);

    LocalTime localTime = LocalTime.of(0, 0);
    if (inputDateTime.length > 1) {
      int timeLength = inputDateTime[1].length();
      if (timeLength == 4) {
        int hour = Integer.parseInt(inputDateTime[1].substring(0, 2));
        int minute = Integer.parseInt(inputDateTime[1].substring(2, 4));
        localTime = LocalTime.of(hour, minute);
      } else if (timeLength == 3) {
        int hour = Integer.parseInt(String.valueOf(inputDateTime[1].charAt(0)));
        int minute = Integer.parseInt(inputDateTime[1].substring(1, 3));
        localTime = LocalTime.of(hour, minute);
      } else {
        throw new DukeException("Error with input time");
      }
    }

    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

    System.out.println("Got it. I've added this task:");
    taskList.addTask(new Deadline(description, localDateTime));
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

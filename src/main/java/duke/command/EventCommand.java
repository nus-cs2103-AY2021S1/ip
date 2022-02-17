package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.common.DukeException;
import duke.common.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Event;

/**
 * Event command type.
 * Creates event task and add into task arraylist.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class EventCommand extends Command {

    private final String fullCommand;

    /**
     * Class constructor.
     * Extracts task details from full command.
     *
     * @param fullCommand full command input by user.
     */
    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;

    }

    /**
     * Creates event task and add to task arraylist.
     * Converts details like data time to dateTime format.
     * Writes to file.
     *
     * @param taskList arraylist of task.
     * @param ui       ui class for print.
     * @param storage  storage for read, write to file.
     * @throws DukeException Exception for wrong date or time format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (!fullCommand.contains(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        if (!fullCommand.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The date & time of a event cannot be empty.");
        }

        String details = fullCommand.substring(fullCommand.indexOf(" ")).trim();
        String description = details.substring(0, details.indexOf("/at")).trim();
        String at = details.substring(details.indexOf("/at") + 3).trim();

        String[] inputDateTime = at.split(" ");
        String[] date = inputDateTime[0].split("[/\\\\]|-");

        LocalDate localDate = convertDateToLocalDate(date);
        LocalTime startLocalTime = LocalTime.of(0, 0);
        LocalTime endLocalTime = LocalTime.of(23, 59);
        if (inputDateTime.length > 1) {
            String[] timeArr = inputDateTime[1].split("-");
            if (timeArr.length > 1) {
                endLocalTime = convertTimeToLocalTime(timeArr[1]);
            }
            startLocalTime = convertTimeToLocalTime(timeArr[0]);
        }

        LocalDateTime startLocalDateTime = LocalDateTime.of(localDate, startLocalTime);
        LocalDateTime endLocalDateTime = LocalDateTime.of(localDate, endLocalTime);
        taskList.addTask(new Event(description, startLocalDateTime, endLocalDateTime));

        String resultSb = String.format("%s\n %s\n", ui.showLine(), "Got it. I've added this task:") + "\t"
                + taskList.retrieveTask(taskList.sizeOfList() - 1) + String.format(
                "\nNow you have %o tasks in list.\n", taskList.sizeOfList()) + ui.showLine();
        Ui.printString(resultSb);

        storage.write(taskList);
    }

    private static LocalDate convertDateToLocalDate(String[] date) throws DukeException {
        LocalDate localDate;
        if (date.length == 3) {
            int day = Integer.parseInt(date[0]);
            assert day < 32
                    : "Not Valid Date";
            assert day > 0
                    : "Not Valid Date";
            int month = Integer.parseInt(date[1]);
            assert month < 13
                    : "Not Valid Month";
            assert month > 0
                    : "Not Valid Month";
            int year = Integer.parseInt(date[2]);
            assert year > 0
                    : "Not Valid Year";
            localDate = LocalDate.of(year, month, day);
        } else {
            throw new DukeException("Error with input date!");
        }
        return localDate;
    }

    private static LocalTime convertTimeToLocalTime(String time) throws DukeException {
        int timeLength = time.length();
        LocalTime localTime;
        if (timeLength == 4) {
            int hour = Integer.parseInt(time.substring(0, 2));
            assert hour >= 0
                    : "Not Valid Hour";
            assert hour <= 23
                    : "Not Valid Hour";
            int minute = Integer.parseInt(time.substring(2, 4));
            assert minute >= 0
                    : "Not Valid Minutes";
            assert minute < 60
                    : "Not Valid Minutes";
            localTime = LocalTime.of(hour, minute);
        } else if (timeLength == 3) {
            int hour = Integer.parseInt(String.valueOf(time.charAt(0)));
            assert hour >= 0
                    : "Not Valid Hour";
            assert hour < 10
                    : "Not Valid Hour";
            int minute = Integer.parseInt(time.substring(1, 3));
            assert minute >= 0
                    : "Not Valid Minutes";
            assert minute < 60
                    : "Not Valid Minutes";
            localTime = LocalTime.of(hour, minute);
        } else {
            throw new DukeException("Error with input time");
        }
        return localTime;
    }

    /**
     * Returns indications for application to end.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

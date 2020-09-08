import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents an event command.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class EventCommand extends Command {

    private final String command;
    private final int EVENT_LENGTH = 5;
    private final int TIME_LENGTH = 4;
    private final String AT_STRING = "/at";

    public EventCommand(String command) {
        this.command = command;
    }

    private static String formatDate(String dateString) {
        if (dateString.contains("/")) {
            dateString = dateString.replaceAll("\\/", "-");
        }
        String[] dateStringArr = dateString.split("-");
        dateString = "";
        for (int i = 0; i < dateStringArr.length; i++) {
            if (dateStringArr[i].length() < 2) {
                dateStringArr[i] = "0" + dateStringArr[i];
            }
            if (i > 0) {
                dateString = dateString + "-" + dateStringArr[i];
            } else {
                dateString = dateStringArr[i];
            }
        }
        return dateString;
    }

    private String initiateAddEvent(String desc, LocalDate dateObject,
                                       String timeString, TaskList taskList) throws DukeException {
        try {
            int time = Integer.parseInt(timeString);
            if (time >= 0000 && time <= 2359) {
                return taskList.addEvent(desc, dateObject, timeString);
            } else {
                throw new DukeException("Please enter a "
                        + "valid time between 0000 and 2359");
            }
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please input the "
                    + "time in the right format (eg. 1800)");
        }
    }
    private String parseTime(String desc, String dateTime,
                             TaskList taskList) throws DukeException {
        String[] dateTimeArray = dateTime.split(" ");
        String dateString = dateTimeArray[0].trim();
        String timeString = dateTimeArray[1].trim();
        dateString = formatDate(dateString);
        LocalDate dateObject = LocalDate.parse(dateString);
        if (timeString.length() == TIME_LENGTH) {
            return initiateAddEvent(desc, dateObject, timeString, taskList);
        } else {
            return taskList.addEvent(desc, dateObject);
        }
    }

    private String parseDate(String desc, String date,
                             TaskList taskList) {
        String formattedDate = formatDate(date);
        LocalDate dateObject = LocalDate.parse(formattedDate);
        return taskList.addEvent(desc, dateObject);
    }

    private String parseDateTime(String desc, String dateTime,
                                 TaskList taskList) throws DukeException {
        boolean containsTimeInput = dateTime.contains(" ");
        try {
            if (containsTimeInput) {
                return parseTime(desc, dateTime, taskList);
            } else {
                return parseDate(desc, dateTime, taskList);
            }
        } catch (DateTimeException dte) {
            throw new DukeException("Please enter your date "
                    + "and time in the format yyyy-mm-dd hhmm "
                    + "(eg. 2020-08-23 1800)");
        }
    }

    private String splitDescDateTime(String descDateTimeString, TaskList taskList) throws DukeException {
        String[] descDateTimeArray = descDateTimeString.split(AT_STRING);
        String desc = descDateTimeArray[0].trim();
        String dateTime = descDateTimeArray[1].trim();
        return parseDateTime(desc, dateTime, taskList);
    }

    /**
     * Executes the command.
     *
     * @param storage   The storage object that handles interactions with the
     *                  local file.
     * @param ui        The UI object that handles interactions with the user.
     * @param taskList  The current list of tasks.
     * @return          The string to be displayed to the user.
     * @throws DukeException    When the input command is invalid.
     */
    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        String descDateTimeString = this.command.substring(EVENT_LENGTH).trim();
        boolean isDescDateTimeEmpty = descDateTimeString.isEmpty();
        boolean containsDesc = descDateTimeString.contains(AT_STRING) &&
                !descDateTimeString.startsWith(AT_STRING);
        boolean containsDateTime = !descDateTimeString.endsWith(AT_STRING);

        if (!isDescDateTimeEmpty && containsDesc && containsDateTime) {
            return splitDescDateTime(descDateTimeString, taskList);
        } else {
            throw new DukeException("Please enter a valid event");
        }
    }
}

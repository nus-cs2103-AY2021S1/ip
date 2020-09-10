import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class Parser {
    /**
     * Gets the relevant task id from command.
     *
     * @param command User command.
     * @return Id of the task to be modified/deleted.
     */
    public static Integer[] getTaskIds(String command) {
        String idString = command.split(" ", 2)[1];
        String[] ids = idString.split(",");
        return Arrays.stream(ids)
                     .map(x -> Integer.parseInt(x) - 1)
                     .sorted(Comparator.reverseOrder())
                     .toArray(Integer[]::new);
    }

    /**
     * Parses the string representation of a task to its Task type.
     *
     * @param taskString line in duke.txt file.
     * @return Task to be included in task list.
     * @throws DukeException If Deadline/Event tasks cannot be created.
     */
    public static Task getTask(String taskString) throws DukeException {
        char taskType = taskString.charAt(1);
        assert taskType == 'T' || taskType == 'D' || taskType == 'E';
        char completionStatus = taskString.charAt(4);
        assert completionStatus == '0' || completionStatus == '1';
        boolean isTaskDone = completionStatus == '1';
        Task result;

        if (taskType == 'T') {
            String taskDesc = taskString.substring(7);
            result = new ToDo(taskDesc);
        } else {
            String[] strArr = taskString.split(taskType == 'D' ? " \\(by: " : " \\(at: ");
            String taskDesc = strArr[0].substring(7);
            String time = strArr[1].substring(0, strArr[1].length() - 1);
            result = taskType == 'D' ? new Deadline(taskDesc, time) : new Event(taskDesc, time);
        }

        if (isTaskDone) {
            result.markAsDone();
        }
        return result;
    }

    /**
     * Gets the action keyword of user command.
     *
     * @param command User command.
     * @return Action keyword.
     */
    public static String getAction(String command) {
        String[] commandList = command.split(" ", 2);
        return commandList[0].toLowerCase();
    }

    /**
     * Gets the details for the creation of tasks.
     *
     * @param command User command.
     * @return Details of the task.
     */
    public static String getDetail(String command) {
        String[] commandList = command.split(" ", 2);
        return commandList[1];
    }

    /**
     * Gets the description for the creation of a deadline task.
     *
     * @param command User command.
     * @return Deadline task description.
     */
    public static String getDeadlineDesc(String command) {
        return getDetail(command).split(" /by ")[0];
    }

    /**
     * Gets the deadline for the creation of a deadline task.
     *
     * @param command User command.
     * @return Deadline of task.
     */
    public static String getBy(String command) {
        return getDetail(command).split(" /by ")[1];
    }

    /**
     * Gets the description for the creation of an event task.
     *
     * @param command User command.
     * @return Event task description.
     */
    public static String getEventDesc(String command) {
        return getDetail(command).split(" /at ")[0];
    }

    /**
     * Gets the timing for the creation of an event task.
     *
     * @param command User command.
     * @return Event duration.
     */
    public static String getAt(String command) {
        return getDetail(command).split(" /at ")[1];
    }

    /**
     * Parses the string representation of datetime to LocalDateTime type.
     *
     * @param by Deadline of task.
     * @return LocalDateTime of deadline.
     */
    public static LocalDateTime getLocalDateTimeBy(String by) {
        String parsableBy = by.replace(" ", "T");
        return LocalDateTime.parse(parsableBy);
    }

    /**
     * Parses the string representation of the start time to LocalDateTime type.
     *
     * @param at Event duration.
     * @return LocalDateTime of start time.
     */
    public static LocalDateTime getLocalDateTimeStart(String at) {
        String[] dateTimeArr = at.split(" to ");
        String parsableStart = dateTimeArr[0].replace(" ", "T");
        return LocalDateTime.parse(parsableStart);
    }

    /**
     * Parses the string representation of the end time to LocalDateTime type.
     * @param at Event duration.
     * @return LocalDateTime of end time.
     */
    public static LocalDateTime getLocalDateTimeEnd(String at) {
        String[] dateTimeArr = at.split(" to ");
        String parsableEnd = dateTimeArr[1].replace(" ", "T");
        return LocalDateTime.parse(parsableEnd);
    }

    /**
     * Parses the LocalDateTime representation of the start time to String type.
     *
     * @param start Start datetime of the Event.
     * @return String of start datetime of the Event.
     */
    public static String getStringStart(LocalDateTime start) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
        return start.format(formatter);
    }

    /**
     * Parses the LocalDateTime representation of the end time to String type.
     *
     * @param end End datetime of the Event.
     * @return String of end datetime of the Event.
     */
    public static String getStringEnd(LocalDateTime end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
        return end.format(formatter);
    }

    /**
     * Parses the LocalDateTime representation of the deadline to String type.
     * @param by Deadline of the Task.
     * @return String of the deadline.
     */
    public static String getStringBy(LocalDateTime by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
        return by.format(formatter);

    }

    /**
     * Retrieves the day of the deadline.
     *
     * @param by Deadline of the Task.
     * @return Day of the deadline.
     */
    public static String getDay(LocalDateTime by) {
        return by.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("en"));
    }

    /**
     * Retrieves the day of the start of the Event.
     *
     * @param start Start datetime of the Event.
     * @return Day of the start of the Event.
     */
    public static String getStartDay(LocalDateTime start) {
        return start.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("en"));
    }

    /**
     * Retrieves the day of the end of the Event.
     * @param end End datetime of the Event.
     * @return Day of the end of the Event.
     */
    public static String getEndDay(LocalDateTime end) {
        return end.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("en"));
    }
}

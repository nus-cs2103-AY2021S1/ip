package duke.storage;

import static duke.storage.Storage.TASK_DATA_ARGS_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import duke.exceptions.IllegalValueException;
import duke.tasks.Task;
import duke.tasks.TaskDeadline;
import duke.tasks.TaskEvent;
import duke.tasks.TaskList;
import duke.tasks.TaskToDo;

/**
 * Transforms String input from save file
 * into {@code Tasks}.
 * <p>
 * Methods contained are static.
 * </p>
 */
public class TaskDecoder {

    /**
     * Decodes a list of Strings into a {@code TaskList}.
     * @param dataLines input from save file.
     * @return TaskList object filled with decoded tasks.
     * @throws IllegalValueException if input is not in the right format.
     */
    public static TaskList decodeTasksFromSave(List<String> dataLines) throws IllegalValueException {
        List<Task> output = new ArrayList<>();
        for (String line : dataLines) {
            output.add(decodeTaskFromString(line));
        }
        return new TaskList(output);
    }

    /**
     * Decodes a String into a {@code Task}.
     * @param encodedTask task in String format.
     * @return {@code Task} object.
     * @throws IllegalValueException if input is not in the right format.
     */
    private static Task decodeTaskFromString(String encodedTask)
            throws IllegalValueException {
        Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new IllegalValueException("Encoded task in invalid format. Unable to decode." + encodedTask);
        }
        String command = matcher.group("eventType");
        boolean isDone = matcher.group("isDone").equals("0") ? false : true;
        String description = matcher.group("description");
        if (command.equals("T")) {
            return new TaskToDo(description, isDone);
        } else if (command.equals("E")) {
            String pattern = ("(\\S+)(\\s)(\\d{4})([-])(\\d{4})");
            String date = matcher.group("date");
            LocalDate eventDate = LocalDate.parse(date.replaceAll(pattern, "$1"));
            String startTemp = date.replaceAll(pattern, "$3");
            String endTemp = date.replaceAll(pattern, "$5");
            LocalTime startTime = LocalTime.parse(startTemp.substring(0, 2) + ":"
                    + startTemp.substring(2, 4));
            LocalTime endTime = LocalTime.parse(endTemp.substring(0, 2) + ":"
                    + endTemp.substring(2, 4));

            Task taskEvent = new TaskEvent(description, eventDate, startTime, endTime);
            if (isDone) {
                taskEvent.setDone();
            }
            return taskEvent;
        } else if (command.equals("D")) {
            String date = matcher.group("date");
            String pattern = ("([\\S]+)(\\s)([\\S]+)");
            LocalDate deadlineDate = LocalDate.parse(date.replaceAll(pattern, "$1"));
            String timeTemp = date.replaceAll(pattern, "$3");
            LocalTime deadlineTime = LocalTime.parse(timeTemp.substring(0, 2)
                    + ":" + timeTemp.substring(2, 4));
            LocalDateTime deadline = deadlineDate.atTime(deadlineTime);
            Task taskDeadline = new TaskDeadline(description, deadline);
            if (isDone) {
                taskDeadline.setDone();
            }
            return taskDeadline;
        } else {
            throw new AssertionError("Regex is not checking properly");
        }
    }
}

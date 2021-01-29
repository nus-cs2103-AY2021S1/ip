package duke.storage;

import static duke.storage.Storage.TASK_DATA_ARGS_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        boolean isDone = !matcher.group("isDone").equals("0");
        String description = matcher.group("description");
        assert !command.equals("") && !matcher.group("isDone").equals("") && !description.equals("")
                : "Save field is empty";
        switch (command) {
        case "T":
            return new TaskToDo(description, isDone);
        case "E": {
            String pattern = ("(\\S+)(\\s)(\\d{4})([-])(\\d{4})");
            String date = matcher.group("date");
            assert Pattern.compile(pattern).matcher(date).matches() : "Date input for " + description + " is incorrect";
            LocalDate eventDate = LocalDate.parse(date.replaceAll(pattern, "$1"));
            String startTemp = date.replaceAll(pattern, "$3");
            String endTemp = date.replaceAll(pattern, "$5");
            LocalTime startTime = LocalTime.parse(startTemp, DateTimeFormatter.ofPattern("Hmm"));
            LocalTime endTime = LocalTime.parse(endTemp, DateTimeFormatter.ofPattern("Hmm"));
            Task taskEvent = new TaskEvent(description, eventDate, startTime, endTime);
            if (isDone) {
                taskEvent.setDone();
            }
            return taskEvent;
        }
        case "D": {
            String date = matcher.group("date");
            String pattern = ("([\\S]+)(\\s)([\\S]+)");
            assert Pattern.compile(pattern).matcher(date).matches() : "Date input for " + description + " is incorrect";
            LocalDate deadlineDate = LocalDate.parse(date.replaceAll(pattern, "$1"));
            String timeTemp = date.replaceAll(pattern, "$3");
            LocalTime deadlineTime = LocalTime.parse(timeTemp, DateTimeFormatter.ofPattern("Hmm"));
            LocalDateTime deadline = deadlineDate.atTime(deadlineTime);
            Task taskDeadline = new TaskDeadline(description, deadline);
            if (isDone) {
                taskDeadline.setDone();
            }
            return taskDeadline;
        }
        default:
            throw new AssertionError("Regex is not checking properly");
        }
    }
}

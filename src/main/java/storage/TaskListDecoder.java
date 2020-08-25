package storage;

import data.TaskList;
import data.task.Deadline;
import data.task.Event;
import data.task.Task;
import data.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Decodes the storage data file into a data.tasks.Task List object.
public class TaskListDecoder {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a");
    private static final Pattern TASK_DATA_ARGS_FORMAT = Pattern.compile("(?<taskType>[TDE]+)/(?<isDone>[XO])/(?<arguments>.*)");
    private static final Pattern TODO_DATA_ARGS_FORMAT = Pattern.compile("(?<description>.*)");
    private static final Pattern DEADLINE_DATA_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)/(?<deadline>.*)");
    private static final Pattern EVENT_DATA_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)/(?<dateTime>.*)");

    public static TaskList decodeTaskList(File file) throws FileNotFoundException, Storage.StorageOperationException {
        Scanner s = new Scanner(file);
        List<Task> tasksList = new ArrayList<>();
        while (s.hasNext()) {
            String encodedTask = s.nextLine();
            tasksList.add(decodeTaskFromString(encodedTask));
        }
        s.close();
        return new TaskList(tasksList);
    }

    private static Task decodeTaskFromString(String encodedTask) throws Storage.StorageOperationException {
        final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new Storage.StorageOperationException("Encoded data.task in invalid format. Unable to decode.");
        }

        final String taskType = matcher.group("taskType");
        final boolean isDone = isDonePrefix(matcher.group("isDone"));
        final String arguments = matcher.group("arguments");

        switch (taskType) {
            case "T":
                return addTodo(isDone, arguments);
            case "D":
                return addDeadline(isDone, arguments);
            case "E":
                return addEvent(isDone, arguments);
            default:
                throw new Storage.StorageOperationException("Encoded data.task in invalid format. Unable to decode.");
        }
    }

    private static boolean isDonePrefix(String matchedPrefix) {
        return "O".equals(matchedPrefix);
    }

    private static Todo addTodo(boolean isDone, String arguments) throws Storage.StorageOperationException {
        final Matcher matcher = TODO_DATA_ARGS_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new Storage.StorageOperationException("Encoded todo in invalid format. Unable to decode.");
        }
        return new Todo(isDone, matcher.group("description"));
    }

    private static Deadline addDeadline(boolean isDone, String arguments) throws Storage.StorageOperationException {
        final Matcher matcher = DEADLINE_DATA_ARGS_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new Storage.StorageOperationException("Encoded deadline in invalid format. Unable to decode");
        }
        return new Deadline(isDone, matcher.group("description"), getLocalDateTime(matcher.group("deadline")));
    }

    private static Event addEvent(boolean isDone, String arguments) throws Storage.StorageOperationException {
        final Matcher matcher = EVENT_DATA_ARGS_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new Storage.StorageOperationException("Encoded event in invalid format. Unable to decode");
        }
        return new Event(isDone, matcher.group("description"), getLocalDateTime(matcher.group("dateTime")));
    }

    private static LocalDateTime getLocalDateTime(String dateStr) {
        return LocalDateTime.parse(dateStr, DATE_FORMAT);
    }

}

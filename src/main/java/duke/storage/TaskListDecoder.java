package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

/**
 * Decodes the duke.storage duke.data file into an {@code TaskList} object.
 */
public class TaskListDecoder {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a");
    private static final Pattern TASK_DATA_ARGS_FORMAT =
            Pattern.compile("(?<taskType>[TDE]+)/(?<isDone>[XO])/(?<arguments>.*)");
    private static final Pattern TODO_DATA_ARGS_FORMAT = Pattern.compile("(?<description>.*)");
    private static final Pattern DEADLINE_DATA_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)/(?<deadline>.*)");
    private static final Pattern EVENT_DATA_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)/(?<dateTime>.*)");

    /**
     * Decodes {@code encodedTaskList} into a {@code TaskList} containing the decoded tasks.
     *
     * @param file the storage file containing all the saved tasks
     * @return a {@code TaskList object}
     * @throws FileNotFoundException if the file is not found
     * @throws Storage.StorageOperationException if the {@code encodedTaskList} is in an invalid format.
     */
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

    /**
     * Decodes {@code encodedTask} into a {@code Todo}, {@code Deadline} or {@code Event}.
     * @throws Storage.StorageOperationException if {@code encodedTask} is in an invalid format.
     */
    private static Task decodeTaskFromString(String encodedTask) throws Storage.StorageOperationException {
        final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new Storage.StorageOperationException("Encoded task in invalid format. Unable to decode.");
        }

        final String taskType = matcher.group("taskType");
        final boolean isDone = isDonePrefix(matcher.group("isDone"));
        final String arguments = matcher.group("arguments");

        switch (taskType) {
        case "T":
            return decodeTodo(isDone, arguments);
        case "D":
            return decodeDeadline(isDone, arguments);
        case "E":
            return decodeEvent(isDone, arguments);
        default:
            throw new Storage.StorageOperationException("Encoded task in invalid format. Unable to decode.");
        }
    }

    /**
     * Returns true if {@code matchedPrefix} is equal to the prefix for completed task.
     */
    private static boolean isDonePrefix(String matchedPrefix) {
        return "O".equals(matchedPrefix);
    }

    /**
     * Decodes {@code encodedTodo} into a {@code Todo}.
     * @throws Storage.StorageOperationException if {@code encodedTodo} is in an invalid format.
     */
    private static Todo decodeTodo(boolean isDone, String arguments) throws Storage.StorageOperationException {
        final Matcher matcher = TODO_DATA_ARGS_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new Storage.StorageOperationException("Encoded todo in invalid format. Unable to decode.");
        }
        return new Todo(isDone, matcher.group("description"));
    }

    /**
     * Decodes {@code encodedDeadline} into a {@code Deadline}.
     * @throws Storage.StorageOperationException if {@code encodedDeadline} is in an invalid format.
     */
    private static Deadline decodeDeadline(boolean isDone, String arguments) throws Storage.StorageOperationException {
        final Matcher matcher = DEADLINE_DATA_ARGS_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new Storage.StorageOperationException("Encoded deadline in invalid format. Unable to decode");
        }
        return new Deadline(isDone, matcher.group("description"), getLocalDateTime(matcher.group("deadline")));
    }

    /**
     * Decodes {@code encodedEvent} into a {@code Event}.
     * @throws Storage.StorageOperationException if {@code encodedEvent} is in an invalid format.
     */
    private static Event decodeEvent(boolean isDone, String arguments) throws Storage.StorageOperationException {
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

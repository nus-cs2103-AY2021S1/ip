package duke.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.backend.exception.DukeInvalidDataException;
import duke.command.exception.DukeInvalidIndexException;
import duke.response.Parser;
import duke.response.exception.DukeInvalidDateTimeInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * Deals with reading and writing of Task List in the text form.
 */
public class Storage {
    private final String filePath;

    /**
     * Class constructor.
     * @param filePath The filePath of the storage text file.
     */
    public Storage(String filePath) {
        assert(filePath != null && !filePath.equals(""));
        this.filePath = filePath;
    }

    /**
     * Reads the text file and returns the list of tasks in the text.
     *
     * @return The list of tasks in storage text file.
     * @throws DukeInvalidDataException If the Storage text file is corrupted.
     * @throws DukeInvalidDateTimeInputException If the Date Time in storage file is invalid.
     */
    public List<Task> load() throws DukeInvalidDataException, DukeInvalidDateTimeInputException {
        File file = new File(filePath);
        List<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] tokens = sc.nextLine().split(" \\| ");
                if (tokens.length < 3) {
                    throw new DukeInvalidDataException("Oops data is invalid");
                }
                String taskType = tokens[0];
                String isDone = tokens[1];
                String description = tokens[2];
                Task task;
                if (taskType.equals("T")) {
                    task = new Todo(description);
                } else if (tokens.length != 4) {
                    throw new DukeInvalidDataException("Oops data is invalid");
                } else if (taskType.equals("D")) {
                    List<LocalDateTime> ldtList = Parser.getCustomDateTimeList(tokens[3]);
                    task = loadDeadline(description, ldtList);
                } else if (taskType.equals("E")) {
                    List<LocalDateTime> ldtList = Parser.getCustomDateTimeList(tokens[3]);
                    task = loadEvent(description, ldtList);
                } else {
                    throw new DukeInvalidDataException("Oops data is invalid");
                }

                if (isDone.equals("1")) {
                    task.markAsDone();
                } else if (!isDone.equals("0")) {
                    throw new DukeInvalidDataException("Oops data is invalid");
                }
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            try {
                new FileWriter(filePath).write("");
            } catch (IOException ioException) {
                System.out.println(ioException.getMessage());
                list = new ArrayList<>();
            }
        }
        return list;
    }

    private static Deadline loadDeadline(String description,
            List<LocalDateTime> localDateTimeList) {
        LocalDate date = localDateTimeList.get(0).toLocalDate();
        LocalTime time = localDateTimeList.size() == 2
            ? localDateTimeList.get(1).toLocalTime()
            : null;
        return new Deadline(description, date, time);
    }

    private static Event loadEvent(String description,
            List<LocalDateTime> localDateTimeList) {
        LocalDate date = localDateTimeList.get(0).toLocalDate();
        LocalTime time = localDateTimeList.size() == 2
            ? localDateTimeList.get(1).toLocalTime()
            : null;
        return new Event(description, date, time);
    }

    /**
     * Writes and save the Storage text file with the list of tasks.
     *
     * @param taskList The TaskList with list of tasks.
     * @throws IOException If the filePath is invalid.
     * @throws DukeInvalidIndexException If the index exceeds list size.
     */
    public void save(TaskList taskList) throws IOException, DukeInvalidIndexException {
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.get(i);
            TaskType type = task.getTaskType();
            String s = "";
            switch (type) {
            case TODO:
                s = String.format("T | %d | %s", task.getIsDone() ? 1 : 0, task.getDescription());
                break;
            case DEADLINE:
                Deadline deadline = (Deadline) task;
                s = String.format("D | %d | %s | %s", deadline.getIsDone() ? 1 : 0,
                    deadline.getDescription(), deadline.getBy());
                break;
            case EVENT:
                Event event = (Event) task;
                s = String.format("E | %d | %s | %s", event.getIsDone() ? 1 : 0,
                    event.getDescription(), event.getAt());
                break;
            default:
                break;
            }

            if (i != taskList.getSize() - 1) {
                s += "\n";
            }
            writer.write(s);
        }
        writer.close();
    }
}

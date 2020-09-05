package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.FileLoadError;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/** Deals with saving and loading the data into a file. */
public class Storage {

    /** The filepath to store the data. */
    private final String filePath;

    /** The path to an existing saved file. */
    private Path dukeFile;

    /**
     * Constructs a Storage.
     *
     * @param filePath The filepath to store the data in.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createFile();
    }

    /** Creates a file to save the data if it has not existed. */
    private void createFile() {
        try {

            if (Files.notExists(Paths.get(filePath))) {
                Files.createDirectory(Paths.get(filePath));
            }

            if (Files.notExists(Paths.get(filePath + "/duke.txt"))) {
                dukeFile = Files.createFile(Paths.get(filePath + "/duke.txt"));
            } else {
                dukeFile = Paths.get(filePath + "/duke.txt");
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Saves the task list into a file.
     *
     * @param tasks The task list to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {

        StringBuilder taskString = new StringBuilder();

        for (Task task : tasks) {
            taskString.append(task.saveFormat());
            taskString.append("\n");
        }

        try {
            Files.writeString(dukeFile, taskString);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Loads the task list from the saved file.
     *
     * @return The task list.
     * @throws FileLoadError If there is an error in reading or obtaining the file.
     */
    public ArrayList<Task> load() throws FileLoadError {
        try {

            ArrayList<Task> taskList = new ArrayList<>();

            for (String task : Files.readAllLines(dukeFile)) {
                Task t = readTask(task);
                assert t != null: "File read error.";
                taskList.add(readTask(task));
            }

            return taskList;

        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new FileLoadError();
        }
    }

    /**
     * Reads the saved tasks in the file.
     *
     * @param task The task to be read
     * @return The task in the proper format, such as ToDo, Event, or Deadline
     */
    private Task readTask(String task) {

        // Save format = {T}{1} {task description} {date}

        String isoFormat = "yyyy-mm-ddThh:mm:ss";
        String eventDateFormat = isoFormat + " to " + isoFormat;

        char taskCode = task.charAt(0);
        boolean isDone = task.charAt(1) == '1';

        switch (taskCode) {
            case 'T':
                ToDo toDo = new ToDo(task.substring(3).trim());

                if (isDone) {
                    toDo = toDo.markDone();
                }

                return toDo;
                // Fallthrough

            case 'D':
                String endDateString = task.substring(task.length() - isoFormat.length());
                Deadline deadline = new Deadline(task.substring(3, task.length() - isoFormat.length()).trim(),
                    LocalDateTime.parse(endDateString));

                if (isDone) {
                    deadline = deadline.markDone();
                }

                return deadline;
                // Fallthrough

            case 'E':

                String taskText = task.substring(3,
                    task.length() - (eventDateFormat + " ").length()).trim();
                String startDateString = task.substring(
                    task.length() - eventDateFormat.length(),
                    task.length() - (isoFormat + " to ").length());
                endDateString = task.substring(task.length() - isoFormat.length());

                boolean hasEndDate = !endDateString.equals(Event.EMPTY_END_DATE);

                Event event = new Event(taskText, LocalDateTime.parse(startDateString),
                    hasEndDate
                        ? LocalDateTime.parse(endDateString)
                        : null);

                if (isDone) {
                    event = event.markDone();
                }

                return event;
                // Fallthrough

            default:
                return null;
                // Fallthrough
        }
    }
}

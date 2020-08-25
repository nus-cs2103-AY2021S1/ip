package duke;

import duke.exception.FileLoadError;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

/** Deals with saving and loading the data into a file. */
public class Storage {

    /** The filepath to store the data. */
    private final String filePath;

    /** The path to an existing saved file. */
    private Path dukeFile;

    /**
     * Constructs a @Storage.
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

        for (Task task: tasks) {
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
                if (t != null) {
                    taskList.add(readTask(task));
                }
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
     * @return The task in the proper format, such as @ToDo, @Event, or @Deadline
     */
    private Task readTask(String task) {
        switch (task.charAt(0)) {
            case 'T':
                ToDo toDo = new ToDo(task.substring(3).trim());

                if (task.charAt(1) == '1') {
                    toDo = toDo.markDone();
                }

                return toDo;
                // Fallthrough

            case 'D':
                Deadline deadline = new Deadline(task.substring(3, task.length() - 19).trim(),
                        LocalDateTime.parse(task.substring(task.length() - 19)));

                if (task.charAt(1) == '1') {
                    deadline = deadline.markDone();
                }

                return deadline;
                // Fallthrough

            case 'E':

                String taskText = task.substring(3, task.length() - 43).trim();
                String startDateString = task.substring(task.length() - 42, task.length() - 23);
                String endDateString = task.substring(task.length() - 19);

                Event event = new Event(taskText,
                        LocalDateTime.parse(startDateString),
                        endDateString.equals("XXXXXXXXXXXXXXXXXXX")
                                ? null
                                : LocalDateTime.parse(endDateString));

                if (task.charAt(1) == '1') {
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

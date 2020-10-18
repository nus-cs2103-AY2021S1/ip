import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a storage class, handles the writing or reading of the task list to or from a text file.
 */
public class Storage {

    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the tasks from the local storage file and creates a list of Tasks.
     *
     * @return a list of Tasks stored in the local storage file.
     * @throws DukeException if the local storage file cannot be created or loaded.
     */
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        try {
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! There was an error creating a save file.");
        }
        assert file.exists() : "File should exist.";
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String s = fileReader.nextLine();
                Task task = getTask(s);
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException | DukeException e) {
            throw new DukeException("OOPS!!! There was an error while loading the file");
        }
    }

    /**
     * Converts a string into a Task.
     *
     * @param taskString string description of a Task.
     * @return a Task described by the input string.
     * @throws DukeException if input string is in the wrong format.
     */
    private Task getTask(String taskString) throws DukeException {
        final int DESCRIPTION_INDEX = 8;
        final int IS_DONE_INDEX = 4;
        try {
            Task currentTask = null;
            if (taskString.startsWith("T")) {
                currentTask = new Todo(taskString.substring(DESCRIPTION_INDEX));
            } else {
                int timeIndex = taskString.lastIndexOf(" |");
                String dateTime = taskString.substring(timeIndex + 3);
                if (taskString.startsWith("D")) {
                    currentTask = new Deadline(taskString.substring(DESCRIPTION_INDEX, timeIndex),
                            LocalDate.parse(dateTime));
                } else if (taskString.startsWith("E")) {
                    currentTask = new Event(taskString.substring(DESCRIPTION_INDEX, timeIndex),
                            LocalDate.parse(dateTime));
                } 
            }
            assert currentTask != null : "Invalid task description.";
            
            if (taskString.charAt(IS_DONE_INDEX) == '1') {
                currentTask.markAsDone();
            }
            return currentTask;
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException();
        }
    }

    /**
     * Updates the local storage file and stores the list of Tasks.
     *
     * @param taskList list of Tasks to be stored.
     * @throws DukeException if local storage file cannot be accessed.
     */
    void updateTasks(TaskList taskList) throws DukeException {
        try {
            assert new File(filePath).exists() : "File should exist.";
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task t : taskList.getMyTaskList()) {
                fileWriter.write(t.saveAsString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Unable to update tasks.");
        }
    }
}

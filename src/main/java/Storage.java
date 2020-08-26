import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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

        try {
            //noinspection ResultOfMethodCallIgnored
            new File(filePath).createNewFile();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! There was an error creating a save file.");
        }

        try {
            Scanner fileReader = new Scanner(new File(filePath));
            while (fileReader.hasNextLine()) {
                String s = fileReader.nextLine();
                Task task = getTask(s);
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException | DukeException e) {
            throw new DukeException("☹ OOPS!!! There was an error while loading the file");
        }
    }

    /**
     * Converts a string into a Task.
     *
     * @param s string description of a Task.
     * @return a Task described by the input string.
     * @throws DukeException if input string is in the wrong format.
     */
    private Task getTask(String s) throws DukeException {
        try {
            Task currentTask;
            if (s.startsWith("T")) {
                currentTask = new Todo(s.substring(8));
            } else {
                int index = s.lastIndexOf(" |");
                String dateTime = s.substring(index + 3);
                if (s.startsWith("D")) {
                    currentTask = new Deadline(s.substring(8, index), LocalDate.parse(dateTime));
                } else if (s.startsWith("E")) {
                    currentTask = new Event(s.substring(8, index), LocalDate.parse(dateTime));
                } else {
                    throw new DukeException();
                }
            }

            if (s.charAt(4) == '1') {
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
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task t : taskList.getMyTaskList()) {
                fileWriter.write(t.saveAsString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Unable to update tasks.");
        }
    }
}

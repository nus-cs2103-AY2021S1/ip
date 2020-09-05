package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Encapsulates a storage that manages the database used by Duke.
 * It is initialised using a path that leads to a .txt file.
 * A task is saved as a string with various data separated by the '|' character.
 * It supports the uploading and retrieval of tasks to and from the database.
 */
public class Storage {
    private File file;

    /**
     * Locates the .txt file at the given path.
     * Creates the file if it does not exists.
     * @param path Path to locate or create the .txt file.
     * @throws DukeException
     */
    public Storage(String path) throws DukeException {
        this.file = new File(path);
        try {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error creating or locating storage.");
        }
    }

    /**
     * Saves all tasks in a list of tasks to the database.
     * @param tasks A list of tasks to be saved to the database.
     * @throws DukeException
     */
    public void saveTasks(List<Task> tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toData() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to storage.");
        }
    }

    /**
     * Retrieves all tasks that is being stored in the database as a list of tasks.
     * @return A list of all tasks that is being stored in the database.
     * @throws DukeException
     */
    public List<Task> getTasks() throws DukeException {
        try {
            List<Task> tasks = new ArrayList<>();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input = bufferedReader.readLine();
            while (input != null) {
                String[] entryBreakdown = input.split(" \\| ");
                if (entryBreakdown.length < 3) {
                    throw new DukeException("File corrupted");
                }
                if (!(entryBreakdown[1].equals("0") || entryBreakdown[1].equals("1"))) {
                    throw new DukeException("File corrupted");
                }

                String type = entryBreakdown[0];
                boolean isDone = entryBreakdown[1].equals("1");
                String description = entryBreakdown[2];
                Task task;
                switch (type) {
                case ("T"):
                    if (isDone) {
                        assert entryBreakdown.length > 3;
                        String doneDate = entryBreakdown[3];
                        task = new Todo(description, LocalDate.parse(doneDate));
                    } else {
                        task = new Todo(description);
                    }
                    break;
                case ("D"):
                    if (entryBreakdown.length < 4) {
                        throw new DukeException("File corrupted");
                    }
                    if (isDone) {
                        assert entryBreakdown.length > 4;
                        String doneDate = entryBreakdown[3];
                        String by = entryBreakdown[4];
                        task = new Deadline(description, LocalDateTime.parse(by), LocalDate.parse(doneDate));
                    } else {
                        String by = entryBreakdown[3];
                        task = new Deadline(description, LocalDateTime.parse(by));
                    }
                    break;
                case ("E"):
                    if (entryBreakdown.length < 4) {
                        throw new DukeException("File corrupted");
                    }
                    if (isDone) {
                        assert entryBreakdown.length > 4;
                        String doneDate = entryBreakdown[3];
                        String at = entryBreakdown[4];
                        task = new Event(description, LocalDateTime.parse(at), LocalDate.parse(doneDate));
                    } else {
                        String at = entryBreakdown[3];
                        task = new Event(description, LocalDateTime.parse(at));
                    }
                    break;
                default:
                    throw new DukeException("File corrupted.");
                }
                tasks.add(task);
                input = bufferedReader.readLine();
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error retrieving tasks from storage.");
        }
    }
}

package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage for Tasks. A <code>Storage</code> represents a system that manages the storage and retrieval of
 * <code>Task</code> from a file.
 */
public class Storage {
    private final File file;
    protected final boolean isNew;

    Storage(File file, boolean isNew) {
        this.file = file;
        this.isNew = isNew;
    }

    /**
     * Returns a <code>Storage</code> system containing a <code>TaskList</code>.
     * Creates a new file at the given directory if the file does not exists. Else, retrieves all tasks from the
     * given file.
     *
     * @param filePath FilePath representing the location of the file where all <code>Task</code> are saved
     * @return <code>Storage</code> object with all previous <code>Task</code> saved, if applicable
     * @throws DukeException if file cannot be created/ retrieved
     */
    public static Storage createStorage(String filePath) throws DukeException {
        String errMessage = "Woof woof... I can't seem to create a file to store your tasks...\n"
                + "Your tasks would be forgotten at this rate...";
        String[] pathNames = filePath.split("/");
        String dirName = pathNames[0];
        String fileName = pathNames[1];

        try {
            String home = System.getProperty("user.dir");
            Path currDir = Paths.get(home);
            Path targetPath = Paths.get(currDir.toString(), filePath);
            File directory = new File(Paths.get(currDir.toString(), dirName).toString());
            boolean isDirCreated;
            boolean isFileCreated;

            if (!java.nio.file.Files.exists(targetPath)) {

                if (directory.exists()) {
                    isDirCreated = true;
                    File file = new File(Paths.get(currDir.toString(), dirName, fileName).toString());
                    isFileCreated = file.createNewFile();
                } else {
                    File dir = new File(Paths.get(currDir.toString(), dirName).toString());
                    isDirCreated = dir.mkdir();
                    File file = new File(Paths.get(currDir.toString(), dirName, fileName).toString());
                    isFileCreated = file.createNewFile();
                }

                if (isDirCreated && isFileCreated) {
                    return new Storage(new File(targetPath.toString()), true);
                } else {
                    throw new DukeException(errMessage);
                }
            } else {
                return new Storage(new File(targetPath.toString()), false);
            }
        } catch (InvalidPathException | DukeException | IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Loads all <code>Task</code> saved in file into the <code>TaskList</code>, if applicable.
     *
     * @throws DukeException if <code>Task</code> cannot be retrieved due <code>FileNotFound</code> exception
     */
    public List<Task> load() throws DukeException {
        try {
            Scanner s = new Scanner(file);
            List<Task> temporaryList = new ArrayList<>();

            while (s.hasNextLine()) {
                String task = s.nextLine();
//                taskList.checkTask(s.nextLine());
                Task t;
                String taskType = task.substring(0, 3);
                String status = task.substring(3, 6);
                boolean isDone = status.equals("[" + "\u2713" + "]");

                if (taskType.equals("[T]")) {
                    t = new Todo(task.substring(7), isDone);
                } else if (taskType.equals("[D]")) {
                    int indOfTime = task.lastIndexOf("(FINISH by: ");
                    t = new Deadline(task.substring(7, indOfTime),
                            task.substring(indOfTime + 11, task.lastIndexOf(")")).trim(), isDone);
                } else {
                    int indOfTime = task.lastIndexOf("(APPEAR at: ");
                    t = new Event(task.substring(7, indOfTime),
                            task.substring(indOfTime + 11, task.lastIndexOf(")")).trim(), isDone);
                }

                temporaryList.add(t);
            }

            return temporaryList;

        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves the given <code>Task</code> to file.
     *
     * @param taskList Task to be added to file
     * @throws DukeException if <code>Task</code> fails to save
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            List<Task> listOfTask = taskList.getList();
            FileWriter fileWriter = new FileWriter(file);

            for (Task task : listOfTask) {
                fileWriter.write(task.toString());
                fileWriter.write(System.getProperty("line.separator"));
            }

            fileWriter.close();

        } catch (IOException e) {
            String s = " Unable to access file... *woof*\n";
            throw new DukeException(s);
        }
    }

    public boolean isNew() {
        return isNew;
    }
}

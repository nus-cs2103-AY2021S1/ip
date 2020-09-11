package duke.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.FailToReadFileException;
import duke.task.Task;

/**
 * This class deals with saving, changing, or deleting data in the hard disk.
 */
public class Storage {
    private static final int STATUS_POSITION_IN_DISK = 4;
    private static final int TO_START_POSITION_OF_DATE = 16;
    private Path storagePath;

    /**
     * Constructs a Storage object with the specified file path.
     * @param filePath The file path to the storage file
     */
    public Storage(String filePath) {
        assert filePath != null : "Path can't be null!";
        this.storagePath = Paths.get(filePath);
    }

    /**
     * Saves the task to the hard disk.
     * The task will be parsed to the task's saving format before it is saved to the hard disk.
     * @param task The task to be saved
     * @throws DukeException If the FileWriter fails to read the file
     */
    public void saveTaskToFile(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(storagePath.toString(), true);

            String toBeAppend = Parser.parseForSave(task);
            fw.write(toBeAppend);
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    /**
     * Changes the task's status to done in the hard disk based on the line number specified.
     * @param line The task's line that wanted to be changed
     * @throws DukeException If the Scanner or FileWriter fails to read the file
     */
    public void changeTaskStatusInFile(int line) throws DukeException {
        try {
            StringBuilder sb = new StringBuilder();
            File file = new File(storagePath.toString());
            Scanner sc = new Scanner(file);
            int count = 1;

            while (sc.hasNext()) {
                String taskLine = sc.nextLine() + "\n";

                if (count == line) {
                    if (taskLine.charAt(STATUS_POSITION_IN_DISK) == '0') {
                        taskLine = taskLine.replaceFirst("0", "1");
                    }
                }
                sb.append(taskLine);
                count++;
            }

            FileWriter fw = new FileWriter(storagePath.toString());
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    /**
     * Deletes a task in the hard disk based on the line number specified.
     * @param line The task's line that wanted to be deleted
     * @throws DukeException If the Scanner or FileWriter fails to read the file
     */
    public void deleteTaskInFile(int line) throws DukeException {
        try {
            StringBuilder sb = new StringBuilder();
            File file = new File(storagePath.toString());
            Scanner sc = new Scanner(file);
            int count = 1;

            while (sc.hasNext()) {
                String taskLine = sc.nextLine() + "\n";
                if (count != line) {
                    sb.append(taskLine);
                }
                count++;
            }

            FileWriter fw = new FileWriter(storagePath.toString());
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    /**
     * Change the task's date to the specified date in the hard disk based on the line number specified.
     * @param line the task's line that wanted to be changed
     * @param newDate the new date for the task
     * @throws DukeException If the Scanner of FileWriter fails to read the file
     */
    public void changeTaskDateInFile(int line, String newDate) throws DukeException {
        try {
            StringBuilder sb = new StringBuilder();
            File file = new File(storagePath.toString());
            Scanner sc = new Scanner(file);
            int count = 1;

            while (sc.hasNext()) {
                String taskLine = sc.nextLine() + "\n";

                if (count == line) {
                    String subStringTaskLine = taskLine.substring(0, taskLine.length() - TO_START_POSITION_OF_DATE);
                    taskLine = subStringTaskLine + newDate + "\n";
                }
                sb.append(taskLine);
                count++;
            }

            FileWriter fw = new FileWriter(storagePath.toString());
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    private void createStorageFile() throws DukeException {
        try {
            Path dataPath = storagePath.getParent();
            File dataFile = new File(dataPath.toString());
            File storageFile = new File(storagePath.toString());

            if (!dataFile.exists()) {
                dataFile.mkdir();
            }

            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    /**
     * Read the storage file in the hard disk. If no folder or file has been created, it will create the folder or file
     * automatically.
     * @return List of task
     * @throws DukeException If fails to create the new file locally
     * or the Scanner fails to read the file
     */
    public List<Task> loadTasksFromDisk() throws DukeException {
        try {
            createStorageFile();
            File file = new File(storagePath.toString());

            Scanner sc = new Scanner(file);

            List<Task> tasks = new ArrayList<>();

            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                Task taskInFile = Parser.parseForReadingFile(taskString);
                tasks.add(taskInFile);
            }

            return tasks;
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }
}

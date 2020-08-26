package duke.utility;

import duke.exception.DukeException;
import duke.exception.FailToReadFileException;
import duke.task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class deals with saving, changing, or deleting
 * data in the hard disk. These are done in order to remember
 * all the current task when running the Duke again.
 */
public class Storage {
    private Path storagePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path to the storage file
     */
    public Storage(String filePath) {
        this.storagePath = Paths.get(filePath);
    }

    /**
     * Saves the task to the hard disk. The task will be parsed
     * to the task's saving format before it is saved to the hard disk.
     * Note that the new task will be appended to the end of the file.
     *
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
     * Changes the task's status to done in the hard disk based on
     * the line number specified. This will change '0' (which indicates
     * ongoing task) in the hard disk to '1' (which indicates done task).
     *
     * @param line The task line that wanted to be changed
     * @throws DukeException If the Scanner or FileWriter fails to read the file
     */
    public void changeTaskInFile(int line) throws DukeException {
        try {
            StringBuilder sb = new StringBuilder();
            File file = new File(storagePath.toString());

            Scanner sc = new Scanner(file);

            int count = 1;
            while (sc.hasNext()) {
                String taskLine = sc.nextLine() + "\n";

                if (count == line) {
                    if (taskLine.charAt(4) == '0') {
                        taskLine = taskLine.replaceFirst("0", "1");
                    }
                }

                sb.append(taskLine);
                count++;
            }

            String tobeWritten = sb.toString();
            FileWriter fw = new FileWriter(storagePath.toString());

            fw.write(tobeWritten);
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    /**
     * Delete a task in the hard disk based on the line number
     * specified.
     *
     * @param line The task line that wanted to be changed
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

            String tobeWritten = sb.toString();
            FileWriter fw = new FileWriter(storagePath.toString());

            fw.write(tobeWritten);
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    private void createStorageFile() throws DukeException {
        try {
            Path dataPath = storagePath.getParent();
            File dataFile = new File(dataPath.toString());

            if (!dataFile.exists()) {
                dataFile.mkdir();
            }

            File storageFile = new File(storagePath.toString());

            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    /**
     * Read the storage file in the hard disk. If no folder or
     * file has been created, it will create the folder or file
     * automatically. All the strings read from the hard disk
     * will be parsed to their corresponding task and added to the
     * list of task.
     *
     * @return List of task
     * @throws DukeException If fails to create the new file locally
     * or the Scanner fails to read the file
     */
    public List<Task> load() throws DukeException {
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

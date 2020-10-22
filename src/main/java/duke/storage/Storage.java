package duke.storage;

import duke.DukeException;
import duke.task.TaskList;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {

    private Path dirPath;
    private Path filePath;

    public Storage(String dirPath, String fileName) {
        this.dirPath = Paths.get(dirPath);
        this.filePath = Paths.get(dirPath, fileName);
        File dataDir = this.dirPath.toAbsolutePath().toFile();
        File dataFile = this.filePath.toAbsolutePath().toFile();

        try {
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();;
        }

    }

    /**
     * Saves the existing tasklist to a log file specified with filePath in the class constructor along
     * with a timestamp. Creates the necessary directories and files if they do not exist.
     *
     * @param taskList
     * @throws DukeException
     */
    public void save(TaskList taskList) throws DukeException {
        try (BufferedWriter writer = Files.newBufferedWriter(this.filePath)) {
            LocalDateTime now = LocalDateTime.now();
            String msg = "duke.task.Task list (Last updated "
                    + now.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss a"))
                    + "):\n";
            writer.write(msg + taskList.toString());
        } catch (IOException e) {
            throw new DukeException("CANNOT SAVE TASKLIST TO FILE: " + e.getMessage());
        }
    }

    /**
     * loads a saved tasklist from a log file and parses it into an arrayList of Strings that describe
     * the task.
     *
     * @return an arrayList of Strings that describe the tasks saved in the tasklist
     * @throws DukeException
     */
    public ArrayList<String> load() throws DukeException {
        try (BufferedReader reader = Files.newBufferedReader(this.filePath)) {
            ArrayList<String> tasksStr = new ArrayList<>();
            String str;
            reader.readLine();
            while((str = reader.readLine()) != null) {
                tasksStr.add(str.substring(str.indexOf(".") + 1).trim());
            }

            return tasksStr;
        } catch (IOException e) {
            throw new DukeException("GOT ERROR LOADING TASKS! " + e.getMessage());
        }
    }


}

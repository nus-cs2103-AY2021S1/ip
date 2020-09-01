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

    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        File dataFile = this.filePath.toAbsolutePath().toFile();

        if (!dataFile.exists()) {
            dataFile.mkdirs();
        }
    }

    public void save(TaskList taskList) throws DukeException {
        try (BufferedWriter writer = Files.newBufferedWriter(this.filePath)) {
            LocalDateTime now = LocalDateTime.now();
            String msg = "duke.task.Task list (Last updated "
                    + now.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss a"))
                    + "):\n";
            writer.write(msg + taskList.toString());
        } catch (IOException e) {
            throw new DukeException("CANNOT SAVE TASKLIST TO FILE");
        }
    }

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

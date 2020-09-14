package luke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import luke.exception.LukeException;
import luke.task.Task;

public class Storage {
    private final String dataFolderDir;
    private final String dataFile;
    private final String dataFileDir;

    /**
     * Creates a Storage object to manage data file from hard disk.
     *
     * @param filePath path of the data file that contains the current list of tasks
     */
    public Storage (String filePath) {
        this.dataFolderDir = filePath.concat("/data/");
        this.dataFile = "tasks.txt";
        this.dataFileDir = this.dataFolderDir.concat(this.dataFile);
    }

    /**
     * Reads task list from the file.
     *
     * @return Latest task list.
     */
    public List<Task> load() throws LukeException {
        createDataFolderDir();
        File dataFile = new File(this.dataFileDir);
        try {
            if (dataFile.createNewFile()) {
                return new ArrayList<>();
            } else {
                return loadDataFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Writes task list to the file.
     *
     * @param tasks newly updated task list.
     */
    public void save(TaskList tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.dataFileDir, false))) {
            // write task to hard disk (./data/tasks.txt)
            // for each task inside the TaskList, toDataString, then write to file
            String dataString = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                dataString += tasks.getTask(i).toDataString() + "\n";
            }
            bw.write(dataString);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDataFolderDir() {
        File file = new File(this.dataFolderDir);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private List<Task> loadDataFile() throws LukeException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.dataFileDir))) {
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                Task parsedTask = Parser.parseTask(readLine);
                tasks.add(parsedTask);
            }
        } catch (FileNotFoundException e) {
            throw new LukeException(e.getMessage());
        } catch (IOException e) {
            throw new LukeException(e.getMessage());
        }
        return tasks;
    }
}

package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Storage {

    private String filePath;
    private String folderPath;

    public Storage(String filePath, String folderPath) {
        this.filePath = System.getProperty("user.dir") + filePath;
        this.folderPath = System.getProperty("user.dir") + folderPath;
    }

    public ArrayList<String> load() throws DukeException {
        File folder = new File(folderPath);
        File file = new File(filePath);

        if (!folder.exists()) {
            folder.mkdir();
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new DukeException("IOException from creating data file");
            }
            throw new DukeException("Data folder does not exist");
        }

        ArrayList<String> taskList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                taskList.add(line);
            }
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            throw new DukeException("FileNotFoundException");
        } catch (IOException exception) {
            throw new DukeException("Unable to read from file");
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) {
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            for (Task task : taskList) {
                String taskLine = task.toData();
                fileWriter.write(taskLine);
            }
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

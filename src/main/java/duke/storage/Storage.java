package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Storage {

    private File file;
    private File folder;

    public Storage(String filePath, String folderPath) {
        this.file = new File(System.getProperty("user.dir") + filePath);
        this.folder = new File(System.getProperty("user.dir") + folderPath);

    }

    public ArrayList<String> load() throws DukeException {

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

    public void save(TaskList taskList) {
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            ArrayList<String> taskStrings = taskList.tasksToText();
            for (String string : taskStrings) {
                fileWriter.write(string);
            }
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

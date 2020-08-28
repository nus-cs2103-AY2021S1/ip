package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.TaskList;
/**
 * Represents the Storage manager used to save and load tasks from hard disk.
 */
public class Storage {

    private File file;
    private File folder;

    /**
     * Creates a Storage manager.
     *
     * @param filePath Directory where text file is saved.
     * @param folderPath Path name of text file to be saved.
     */
    public Storage(String filePath, String folderPath) {
        this.file = new File(System.getProperty("user.dir") + filePath);
        this.folder = new File(System.getProperty("user.dir") + folderPath);

    }

    /**
     * Loads task data from the text file if it exists.
     *
     * @return List of lines in the text files.
     * @throws DukeException If file is not found or unable to write to file.
     */
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

    /**
     * Writes task data to text file.
     *
     * @param taskList Tasklist containing the tasks to be saved.
     */
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

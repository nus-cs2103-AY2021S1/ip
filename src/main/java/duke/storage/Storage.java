package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Initialises a storage object to save and load from the data file for duke.
 */
public class Storage {

    /** Default filePath for data file. */
    private String filePath = "./src/main/java/duke/Data/data.txt";

    /** Default folderPath for data file. */
    private String folderPath = "./src/main/java/duke/Data";

    /**
     * Constructor to create a storage object with proper referencing.
     * @param filePath File path of data file.
     * @param folderPath Folder path of data file.
     */
    public Storage(String filePath, String folderPath) {
        this.filePath = filePath;
        this.folderPath = folderPath;
    }

    /**
     * Creates a folder in the directory if it does not exist.
     * @throws DukeException
     */
    public void makeFolder() throws DukeException {
        File savedFolder = new File(folderPath);
        savedFolder.mkdir();
        this.makeFile();
    }

    /**
     * Creates the data file if it does not exist in the directory.
     * @throws DukeException
     */
    public void makeFile() throws DukeException {
        assert !filePath.isEmpty() : "Filepath is empty";
        File savedFile = new File(filePath);
        try {
            savedFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("    Error creating data storage file for duke");
        }
    }

    /**
     * Loads the tasks from the data file.
     * @return ArrayList of string to be converted into tasks.
     * @throws DukeException
     */
    public ArrayList<String> load() throws DukeException {
        assert !filePath.isEmpty() : "Filepath is empty";
        assert !folderPath.isEmpty() : "FolderPath is empty";

        File savedFolder = new File(folderPath);
        File savedFile = new File(filePath);

        if (!savedFolder.exists()) {
            this.makeFolder();
            return new ArrayList<>();
        }
        if (!savedFile.exists()) {
            this.makeFile();
            return new ArrayList<>();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(savedFile));
            String storedTask = "";
            ArrayList<String> listOfTasks = new ArrayList<>();

            while ((storedTask = bufferedReader.readLine()) != null) {
                listOfTasks.add(storedTask);
            }
            bufferedReader.close();
            return listOfTasks;

        } catch (FileNotFoundException e) {
            throw new DukeException("   File not found!");
        } catch (IOException e) {
            throw new DukeException("   IOException encountered in loading data file.");
        }
    }

    /**
     * Saves the tasks in duke for the next load.
     * @param listOfTasks List of tasks to be saved to data file.
     * @throws DukeException
     */
    public void save(ArrayList<String> listOfTasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (String task : listOfTasks) {
                writer.write(task + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("   Error writing to data file");
        }
    }
}

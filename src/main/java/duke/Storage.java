package duke;

import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.nio.file.Files;
/**
 * Provides a handle to hard disk storage
 */
public class Storage {
    protected FileWriter writeHandle;
    protected File fileHandle;
    protected TaskList taskList;

    /**
     * Constructor
     */
    public Storage() {
        try {
            // Initializes storage folder & file
            File storageFolder = new File("./data");
            if (!storageFolder.exists()) {
                storageFolder.mkdir(); // creates folder if it doesn't exist
            }

            File storageFile = new File("./data/duke.txt");
            if (!storageFile.exists()) {
                storageFile.createNewFile(); // creates file if it doesn't exist
            }

            // Initializes handle for writing to file
            this.fileHandle = storageFile;

            // Try to open the file and parse the contents
            String data = Files.readString(Paths.get("./data/duke.txt"));
            ArrayList<Task> tasks = Parser.parseFile(data);
            // Load contents into store
            this.taskList = new TaskList(tasks);
        } catch (Exception e) {
            System.out.println("Failed to initialize storage");
            System.out.println(e.toString());
            System.exit(1);
        }
    }

    /**
     * Sync all tasks
     */
    public boolean syncTasks() {
        try {
            // Delete the current file
            fileHandle.delete();

            // Create a new file
            fileHandle.createNewFile();

            // Initializes handle for writing to file
            this.writeHandle = new FileWriter("./data/duke.txt");

            // Format the store output as a string
            String data = taskList.dumpTasks();

            // Write the store to file
            writeHandle.write(data);
            writeHandle.close();
            System.out.println("Saved to disc");
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Sync failed");
        }
        return false;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}

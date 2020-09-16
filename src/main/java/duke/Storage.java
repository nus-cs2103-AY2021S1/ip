package duke;

import java.io.*;

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

            this.fileHandle = new File("./data/duke.ser");
            if (fileHandle.exists()) {
                // Try to open the file and parse the contents
                FileInputStream fileIn =
                        new FileInputStream("./data/duke.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                this.taskList = (TaskList) in.readObject();
                in.close();
                fileIn.close();
            } else {
                this.taskList = new TaskList();
            }

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
            FileOutputStream fileOut =
                    new FileOutputStream("./data/duke.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./data/duke.ser");
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

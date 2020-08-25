import java.io.*;

/**
 * Class to handle logic related to reading and writing of files to storage
 */
public class Storage {

    // Attributes
    private final String filePath;

    // Constructor

    /**
     * Creates a storage object that reads and writes to a file at a specified filepath.
     *
     * @param filePath file path of file to read and write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks whether the file exists.
     *
     * @return Boolean representing whether the file exists.
     */
    public boolean doesFileExist() {
        File file = new File(this.filePath);
        return file.exists();
    }

    /**
     * Creates the file
     */
    public void createFile() throws IOException {
        File file = new File(this.filePath);
        boolean createdDirectory = file.getParentFile().mkdirs();
        boolean createdFile = file.createNewFile();
    }

    /**
     * Saves a list to the file. Creates the file if it does not exist.
     *
     * @param lst TaskList to save to the file.
     */
    public void save(TaskList lst) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(lst);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads from the file containing a TaskList and returns it. If file does not exist, returns an
     * empty file.
     *
     * @return The TaskList read from the file if one is read, otherwise an empty TaskList.
     */
    public TaskList read() {
        try {
            if (doesFileExist()) {
                FileInputStream fileInputStream = new FileInputStream(this.filePath);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                TaskList lst = (TaskList) objectInputStream.readObject();
                objectInputStream.close();
                return lst;
            } else {
                createFile();
                return new TaskList();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
            return new TaskList(); // Returns an empty list so that program can keep running
        }
    }
}

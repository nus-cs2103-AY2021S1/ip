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
     * @param filePath file path of file to read and write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks that file we use to store data exists and creates the file if it does not.
     * @return Boolean representing whether a directory or file has been created.
     */
    public boolean checkOrCreate() throws IOException {
        File file = new File(this.filePath);
        boolean createdDirectory = file.getParentFile().mkdirs();
        boolean createdFile = file.createNewFile();
        return createdDirectory || createdFile;
    }

    /**
     * Saves a list to the file. Creates the file if it does not exist.
     * @param lst TaskList to save to the file.
     */
    public void save(TaskList lst){
        try {
            checkOrCreate();
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
     * @return The TaskList read from the file if one is read, otherwise an empty TaskList.
     */
    public TaskList read() {
        try {
            if (checkOrCreate()) {
                System.out.println("File does not exist, creating file");
                return new TaskList();
            } else {
                System.out.println("Todo file found, reading file");
                FileInputStream fileInputStream = new FileInputStream(this.filePath);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                TaskList lst = (TaskList) objectInputStream.readObject();
                objectInputStream.close();
                return lst;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
            return new TaskList(); // Returns an empty list so that program can keep running
        }
    }
}

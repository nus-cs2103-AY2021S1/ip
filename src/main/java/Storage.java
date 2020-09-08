import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    public void createFile() {
        try {
            File file = new File(this.filePath);
            boolean createdDirectory = file.getParentFile().mkdirs();
            boolean createdFile = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a list to the file. Creates the file if it does not exist.
     *
     * @param object Object to save to file.
     */
    public void save(Object object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
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
    public Object read() {
        try {
            if (doesFileExist()) {
                FileInputStream fileInputStream = new FileInputStream(this.filePath);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Object object = objectInputStream.readObject();
                objectInputStream.close();
                return object;
            } else {
                throw new UnsupportedOperationException("File does not exist");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
            return new TaskList(); // Returns an empty list so that program can keep running
        }
    }
}

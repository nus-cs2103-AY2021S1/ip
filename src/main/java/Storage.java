import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle logic related to reading and writing of files to storage
 */
public class Storage {

    // Attributes
    private final String filePath;

    // Constructor

    /**
     * Creates a storage object that reads and writes to a file at a specified filepath.
     * @param filePath file path of file to read and write to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks that file we use to store data exists and creates the file if it does not.
     */
    public boolean checkOrCreate() throws IOException {
        File file = new File(this.filePath);
        return file.getParentFile().mkdirs() && file.createNewFile();
    }

    /**
     * Saves a list to the file. Creates the file if it does not exist
     * @param lst list to save to the file
     * @param <T> type parameter of object inside list
     */
    public <T> void save(List<T> lst){
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
     * Reads from the file containing a list and returns the list. If file does not exist, returns an
     * empty file.
     * @param <T> type parameter of object inside the list
     * @return a list of objects from the file if file exists, otherwise an empty list
     */
    public <T> List<T> read() {
        try {
            if (checkOrCreate()) {
                System.out.println("File does not exist, creating file");
                return new ArrayList<>();
            } else {
                System.out.println("Todo file found, reading file");
                FileInputStream fileInputStream = new FileInputStream(this.filePath);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                @SuppressWarnings("unchecked")
                List<T> lst = (List<T>) objectInputStream.readObject();
                return lst;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
            return new ArrayList<>(); // Returns an empty list so that program can keep running
        }
    }
}

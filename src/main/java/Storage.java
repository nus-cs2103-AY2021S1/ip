import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    
    // hardcoded for now:
    final public static Path ROOT_PATH = Paths.get("").toAbsolutePath();
    final public static Path DATA_DIR_PATH = Paths.get(ROOT_PATH.toAbsolutePath().toString(), "data");
    final public static Path SAVE_FILE_PATH = Paths.get(DATA_DIR_PATH.toAbsolutePath().toString(), "savedData");
    
    public static void save(TaskList allTasks) throws IOException {
        initStorage();
        try {
            FileOutputStream fos = new FileOutputStream(SAVE_FILE_PATH.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allTasks);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    
    public static TaskList load() throws IOException {
        TaskList savedTasks = new TaskList();
        try {
            FileInputStream fis = new FileInputStream(SAVE_FILE_PATH.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
            savedTasks = (TaskList) ois.readObject();
            ois.close();
            fis.close();
            return savedTasks;
        } catch (ClassNotFoundException | IOException e) {
            initStorage();
        }
        return savedTasks;
    }
    
    private static void initStorage() throws IOException {
        createDataDir();
        createSaveFile();
    }
    
    private static void createDataDir() throws IOException {
        if (!Files.exists(DATA_DIR_PATH)) {
            Files.createDirectory(DATA_DIR_PATH);
        }
        
    }
    
    private static void createSaveFile() throws IOException {
        assert (Files.exists(DATA_DIR_PATH)) : "data dir doesn't exist";
        if (!Files.exists(SAVE_FILE_PATH)) {
            Files.createFile(SAVE_FILE_PATH);
        }
    }
    
}

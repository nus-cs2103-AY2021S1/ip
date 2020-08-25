import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader {
    static boolean doesFileExist(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    static TaskList readListFromFile(String path) throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (TaskList) objectInputStream.readObject();
    }
}

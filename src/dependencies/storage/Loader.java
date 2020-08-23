package dependencies.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to handle the reading and writing to files.
 * Data read and written are objects implementating Serializable interface.
 *
 *
 * Errors and excpetions thrown during the reading and writing is handled here.
 *
 */
public class Loader {

    private static final Path WORKING_DIR_PATH = Paths.get(".");
    private static final Path DIR_PATH = Paths.get(".", "data");
    private static final Path FILE_PATH = Paths.get(".", "data").resolve("taskdata.txt");

    public Loader() {}

    public static <T> void save(T t) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH.toString(), false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.close();
            System.out.println("Successfully saved your data.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T openAndRead() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH.toString());
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.getStackTrace();
            throw new
        }
    }


}

package dependencies.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dependencies.dukeexceptions.MissingListException;

/**
 * Class to handle the reading and writing to files.
 * Data read and written are objects implementating Serializable interface.
 *
 *
 * Errors and excpetions thrown during the reading and writing is handled here.
 *
 */
public class Loader {

    private final Path DIR_PATH;
    private final Path FILE_PATH;

    private boolean isFilePresent;

    public Loader(String cwd, String dir, String fileName) {
        DIR_PATH = Paths.get(cwd, dir);
        FILE_PATH = DIR_PATH.resolve(fileName);
        isFilePresent = Files.exists(FILE_PATH);
    }

    public boolean isFilePresent() {
        return isFilePresent;
    }

    public void instantiateFile() {
        assert !isFilePresent;
        try {
            if (Files.exists(DIR_PATH)) {
                System.out.println("Dir exist");
                if (Files.exists(FILE_PATH)) {
                    System.out.println("File exists");
                } else {
                    Files.createFile(FILE_PATH);
                    System.out.println("File created");
                }
            } else {
                // Directory and storage file not present
                System.out.println("Creating Dir");
                Files.createDirectory(DIR_PATH);
                System.out.println("Dir created");
                System.out.println("Creating file");
                Files.createFile(FILE_PATH);
                System.out.println("File created");
            }
            isFilePresent = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unexpected error occurred while trying to create a file to save your data.");
        }
    }

    /**
     * Saves the given object. Overwrites the file.
     *
     * @param t the given object to overwrite with
     * @param <T> type of the object given
     */
    public <T> void overwriteAndSave(T t) {
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

    /**
     * Returns the object stored in the data file. Typecasts the object to
     * T.
     * @param <T> object of type T
     * @return T
     * @throws MissingListException
     */
    @SuppressWarnings("unchecked")
    public <T> T openAndReadObject() throws MissingListException {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH.toString());
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.getStackTrace();
            throw new MissingListException("Error: Unable to load your saved list");
        }
    }

    public static void main(String[] args) throws Exception {
        Loader l = new Loader(".", "data", "taskdata.txt");
        l.instantiateFile();
    }


}

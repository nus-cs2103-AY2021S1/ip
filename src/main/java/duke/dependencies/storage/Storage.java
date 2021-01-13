package duke.dependencies.storage;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.dependencies.dukeexceptions.MissingListException;

import duke.dependencies.parser.Parser;

/**
 * Utility lass to handle the reading and writing to files.
 * Data read and written are objects implementating Serializable interface.
 *
 *
 * Errors and exceptions thrown during the reading and writing is handled here.
 *
 */
public class Storage {

    /** Paths for sa. */
    private final Path DIR_PATH;
    private final Path FILE_PATH;
    private final static Path CURRENT_DIR_PATH = Paths.get(".");

    private boolean isContentStringalizable;

    /**
     * Constructor for the storage class to read and write to files.
     */
    public Storage(String dirName, String fileName) {
        DIR_PATH = Paths.get(".", dirName);
        FILE_PATH = DIR_PATH.resolve(fileName);
        isContentStringalizable = Parser.checkForWord(fileName, ".dat");
    }

    /**
     * Returns whether the save file is present.
     *
     * @return True if the file is present, else otherwise.
     */
    public boolean isSavedFilePresent() {
        return Files.exists(FILE_PATH );
    }

    /**
     * Instantiates the file in specified directory. Does nothing if the file is present.
     */
    public void instantiateFile() {
        assert !isSavedFilePresent();
        try {
            if (Files.exists(DIR_PATH)) {
                if (Files.exists(FILE_PATH)) {
                } else {
                    Files.createFile(FILE_PATH);
                }
                System.out.println("Clean slate: Initialising cache...");
            } else {
                Files.createDirectory(DIR_PATH);
                Files.createFile(FILE_PATH);
                System.out.println("Clean slate: Initialising cache...");

            }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the object stored in the data file.
     *
     * @param <T> Object of type T.
     * @return T The object stored int he file. Serializable.
     * @throws MissingListException if the file is not found.
     */
    @SuppressWarnings("unchecked")
    public <T> T openAndReadObject() throws MissingListException {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH.toString());
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (T) ois.readObject();
        } catch (Exception e) {
            throw new MissingListException("Error: Unable to load your saved list");
        }
    }

    /**
     * Reads the file contents as a String
     * @return
     */
    public String readDataFileAsString() {
        assert isContentStringalizable : "Wrong usage of storage: .dat file expected instead .txt file found.";
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH.toString()));
            String r = dis.readUTF();
            dis.close();
            return r;
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Writes the given string to the file. This overwrites previous data in the file. Equivalent to the creation of
     * a new user details.
     *
     * @param pw The string to be written to the file.
     */
    public void writeStringToFile(String pw) {
        assert isSavedFilePresent() && isContentStringalizable;
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH.toString()));
            dos.writeUTF(pw);
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ---------------------------------------- DEPRECATED ---------------------------------------------------------- */

//    /**
//     * Checks the
//     * @return
//     */
//    public boolean checkFileCache() {
//        try {
//            DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH.toString()));
//            dis.close();
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
//    }


}

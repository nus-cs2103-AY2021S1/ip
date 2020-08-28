package duke.dependencies.storage;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.dependencies.dukeexceptions.MissingListException;

/**
 * Utility lass to handle the reading and writing to files.
 * Data read and written are objects implementating Serializable interface.
 *
 *
 * Errors and exceptions thrown during the reading and writing is handled here.
 *
 */
public class Storage {

    private final Path PW_FILE_PATH;
    private final Path PW_DIR_PATH;

    /** Paths for saved list. */
    private final Path DIR_PATH;
    private final Path FILE_PATH;

    private boolean isFilePresent;

    /**
     * Constructor for the storage class to read and write to files.
     */
    public Storage() {
        DIR_PATH = Paths.get(".", "data");
        FILE_PATH = DIR_PATH.resolve("taskdata.txt");
        isFilePresent = Files.exists(FILE_PATH);
        PW_DIR_PATH = Paths.get(".", "cache");
        PW_FILE_PATH = PW_DIR_PATH.resolve("pw.dat");
    }

    /**
     * Returns whether the save file is present.
     *
     * @return True if the file is present, else otherwise.
     */
    public boolean isSavedFilePresent() {
        return isFilePresent;
    }

    /**
     * Instantiates a .txt file in /data directory to save task list of user. Does nothing if the file is present.
     */
    public void instantiateFile() {
        assert !isFilePresent;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the object stored in the data file. Typecasts the object to
     * T.
     * @param <T> Object of type T.
     * @return T
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

    public boolean checkUserAuth(String pw) {
        return pw.equals(readUserPw());
    }

    public String readUserPw() {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(PW_FILE_PATH.toString()));
            String r = dis.readUTF();
            dis.close();
            return r;
        } catch (IOException e) {
            return "";
        }
    }

    public void instantiatePwFile() {
        try {
            if (Files.exists(PW_DIR_PATH)) {
                if (Files.exists(PW_FILE_PATH)) {
                } else {
                    Files.createFile(PW_FILE_PATH);;
                }
                System.out.println("Clean slate: Initialising cache...");
            } else {
                Files.createDirectory(PW_DIR_PATH);
                Files.createFile(PW_FILE_PATH);
                System.out.println("Clean slate: Initialising cache...");

            }
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("Unexpected error occurred while trying to create a file to save your data.");
        }
    }

    public void saveUserPw(String pw) {
        if (!checkPwCache()) {
            instantiatePwFile();
        }
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(PW_FILE_PATH.toString()));
            dos.writeUTF(pw);
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkPwCache() {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(PW_FILE_PATH.toString()));
            dis.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }


}

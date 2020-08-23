package storage;

import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class is responsible for loading the storage system
 * and adding the printed text to the storage system.
 * @author Maguire Ong
 */

public class Storage {
    public String filePath;

    public final static String FILEPATH = System.getProperty("user.dir") + "/duke.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the storage system and creates the saved file
     * if it is not created yet.
     * @throws IOException  if the named file exists but
     * is a directory rather than a regular file
     */
    public void load() throws IOException {
        boolean directoryExists = new File(filePath).exists();

        if (!directoryExists) {
            FileWriter fw = new FileWriter(filePath, true);
        }
    }

    /**
     * Appends the printed text to the saved file.
     * @throws IOException  if the named file exists but
     * is a directory rather than a regular file
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        Ui.printCommand(textToAppend);
        fw.close();
    }


}
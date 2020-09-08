package com.siawsam.duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Represents a class to handle read-from-disk operations.
 */
public class Reader {
    /**
     * Checks if a list of files exists.
     *
     * @param filePaths The file paths to check.
     * @return True if all files exist, False otherwise.
     */
    static boolean doFilesExist(String... filePaths) {
        for (String path : filePaths) {
            File file = new File(path);
            boolean isInvalidFile = !(file.exists() && file.isFile());
            if (isInvalidFile) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reads a serialized {@link TaskList TaskList} instance from disk.
     *
     * @param path The file path of the serialized TaskList.
     * @return The deserialized {@link TaskList TaskList} instance.
     * @throws ClassNotFoundException if the file given doesn't contain a serialized
     * {@link TaskList TaskList} instance.
     * @throws IOException if an IO exception occurs while reading the file.
     */
    static TaskList readListFromFile(String path) throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (TaskList) objectInputStream.readObject();
    }
    
    static TagList readTagsFromFile(String path) throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (TagList) objectInputStream.readObject();
    }
}

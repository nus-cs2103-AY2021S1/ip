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
     * Checks if a file exists.
     *
     * @param path The file path to check.
     * @return True if the file exists, False otherwise.
     */
    static boolean doFilesExist(String taskListFilePath, String tagsFilePath) {
        File taskListFile = new File(taskListFilePath);
        File tagsFile = new File(tagsFilePath);
        return taskListFile.exists() && taskListFile.isFile()
                && tagsFile.exists() && tagsFile.isFile();
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

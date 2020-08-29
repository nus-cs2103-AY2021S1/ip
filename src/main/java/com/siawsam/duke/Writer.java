package com.siawsam.duke;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Represents a class to handle write-to-disk operations.
 */
public class Writer {
    /**
     * Writes a TaskList instance to a file on disk.
     *
     * @param taskList The TaskList instance to write.
     * @param path The file path to write to.
     */
    static void writeListToFile(TaskList taskList, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(taskList);
            objectOutputStream.flush();
            objectOutputStream.close();
            Ui.showSuccessfulSave();
        } catch (IOException e) {
            Ui.showErrorMessage(e);
        }
    }
}

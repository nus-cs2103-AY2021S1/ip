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
     * @return A response representing the result of the write operation.
     */
    static Response writeListToFile(TaskList taskList, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(taskList);
            objectOutputStream.flush();
            objectOutputStream.close();
            return new Response(Ui.showSuccessfulSave());
        } catch (IOException e) {
            return new Response(Ui.showErrorMessage(e));
        }
    }
}

package com.siawsam.duke;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Represents a class to handle write-to-disk operations.
 */
public class Writer {
    /**
     * Serializes and writes a {@link TagList tag list}  and {@link TaskList task list} to disk.
     *
     * @param taskList The task list to save.
     * @param tagList The tag list to save.
     * @param taskListPath The write path for the task list.
     * @param tagListPath The write path for the tag list.
     * @return A Response indicating the result of the write operation.
     */
    static Response writeToFile(
            TaskList taskList, TagList tagList,
            String taskListPath, String tagListPath
    ) {
        try {
            writeListToFile(taskList, taskListPath);
            writeTagsToFile(tagList, tagListPath);
            return new Response(Ui.showSuccessfulSave());
        } catch (IOException e) {
            return new Response(Ui.showErrorMessage(e));
        }
    }
    
    private static void writeListToFile(TaskList taskList, String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(taskList);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
    
    private static void writeTagsToFile(TagList tagList, String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(tagList);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}

package com.siawsam.duke;

import java.io.IOException;

/**
 * Represents a class that handles disk operations for Duke.
 */
public class Storage {
    /** The file path associated with the current running instance of Duke. */
    private final String taskListFilePath;
    private final String tagsFilePath;
    private TaskList loadedTaskList;
    private TagList loadedTagList;

    /**
     * Constructs a new Storage instance with an associated file path.
     *
     * @param taskListFilePath file path associated with a saved task list.
     * @param tagsFilePath file path associated with a saved tag list.
     */
    public Storage(String taskListFilePath, String tagsFilePath) {
        this.taskListFilePath = taskListFilePath;
        this.tagsFilePath = tagsFilePath;
    }

    /**
     * Loads a TaskList instance from disk.
     *
     * @return The saved TaskList.
     * @throws IOException if an IO exception occurs while reading.
     * @throws ClassNotFoundException if the file cannot be deserialized into a TaskList.
     */
    void load() throws IOException, ClassNotFoundException {
        loadedTaskList = Reader.readListFromFile(taskListFilePath);
        loadedTagList = Reader.readTagsFromFile(tagsFilePath);
    }
    
    TaskList getLoadedTasks() {
        return loadedTaskList;
    }
    
    TagList getLoadedTags() {
        return loadedTagList;
    }

    /**
     * Saves a TaskList instance to disk.
     *
     * @param taskList The TaskList instance to save.
     * @return A response representing the result of the save operation.
     */
    Response save(TaskList taskList, TagList tagList) {
        return Writer.writeToFile(taskList, tagList, taskListFilePath, tagsFilePath);
    }

    /**
     * Checks if a file already exists at the storage file path.
     *
     * @return True if file exists, False if otherwise.
     */
    boolean doStorageFilesExist() {
        return Reader.doFilesExist(taskListFilePath, tagsFilePath);
    }
}

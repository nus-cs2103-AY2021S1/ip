package com.siawsam.duke;

import java.io.IOException;

/**
 * Represents a class that handles disk operations for Duke.
 */
public class Storage {
    private final String taskListFilePath;
    private final String tagsFilePath;
    private TaskList loadedTaskList;
    private TagList loadedTagList;

    /**
     * Constructs a new Storage instance with the associated file paths.
     *
     * @param taskListFilePath File path associated with a saved task list.
     * @param tagsFilePath File path associated with a saved tag list.
     */
    public Storage(String taskListFilePath, String tagsFilePath) {
        this.taskListFilePath = taskListFilePath;
        this.tagsFilePath = tagsFilePath;
    }

    /**
     * Loads a {@link TaskList task list} and {@link TagList tag list} from disk.
     *
     * @throws IOException if an IO exception occurs while reading.
     * @throws ClassNotFoundException if the files cannot be deserialized into a TaskList/TagList.
     */
    void load() throws IOException, ClassNotFoundException {
        loadedTaskList = Reader.readListFromFile(taskListFilePath);
        loadedTagList = Reader.readTagsFromFile(tagsFilePath);
    }
    
    /**
     * Return the {@link TaskList task list} that was read from disk.
     *
     * @return The loaded task list.
     */
    TaskList getLoadedTasks() {
        return loadedTaskList;
    }
    
    /**
     * Return the {@link TagList tag list} that was read from disk.
     *
     * @return The loaded tag list.
     */
    TagList getLoadedTags() {
        return loadedTagList;
    }

    /**
     * Saves a {@link TaskList task list} and {@link TagList tag list} to disk.
     *
     * @param taskList The TaskList instance to save.
     * @param tagList The TagList instance to save.
     * @return A response representing the result of the save operation.
     */
    Response save(TaskList taskList, TagList tagList) {
        return Writer.writeToFile(taskList, tagList, taskListFilePath, tagsFilePath);
    }

    /**
     * Checks if the Duke save-files already exist at the storage file path.
     *
     * @return True if files exist, False if otherwise.
     */
    boolean doStorageFilesExist() {
        return Reader.doFilesExist(taskListFilePath, tagsFilePath);
    }
}

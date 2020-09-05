package duke.main;

import java.util.List;
import java.util.stream.Collectors;

import duke.errors.DuplicateTaskException;
import duke.errors.InvalidIndexException;
import duke.tasks.Task;

/**
 * The type Task list.
 */
public class TaskList {
    /**
     * The Storage.
     */
    private Storage storage;

    /**
     * Instantiates a new Task list.
     *
     * @param storage the storage
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        /*
           - Values are constantly updated to prevent User's data
           from disappearing if they quit halfway through.
           - This however reduces the efficiency as it has to
           pull and push to the txt file each time.
         */
    }

    /**
     * Gets list.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Task> getList() throws Exception {
        return storage.getFileContents();
    }

    /**
     * Checks if index is within acceptable range
     *
     * @param i index for fileContents
     * @param fileContents List of Tasks
     * @throws Exception the exception
     */
    private void checkIndex(int i, List<Task> fileContents) throws Exception {
        boolean greaterThanOne = i > 0;
        boolean smallerThanFileSize = i <= fileContents.size();
        assert (greaterThanOne || smallerThanFileSize) : "Index out of range";
        if (!(greaterThanOne && smallerThanFileSize)) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Pulls from txt file into a Task object
     *
     * @param i Line to get
     * @return Task object
     * @throws Exception the exception
     */
    public Task getTask(int i) throws Exception {
        List<Task> fileContents = storage.getFileContents();

        checkIndex(i, fileContents);

        return fileContents.get(i - 1);
    }

    /**
     * Adds a Task into txt file as a string
     *
     * @param arr Array of respective values representing a Task
     * @throws Exception the exception
     */
    public void addTask(String[] arr) throws Exception {
        List<Task> fileContents = storage.getFileContents();

        // We append first since it helps us to parse into the correct format
        String dataToStore = Task.stringFormat(arr);
        storage.appendToFile(dataToStore);

        // Check for duplicate and delete
        int newFileSize = getSize();
        Task addedTask = getTask(newFileSize);
        for (int i = 0; i < fileContents.size(); i++) {
            if ((fileContents.get(i)).equals(addedTask)) {
                // Delete newly added task if it's a duplicate
                deleteTask(newFileSize);
                throw new DuplicateTaskException();
            }
        }
    }

    /**
     * Completes a Task
     *
     * @param i Line to update
     * @return Completed Task object
     * @throws Exception the exception
     */
    public Task completeTask(int i) throws Exception {
        List<Task> fileContents = storage.getFileContents();
        checkIndex(i, fileContents);

        Task t = fileContents.get(i - 1);

        fileContents.get(i - 1).setStatus("1");
        storage.rewriteFileContents(fileContents);
        return t;
    }

    /**
     * Deletes a Task
     *
     * @param i Line to delete
     * @return Delete Task object
     * @throws Exception the exception
     */
    public Task deleteTask(int i) throws Exception {
        List<Task> fileContents = storage.getFileContents();
        checkIndex(i, fileContents);

        Task t = fileContents.get(i - 1);

        fileContents.remove(i - 1);
        storage.rewriteFileContents(fileContents);
        return t;
    }

    /**
     * Find List of Tasks by filter
     *
     * @param filter Text to filter Task Descriptions
     * @return List of filtered Tasks
     * @throws Exception the exception
     */
    public List<Task> findTasks(String filter) throws Exception {
        List<Task> filteredContents = storage.getFileContents()
            .stream()
            .filter(Task -> Task.getDescription().contains(filter))
            .collect(Collectors.toList());
        return filteredContents;
    }

    /**
     * Gets total number of lines on txt
     *
     * @return size size
     * @throws Exception the exception
     */
    public int getSize() throws Exception {
        List<Task> fileContents = storage.getFileContents();
        return fileContents.size();
    }
}

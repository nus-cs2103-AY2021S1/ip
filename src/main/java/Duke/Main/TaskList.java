package duke.main;

import java.util.List;
import java.util.stream.Collectors;

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
     * Pulls from txt file into a Task object
     *
     * @param i Line to get
     * @return Task object
     * @throws Exception the exception
     */
    public Task getTask(int i) throws Exception {
        List<Task> filecontents = storage.getFileContents();
        if (i <= 0 || i > filecontents.size()) {
            throw new InvalidIndexException();
        }
        return filecontents.get(i - 1);
    }

    /**
     * Adds a Task into txt file as a string
     *
     * @param arr Array of respective values representing a Task
     * @throws Exception the exception
     */
    public void addTask(String[] arr) throws Exception {
        storage.appendToFile(Task.stringFormat(arr));
    }

    /**
     * Completes a Task
     *
     * @param i Line to update
     * @return Completed Task object
     * @throws Exception the exception
     */
    public Task completeTask(int i) throws Exception {
        List<Task> filecontents = storage.getFileContents();
        Task t = filecontents.get(i - 1);
        if (i <= 0 || i > filecontents.size()) {
            throw new InvalidIndexException();
        }
        filecontents.get(i - 1).setStatus("1");
        storage.rewriteFileContents(filecontents);
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
        List<Task> filecontents = storage.getFileContents();
        Task t = filecontents.get(i - 1);
        if (i <= 0 || i > filecontents.size()) {
            throw new InvalidIndexException();
        }
        filecontents.remove(i - 1);
        storage.rewriteFileContents(filecontents);
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
        List<Task> filterdContents = storage.getFileContents()
            .stream()
            .filter(Task -> Task.getDescription().contains(filter))
            .collect(Collectors.toList());
        return filterdContents;
    }

    /**
     * Gets total number of lines on txt
     *
     * @return size
     * @throws Exception the exception
     */
    public int getSize() throws Exception {
        List<Task> filecontents = storage.getFileContents();
        return filecontents.size();
    }
}

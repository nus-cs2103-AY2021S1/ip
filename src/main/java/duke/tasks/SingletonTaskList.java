package duke.tasks;

import java.util.List;

import duke.exceptions.TaskOutOfBoundException;
import duke.storage.Storage;
import duke.ui.Printer;


/**
 * Class for task list.
 */
public class SingletonTaskList {

    private static SingletonTaskList instance; //this class should only be instantiate once

    private Storage storage;

    private List<Task> tasks;

    private SingletonTaskList(Storage database) {
        this.storage = database;
        tasks = storage.readAll();
    }

    public static synchronized SingletonTaskList getInstance(Storage database) {
        if (instance == null) {
            instance = new SingletonTaskList(database);
        }
        return instance;
    }

    /**
     * Add task to the task list and the database.
     * @param task task to add
     */
    public void add(Task task) {
        tasks.add(task);
        int numOfTask = tasks.size();
        Printer.printAdd(task, numOfTask);
        storage.update(tasks);
    }

    /**
     * Delete task from the task list ant the database.
     * @param idx the idx of task to delete
     * @throws TaskOutOfBoundException when the idx is not valid
     * @see duke.exceptions.TaskOutOfBoundException
     */
    public void delete(int idx) throws TaskOutOfBoundException {
        try {
            Printer.printDelete(tasks.get(idx - 1), tasks.size() - 1);
            tasks.remove(idx - 1);
            storage.update(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("task number out of bound", idx);
        }
    }

    /**
     * List all the tasks in the task list.
     */
    public void listAll() {
        if (tasks.size() == 0) {
            Printer.printNoTaskReminder();
        }
        Printer.printAllTask(tasks, true);
    }

    /**
     * Set {@code idx} of task done.
     * @param idx idx of the task
     * @throws TaskOutOfBoundException when the {@code idx} is not valid
     * @see duke.exceptions.TaskOutOfBoundException
     */
    public void setTaskDone(int idx) throws TaskOutOfBoundException {
        try {
            Task task = this.tasks.get(idx);
            task.setStatus(true);
            Printer.printDoneTask(task);
            storage.update(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("Target number of task out of bound", idx + 1);
        }
    }

    /**
     * Query tasks in the taskList with the {@code queryKey}.
     * @param queryKey the key used to query the tasks
     */
    public void query(String queryKey) {
        List<Task> matchedTasks = storage.query(queryKey);
        Printer.printAllTask(matchedTasks, false);
    }

}

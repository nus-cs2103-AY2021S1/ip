package duke.tasks;

import java.util.List;

import duke.exceptions.DukeException;
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
    public String add(Task task) {
        tasks.add(task);
        int numOfTask = tasks.size();
        String message = Printer.printAdd(task, numOfTask);
        storage.update(tasks);
        return message;
    }

    /**
     * Delete task from the task list ant the database.
     * @param idx the idx of task to delete
     * @throws TaskOutOfBoundException when the idx is not valid
     * @see duke.exceptions.TaskOutOfBoundException
     */
    public String delete(int idx) throws TaskOutOfBoundException {
        try {
            String message = Printer.printDelete(tasks.get(idx), tasks.size() - 1);
            tasks.remove(idx);
            storage.update(tasks);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("task number out of bound", idx + 1);
        }
    }

    /**
     * List all the tasks in the task list.
     */
    public String listAll() throws DukeException {
        if (tasks.size() == 0) {
            return Printer.printNoTaskReminder();
        }
        return Printer.printAllTask(tasks, true);
    }

    /**
     * Set {@code idx} of task done.
     * @param idx idx of the task
     * @throws TaskOutOfBoundException when the {@code idx} is not valid
     * @see duke.exceptions.TaskOutOfBoundException
     */
    public String setTaskDone(int idx) throws TaskOutOfBoundException {
        try {
            Task task = this.tasks.get(idx);
            task.setStatus(true);
            String message = Printer.printDoneTask(task);
            storage.update(tasks);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("Target number of task out of bound", idx + 1);
        }
    }

    /**
     * Query tasks in the taskList with the {@code queryKey}.
     * @param queryKey the key used to query the tasks
     */
    public String query(String queryKey) throws DukeException {
        List<Task> matchedTasks = storage.query(queryKey);
        return Printer.printAllTask(matchedTasks, false);
    }

}

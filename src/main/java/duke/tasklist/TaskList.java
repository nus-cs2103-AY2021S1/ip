package duke.tasklist;

import java.util.ArrayList;
import java.util.Iterator;

import duke.dukeexception.DukeTaskNonExistException;
import duke.task.Task;

/**
 * The TaskList class is responsible for storing the different tasks in an ArrayList.
 */
public class TaskList {

    /**
     * The arraylist that is used to store the tasks
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList object.
     *
     * @param tasks An ArrayList that encapsulates Tasks objects
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter to get a task from the arraylist.
     *
     * @param index Zero-based index to get the task from the arraylist
     * @return Returns the Task that is gotten from the arraylist
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * This method adds a task to the back of the arraylist.
     *
     * @param task Task object to be added to the arraylist
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task in the Arraylist as complete.
     *
     * @param index Zero-based index on where the task is in the Arraylist
     * @return the task in the index that is completed
     * @throws DukeTaskNonExistException if the index provided is out of range of the arraylist
     */
    public Task completeTask(int index) throws DukeTaskNonExistException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskNonExistException("error");
        }
        Task task = this.tasks.get(index);
        task.complete();
        assert task.isComplete() : "Task has failed to complete";
        return task;
    }


    /**
     * Getter for the size of the arraylist
     *
     * @return the size of the arraylist
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * A method that iterates through the arraylist, starting from the oldest first, and printing them, indicating the
     * id of the task. The first task would be id 1, second task would be id 2 and so on.
     */
    public String iterate() {
        Iterator<Task> iter = tasks.iterator();
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: ").append('\n');
        int counter = 1;
        while (iter.hasNext()) {
            sb.append(counter).append(". ").append(iter.next()).append('\n');
            counter++;
        }
        return sb.toString();
    }

    /**
     * A method that deletes a given task from the arraylist based on the index given.
     *
     * @param index Zero-based index of the task in the arraylist.
     * @throws DukeTaskNonExistException if the index provided is out of range of the arraylist.
     */
    public void delete(int index) throws DukeTaskNonExistException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskNonExistException("error");
        }
        this.tasks.remove(index);
    }

    /**
     * A method that finds a given task from the arraylist based on the respomse given.
     * It will return all tasks that contains the keyword that was specified.
     *
     * @param response The string that will be identified from the tasks.
     */
    public String findTask(String response) {
        ArrayList<Task> temp = getSearchResults(response);
        return returnSearchResult(temp);
    }

    private String returnSearchResult(ArrayList<Task> temp) {
        Iterator<Task> iter;
        iter = temp.iterator();
        int counter = 1;
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list: ").append('\n');
        while (iter.hasNext()) {
            sb.append(counter).append(". ").append(iter.next()).append('\n');
            counter++;
        }
        return sb.toString();
    }

    private ArrayList<Task> getSearchResults(String response) {
        Iterator<Task> iter = tasks.iterator();
        ArrayList<Task> tasks = new ArrayList<>();
        while (iter.hasNext()) {
            Task task = iter.next();
            if (task.getName().contains(response)) {
                tasks.add(task);
            }
        }
        return tasks;
    }

}

package duke.tasklist;

import duke.dukeexception.DukeTaskNonExistException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The TaskList class is responsible for storing the different tasks in an ArrayList.
 */
public class TaskList {

    /**
     * The arraylist that is used to store the tasks
     */
    private final ArrayList<Task> shelf;

    /**
     * Constructor for the TaskList object.
     *
     * @param shelf An ArrayList that encapsulates Tasks objects
     */
    public TaskList(ArrayList<Task> shelf) {
        this.shelf = shelf;
    }

    /**
     * Getter to get a task from the arraylist.
     *
     * @param index Zero-based index to get the task from the arraylist
     * @return Returns the Task that is gotten from the arraylist
     */
    public Task getTask(int index) {
        return this.shelf.get(index);
    }

    /**
     * This method adds a task to the back of the arraylist.
     *
     * @param task Task object to be added to the arraylist
     */
    public void addTask(Task task) {
        this.shelf.add(task);
    }

    /**
     * Marks a task in the Arraylist as complete.
     *
     * @param index Zero-based index on where the task is in the Arraylist
     * @return the task in the index that is completed
     * @throws DukeTaskNonExistException if the index provided is out of range of the arraylist
     */
    public Task completeTask(int index) throws DukeTaskNonExistException {
        if (index < 0 | index >= shelf.size()) {
            throw new DukeTaskNonExistException("error");
        }
        Task book = this.shelf.get(index);
        book.complete();
        return book;
    }


    /**
     * Getter for the size of the arraylist
     *
     * @return the size of the arraylist
     */
    public int getSize() {
        return this.shelf.size();
    }

    /**
     * A method that iterates through the arraylist, starting from the oldest first, and printing them, indicating the
     * id of the task. The first task would be id 1, second task would be id 2 and so on.
     */
    public void iterate() {
        Iterator<Task> iter = shelf.iterator();
        int counter = 1;
        System.out.println("Here are the tasks in your list: ");
        while (iter.hasNext()) {
            System.out.println(counter + ". " + iter.next());
            counter++;
        }
    }

    /**
     * A method that deletes a given task from the arraylist based on the index given.
     *
     * @param index Zero-based index of the task in the arraylist
     * @throws DukeTaskNonExistException if the index provided is out of range of the arraylist
     */
    public void delete(int index) throws DukeTaskNonExistException {
        if (index < 0 | index >= shelf.size()) {
            throw new DukeTaskNonExistException("error");
        }
        this.shelf.remove(index);
    }

    public void find(String response) {
        Iterator<Task> iter = shelf.iterator();
        ArrayList<Task> temp = new ArrayList<>();
        while (iter.hasNext()) {
            Task book = iter.next();
            if(book.getName().contains(response)){
                temp.add(book);
            }
        }
        iter = temp.iterator();
        int counter = 1;
        System.out.println("Here are the matching tasks in your list: ");
        while (iter.hasNext()) {
            System.out.println(counter + ". " + iter.next());
            counter++;
        }
    }

}

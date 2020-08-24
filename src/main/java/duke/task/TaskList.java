package duke.task;

import duke.exceptions.DukeException;
import duke.ui.Messenger;

import java.util.ArrayList;

/**
 * Encapsulate a class that represents a list of tasks that the user entered.
 */
public class TaskList {
    private ArrayList<Task> tasks; // a list of tasks

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets all the tasks of a list.
     *
     * @return an ArrayList including all the tasks in the list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints out all the tasks in the list in their string representation.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        int size = tasks.size();
        for (int i = 0; i < size; ++i) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Marks the task of index as completed.
     *
     * @param index the index of the task to be marked as completed.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index - 1).markAsDone();
        Messenger.markAsDoneMessage(tasks.get(index - 1));
    }

    /**
     * Adds a new task that has an attached date to the list.
     *
     * @param content the content of the task.
     * @param type the type of the task.
     * @param date the date of the task.
     */
    public void addTask(String content, String type, String date) {
        Task newTask = new Task(content, type, date);
        tasks.add(newTask);
        int size = tasks.size();
        Messenger.addTaskMessage(newTask, size);
    }

    /**
     * Adds a new task that has no attached date to the list.
     *
     * @param content the content of the task.
     * @param type the type of the task.
     */
    public void addTask(String content, String type) {
        Task newTask = new Task(content, type);
        tasks.add(newTask);
        int size = tasks.size();
        Messenger.addTaskMessage(newTask, size);
    }

    /**
     * Deletes the task of index from the list.
     *
     * @param index the index of the task to be deleted.
     * @throws DukeException throws an index out of bound exception.
     */
    public void deleteTask(int index) throws DukeException {
        int size = tasks.size();
        if (index > size) {
            throw new DukeException(Messenger.INDEX_OUT_OF_BOUND_ERROR);
        }
        Messenger.deleteTaskMessage(tasks.get(index - 1), size - 1);
        tasks.remove(index - 1);
    }

    /**
     * Finds the tasks that have the keyword.
     *
     * @param keyword the keyword to be searched for.
     */
    public void findTask(String keyword) {
        int index = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            String content = task.getContent();
            if (content.contains(keyword)) {
                System.out.println(index + "." + task.toString());
                index++;
            }
        }
    }
}

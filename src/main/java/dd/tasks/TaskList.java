package dd.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A task list contains tasks in an array list.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private int numOfTodo = 0;

    /**
     * Class Constructor.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Class Constructor with specified file to fetch data from.
     *
     * @param f Specified file to fetch data from to create TaskList.
     * @throws FileNotFoundException When file is not found.
     */
    public TaskList(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> scannedTasks = new ArrayList<>();

        while (s.hasNext()) {
            String[] temp = s.nextLine().split(" , ");

            if (temp[0].equals("T")) {
                scannedTasks.add(new Todo(temp[2]));
                this.numOfTodo += 1;
            } else if (temp[0].equals("E")) {
                scannedTasks.add(new Event(temp[2], temp[3]));
            } else {
                scannedTasks.add(new Deadline(temp[2], temp[3]));
            }

            if (temp[1].equals("1")) {
                scannedTasks.get(scannedTasks.size() - 1).markAsDone();
            }
        }
        this.taskList = scannedTasks;
    }

    /**
     * Returns the ArrayList of tasks saved.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the number of tasks saved.
     *
     * @return The size of ArrayList of tasks.
     */
    public int getTaskSize() {
        return taskList.size();
    }

    /**
     * Returns a task according to a given index.
     *
     * @param i Index of Task in tasks to be returned.
     * @return Task at the given index.
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Adds a Task given into tasks.
     *
     * @param t Task to be added to ArrayList of tasks.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Adds a To-do task, ensures that all to-do tasks are at the top of list
     * and increase number of to-do by one.
     *
     * @param t To-do task to be added to ArrayList of tasks.
     */
    public void addTodo(Task t) {
        taskList.add(numOfTodo, t);
        this.numOfTodo += 1;
    }

    /**
     * Deletes the task at a given index.
     *
     * @param i Index of Task in tasks to be deleted.
     */
    public void deleteTask(int i) {
        if (taskList.get(i) instanceof Todo) {
            this.numOfTodo -= 1;
        }
        taskList.remove(i);
    }

    /**
     * Sorts the deadline and event tasks chronologically.
     */
    public void sortTasks() {
        assert (!(taskList.get(numOfTodo) instanceof Todo)) : "trying to sort todo";
        taskList.subList(numOfTodo, getTaskSize()).sort(Comparator.comparing(Task::getDateTime));
    }
}

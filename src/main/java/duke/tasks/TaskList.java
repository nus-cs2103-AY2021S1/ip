package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.views.Messenger;


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
     *
     * @return a string representing the printed content of the task list.
     */
    public String printList() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        int size = tasks.size();
        for (int i = 0; i < size; ++i) {
            output.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Marks the task of index as completed.
     *
     * @param index the index of the task to be marked as completed.
     * @return a string representing the message after the task is marked done.
     */
    public String markTaskAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n    " + task;
    }

    /**
     * Adds a new task that has an attached date to the list.
     *
     * @param content the content of the task.
     * @param type the type of the task.
     * @param date the date of the task.
     * @return a string representing the message for adding a task.
     */
    public String addTask(String content, String type, String date) {
        Task newTask = new Task(content, type, date);
        tasks.add(newTask);
        int size = tasks.size();
        String output = "Got it. I've added this task:    " + newTask.toString() + "\n";
        output += String.format("Now you have %s %s in the list.", size, (size > 1 ? "tasks" : "task"));
        return output;
    }

    /**
     * Adds a new task that has an attached date to the list.
     *
     * @param content the content of the task.
     * @param type the type of the task.
     * @return a string representing the message for adding a task.
     */
    public String addTask(String content, String type) {
        Task newTask = new Task(content, type);
        tasks.add(newTask);
        int size = tasks.size();
        String output = "Got it. I've added this task:    " + newTask.toString() + "\n";
        output += String.format("Now you have %s %s in the list.", size, (size > 1 ? "tasks" : "task"));
        return output;
    }

    /**
     * Deletes the task of index from the list.
     *
     * @param index the index of the task to be deleted.
     * @return a string representing the message for deleting a task.
     * @throws DukeException throws an index out of bound exception.
     */
    public String deleteTask(int index) throws DukeException {
        int size = tasks.size();
        Task task = tasks.get(index - 1);
        if (index > size) {
            throw new DukeException(Messenger.INDEX_OUT_OF_BOUND_ERROR);
        }
        String output = "Noted. I've removed this task:\n";
        output += task + "\n";
        output += String.format("Now you have %s %s in the list.", size, (size > 1 ? "tasks" : "task"));
        tasks.remove(index - 1);
        return output;
    }

    /**
     * Finds the tasks that have the keyword.
     *
     * @param keyword the keyword to be searched for.
     * @return a string representing the message for the found task.
     */
    public String findTask(String keyword) {
        int index = 1;
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : tasks) {
            String content = task.getContent();
            if (content.contains(keyword)) {
                output.append(index).append(".").append(task.toString()).append("\n");
                index++;
            }
        }
        return output.toString();
    }

    /**
     * Clears the tasks in the list.
     */
    public void clearTasks() {
        tasks.clear();
    }
}

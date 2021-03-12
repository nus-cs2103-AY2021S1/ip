package junimo.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains and handles the task list.
 */
public class TaskList {
    private final List<Task> list;
    private final List<Task> archives;

    /**
     * Constructs an instance of TaskList with an empty task list.
     */
    public TaskList() {
        list = new ArrayList<>();
        archives = new ArrayList<>();
    }

    /**
     * Returns the task list containing the user's task.
     * @return List containing the user's Task.
     */
    public List<Task> getTaskList() {
        return list;
    }

    /**
     * Returns the archives list containing the user's archived task.
     * @return List containing the user's archived Task.
     */
    public List<Task> getArchives() {
        return archives;
    }

    /**
     * Returns the String representing the specific task.
     * @param listIndex Index of the task as shown in the printed list (starting from 1).
     * @return String representing the task.
     */
    public String printTask(int listIndex, Task task) {
        return String.format("%d. %s", listIndex, task);
    }

    private String printList(List<Task> list, String listObjectDescription) {
        StringBuilder response = new StringBuilder();
        response.append(String.format("Here are the %s in your list:", listObjectDescription));
        for (int i = 0; i < list.size(); i++) {
            response.append("\n");
            response.append(printTask(i + 1, list.get(i)));
        }
        System.out.println(response.toString());
        return response.toString();
    }

    /**
     * Returns a String representing the list of the user's tasks.
     * @return String representing list of user's tasks.
     */
    public String list() {
        return printList(list, "tasks");
    }

    /**
     * Prints and returns a list of the user's archived tasks.
     * @return String representing list of user's archived tasks.
     */
    public String showArchives() {
        return printList(archives, "archived tasks");
    }

    /**
     * Prints and returns the task added and number of tasks in the list.
     * @param task The task added.
     * @return String response that task has been successfully added.
     */
    public String echo(String task) {
        String echo = String.format("added: %s.\nNow you have %d tasks in the list.", task, list.size());
        System.out.println(echo);
        return echo;
    }

    /**
     * Handles user input command related to adding new task to the list.
     * Throws errors to be caught and handled by parser if user input commands are in an invalid format.
     * @param task Task to add to task list.
     */
    public String addTask(Task task) {
        list.add(task);
        return echo(task.toString());
    }

    /**
     * Adds archived task to list of archives.
     * @param task Task to be archived.
     * @return null.
     */
    public String addArchivedTask(Task task) {
        archives.add(task);
        return null;
    }

    /**
     * Handles the marking of tasks in the list as done. Feedbacks to user if list index of task is invalid.
     * @param listIndexString List index of task to mark as done (starting from 1).
     * @return String response of whether task was successfully marked as done or not.
     */
    public String markTaskAsDone(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = list.get(listIndexNumber - 1);
            task.setDoneAsTrue();
            String response = String.format("Nice! I've marked this task as done:\n%s", task);
            System.out.println(response);
            return response;

        } catch (IndexOutOfBoundsException ex) { // if list index is not in the list
            String response = "OOPS! This task index does not exist! Type 'list' to check out your tasks.";
            System.out.println(response);
            return response;

        } catch (NumberFormatException ex) { // if list index string is not an integer
            String response = "OOPS! The keyword 'done' is used to check off tasks as follows:"
                    + "   done <task index>";
            System.out.println(response);
            return response;
        }
    }

    /**
     * Handles the deleting of tasks from task list. Feedbacks to user if list index of task is invalid.
     * @param listIndexString List index of task to delete (starting from 1).
     * @return String response of whether task was successfully deleted or not.
     */
    public String deleteTask(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = list.get(listIndexNumber - 1);
            list.remove(listIndexNumber - 1);
            assert !list.contains(task);
            String response = "The following task has been deleted:\n" + task;
            System.out.println(response);
            return response;

        } catch (IndexOutOfBoundsException ex) {
            String response = "OOPS! This task index does not exist! Type 'list' to check out your tasks.";
            System.out.println(response);
            return response;

        } catch (NumberFormatException ex) { // if list index string is not an integer
            String response = "OOPS! The keyword 'delete' is used to delete tasks as follows:"
                    + "   delete <task index>";
            System.out.println(response);
            return response;
        }
    }

    /**
     * Returns a list of Task with descriptions containing the keyword.
     * @param keyword The keyword used to filter Task descriptions.
     * @return String representing list of Task with descriptions containing the keyword.
     */
    public String find(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return printList(matchingTasks, "matching tasks");
    }

    /**
     * Archives task.
     * @param listIndexString List index of task to archive (starting from 1).
     * @return String response of whether the whether the task was successfully archived or not.
     */
    public String archive(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = list.get(listIndexNumber - 1);
            list.remove(listIndexNumber - 1);
            archives.add(task);
            String response = "The following task has been archived:\n" + task;
            System.out.println(response);
            return response;
        } catch (IndexOutOfBoundsException ex) {
            String response = "OOPS! This task index does not exist! Type 'list' to check out your tasks.";
            System.out.println(response);
            return response;
        } catch (NumberFormatException ex) { // if list index string is not an integer
            String response = "OOPS! The keyword 'archive' is used to archive tasks as follows:"
                    + "   archive <task index>";
            System.out.println(response);
            return response;
        }
    }

    /**
     * Unarchive tasks.
     * @param listIndexString Archive list index of task to unarchive (starting from 1).
     * @return String response of whether the whether the archived task was successfully unarchived or not.
     */
    public String unarchive(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = archives.get(listIndexNumber - 1);
            archives.remove(listIndexNumber - 1);
            list.add(task);
            String response = "The following task has been unarchived:\n" + task;
            System.out.println(response);
            return response;
        } catch (IndexOutOfBoundsException ex) {
            String response = "OOPS! This archived task index does not exist! Type 'archives' to check out your "
                    + "archived tasks.";
            System.out.println(response);
            return response;
        } catch (NumberFormatException ex) { // if list index string is not an integer
            String response = "OOPS! The keyword 'unarchive' is used to unarchive tasks as follows:"
                    + "   unarchive <task index>";
            System.out.println(response);
            return response;
        }
    }
}

package duke;

import java.util.ArrayList;

import duke.exceptions.EmptyCommandException;
import duke.exceptions.EmptyFindException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.MissingTimeException;
import duke.exceptions.TaskCompletionException;
import duke.exceptions.TaskDeletionException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Stores the current list of tasks.
 */

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints all the tasks line by line to the user.
     *
     * @return All tasks in order.
     */
    public String displayTasks() {
        StringBuilder res = new StringBuilder("Here are the tasks in your list:");
        int i = 1;
        for (Task t : tasks) {
            res.append("\n").append(i).append(".").append(t.toString());
            i++;
        }
        return res.toString();
    }

    /**
     * Adds a task to the list based on user input.
     *
     * @param str User input
     * @return A string to indicate task has been added.
     * @throws InvalidCommandException If command is not valid.
     * @throws EmptyCommandException   If task is missing description.
     * @throws MissingTimeException    If task is missing time.
     */
    public String addTask(String str) throws InvalidCommandException, EmptyCommandException, MissingTimeException {
        if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
            throw new EmptyCommandException(str);
        }
        if (str.contains(" ")) {
            String[] arr = str.split(" ", 2);
            String str2 = arr[1];
            switch (arr[0]) {
            case "todo":
                ToDo td = new ToDo(str2);
                return insert(td);
            case "deadline":
                if (str2.contains("/by")) {
                    String[] arr2 = str2.split("/by", 2);
                    if (arr2[0].isBlank()) {
                        throw new EmptyCommandException("deadline");
                    }
                    if (arr2[1].isBlank()) {
                        throw new MissingTimeException("deadline");
                    }
                    Deadline dl = new Deadline(arr2[0], arr2[1].trim());
                    return insert(dl);
                } else {
                    throw new MissingTimeException("deadline");
                }
            case "event":
                if (str2.contains("/at")) {
                    String[] arr2 = str2.split("/at", 2);
                    if (arr2[0].isBlank()) {
                        throw new EmptyCommandException("event");
                    }
                    if (arr2[1].isBlank()) {
                        throw new MissingTimeException("event");
                    }
                    Event ev = new Event(arr2[0], arr2[1].trim());
                    return insert(ev);
                } else {
                    throw new MissingTimeException("event");
                }
            default:
                throw new InvalidCommandException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Finds and displays tasks containing the relevant keyword
     *
     * @param str The keyword the user is searching for.
     * @return The tasks which contain the keyword.
     * @throws EmptyFindException      If there is no keyword given.
     * @throws InvalidCommandException If command is not valid.
     **/
    public String find(String str) throws EmptyFindException, InvalidCommandException {
        if (str.equals("find")) {
            throw new EmptyFindException();
        } else if (str.startsWith("find ")) {
            String keyword = str.substring(5);
            int i = 1;
            boolean isFound = false;
            //search if there are any matching tasks
            for (Task t : tasks) {
                if (t.getDescription().contains(keyword)) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                StringBuilder result = new StringBuilder("Here are the matching tasks in your list:");
                for (Task t : tasks) {
                    if (t.getDescription().contains(keyword)) {
                        result.append("\n").append(i).append(".").append(t);
                        i++;
                    }
                }
                return result.toString();
            } else {
                return "No matching task has been found";
            }
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Completes the task at the position in the list which the user specifies.
     *
     * @param str User input
     * @return A string indicating completion of the task.
     * @throws TaskCompletionException If the number is out of range of the list.
     */
    public String completeTask(String str) throws TaskCompletionException {
        if (!str.startsWith("done ")) {
            throw new TaskCompletionException(tasks.size());
        }
        String val = str.substring(5);
        if (isInteger(val)) {
            int i = Integer.parseInt(val);
            //Check that task is in range
            if (i > 0 && i <= tasks.size()) {
                if (tasks.get(i - 1).complete()) {
                    return "Task is already completed.\n" + tasks.get(i - 1);
                } else {
                    return "Task completed: " + tasks.get(i - 1);
                }
            } else {
                throw new TaskCompletionException(tasks.size());
            }
        } else {
            throw new TaskCompletionException(tasks.size());
        }
    }

    /**
     * Deletes the task at the position in the list which the user specifies.
     *
     * @param str User input
     * @return A string indicating deletion of the task.
     * @throws TaskDeletionException If the number is out of range of the list.
     */
    public String deleteTask(String str) throws TaskDeletionException {
        if (!str.startsWith("delete ")) {
            throw new TaskDeletionException(tasks.size());
        }
        String val = str.substring(7);
        if (isInteger(val)) {
            int i = Integer.parseInt(val);
            if (i > 0 && i <= tasks.size()) {
                int temp = tasks.size();
                String res = "Task has been removed.\n" + tasks.get(i - 1);
                delete(i);
                res += "\nYou now have " + tasks.size() + " tasks in the list";
                assert temp - tasks.size() == 1;
                return res;
            } else {
                throw new TaskDeletionException(tasks.size());
            }
        } else {
            throw new TaskDeletionException(tasks.size());
        }
    }

    /**
     * Deletes all items in the current list.
     *
     * @return A string indicating tasks have been cleared.
     */
    public String clear() {
        tasks.clear();
        return "Task List has been cleared.";
    }

    private String insert(Task task) {
        tasks.add(task);
        return "Task has been added:\n" + task + "\nYou now have " + tasks.size() + " tasks in the list";
    }

    private void delete(int index) {
        tasks.remove(index - 1);
    }

    //helper function to check if part of user input is an integer
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

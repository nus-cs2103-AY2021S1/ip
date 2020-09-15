package duke;

import java.util.ArrayList;
import java.util.function.Predicate;

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
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        int i = 1;
        for (Task t : tasks) {
            result.append("\n").append(i).append(".").append(t.toString());
            i++;
        }
        return result.toString();
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
            String type = arr[0];
            String content = arr[1];
            switch (type) {
            case "todo":
                return addToDo(content);
            case "deadline":
                return addDeadline(content);
            case "event":
                return addEvent(content);
            default:
                throw new InvalidCommandException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }

    private String addToDo(String content) {
        ToDo td = new ToDo(content);
        return insert(td);
    }

    private String addDeadline(String content) throws MissingTimeException, EmptyCommandException {
        if (!content.contains("/by")) {
            throw new MissingTimeException("deadline");
        }
        String[] arr = content.split("/by", 2);
        if (arr[0].isBlank()) {
            throw new EmptyCommandException("deadline");
        }
        if (arr[1].isBlank()) {
            throw new MissingTimeException("deadline");
        }
        return insert(new Deadline(arr[0], arr[1].trim()));
    }

    private String addEvent(String content) throws MissingTimeException, EmptyCommandException {
        if (!content.contains("/at")) {
            throw new MissingTimeException("event");
        }
        String[] arr = content.split("/at", 2);
        if (arr[0].isBlank()) {
            throw new EmptyCommandException("event");
        }
        if (arr[1].isBlank()) {
            throw new MissingTimeException("event");
        }
        return insert(new Event(arr[0], arr[1].trim()));
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
        }
        if (str.startsWith("find ")) {
            int startIndex = "find".length() + 1;
            String keyword = str.substring(startIndex);
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
                int i = 1;
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
     * May also complete all tasks of a type or containing a keyword.
     *
     * @param str User input
     * @return A string indicating completion of the task.
     * @throws TaskCompletionException If the number is out of range of the list.
     */
    public String completeTask(String str) throws TaskCompletionException {
        if (!str.startsWith("done ")) {
            throw new TaskCompletionException(tasks.size());
        }
        int startIndex = "done".length() + 1;
        String val = str.substring(startIndex);
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
            return completeMultiple(val.trim());
        }
    }

    private String completeMultiple(String keyword) {
        switch (keyword) {
        case "deadline":
            completeIf(task -> task instanceof Deadline);
            return "All deadlines have been completed.";
        case "event":
            completeIf(task -> task instanceof Event);
            return "All events have been completed.";
        case "todo":
            completeIf(task -> task instanceof ToDo);
            return "All todos have been completed.";
        default:
            completeIf(task -> task.getDescription().contains(keyword));
            return "All tasks related to " + keyword + " have been completed.";
        }
    }

    private void completeIf(Predicate<Task> predicate) {
        for (Task task : tasks) {
            if (predicate.test(task)) {
                task.complete();
            }
        }
    }

    /**
     * Deletes the task at the position in the list which the user specifies.
     * May also delete all tasks of a type or containing a keyword.
     *
     * @param str User input
     * @return A string indicating deletion of the task.
     * @throws TaskDeletionException If the number is out of range of the list.
     */
    public String deleteTask(String str) throws TaskDeletionException {
        if (!str.startsWith("delete ")) {
            throw new TaskDeletionException(tasks.size());
        }
        int startIndex = "delete".length() + 1;
        String val = str.substring(startIndex);
        if (!isInteger(val)) {
            return deleteMultiple(val.trim());
        } else {
            int i = Integer.parseInt(val);
            if (i > 0 && i <= tasks.size()) {
                int temp = tasks.size();
                String res = "Task has been removed.\n" + tasks.get(i - 1);
                tasks.remove(i - 1);
                res += "\nYou now have " + tasks.size() + " tasks in the list";
                assert temp - tasks.size() == 1;
                return res;
            } else {
                throw new TaskDeletionException(tasks.size());
            }
        }
    }

    private String deleteMultiple(String type) {
        switch (type) {
        case "deadline":
            tasks.removeIf(task -> task instanceof Deadline);
            break;
        case "event":
            tasks.removeIf(task -> task instanceof Event);
            break;
        case "todo":
            tasks.removeIf(task -> task instanceof ToDo);
            break;
        case "done":
            tasks.removeIf(Task::isDone);
            break;
        case "pending":
            tasks.removeIf(task -> !task.isDone());
            break;
        default:
            tasks.removeIf(task -> task.getDescription().contains(type));
        }
        return "All " + type + " tasks have been removed.\nYou now have " + tasks.size() + " tasks in the list";
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

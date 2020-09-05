package task;

import java.util.List;

import storage.Storage;
import task.tasks.Deadline;
import task.tasks.Event;
import task.tasks.Task;
import task.tasks.Todo;

/**
 * Smaller methods to assist with task handling in TaskList class.
 */
public class TaskHelper {
    /**
     * Adds a todo to task list.
     *
     * @param userCommand User Input.
     * @param tasklist    List of tasks.
     * @return
     */
    public static String handleTodo(String userCommand, TaskList tasklist) {
        String[] userCommandSplit = userCommand.split(" ", 2);
        assert userCommandSplit.length == 2 : "Something went wrong when splitting user input for adding todo.";
        String description = userCommandSplit[1];
        Task newTask = new Todo(description);
        List<Task> tasks = tasklist.getTasks();
        int initialSize = tasks.size();
        tasks.add(newTask);
        assert tasks.size() == initialSize + 1 : "Size of tasks list is incorrect after adding todo.";
        Storage.appendToFile(newTask.toString());
        return TaskDescription.addedTaskDescription(tasks, newTask);
    }

    /**
     * Adds a deadline to task list.
     *
     * @param userCommand User Input.
     * @param tasklist    List of tasks.
     * @return
     */
    public static String handleDeadline(String userCommand, TaskList tasklist) {
        String[] userCommandSplit = userCommand.split(" /by ");
        String description = userCommandSplit[0].split(" ", 2)[1];
        String by = userCommandSplit[1];

        // Add and report that the deadline is added
        Task newTask = new Deadline(description, by);
        List<Task> tasks = tasklist.getTasks();
        int initialSize = tasks.size();
        tasks.add(newTask);
        Storage.appendToFile(newTask.toString());
        return TaskDescription.addedTaskDescription(tasks, newTask);
    }

    /**
     * Adds an event to task list.
     *
     * @param userCommand User Input.
     * @param tasklist    List of tasks.
     * @return
     */
    public static String handleEvent(String userCommand, TaskList tasklist) {
        String[] userCommandSplit = userCommand.split(" /at ");
        String description = userCommandSplit[0].split(" ", 2)[1];
        String at = userCommandSplit[1];

        // Add and report that the event is added
        Task newTask = new Event(description, at);
        List<Task> tasks = tasklist.getTasks();
        int initialSize = tasks.size();
        tasks.add(newTask);
        Storage.appendToFile(newTask.toString());
        return TaskDescription.addedTaskDescription(tasks, newTask);
    }

    /**
     * Mark a task as completed.
     *
     * @param userCommandSplit Parsed / Splitted user input.
     * @param tasklist         List of tasks
     * @return
     */
    public static String handleCompletedTask(String[] userCommandSplit, TaskList tasklist) {
        assert userCommandSplit.length == 2
                : "Something went wrong when splitting user input for marking task completion.";
        // Take serial number e.g 1 "done 1"
        int serialNumber = Integer.parseInt(userCommandSplit[1]);
        int index = serialNumber - 1;

        // Mark as done and report that the task is done
        List<Task> tasks = tasklist.getTasks();
        Task doneTask = tasks.get(index);

        // If task has already been marked as done, don't mark it as done again
        if (doneTask.getStatusIcon().equals("Y")) {
            return TaskDescription.taskAlreadyMarkedDone();
        } else {
            assert doneTask.getStatusIcon().equals("N") : "Status icon should be [N] but something is wrong.";
            // Proceed to mark task as done/completed
            String currentText = doneTask.toString();
            doneTask.markAsDone();
            String amendedText = doneTask.toString();
            Storage.amendFile(currentText, amendedText);
            return TaskDescription.doneTaskDescription(doneTask);
        }
    }

    /**
     * Deletes a task.
     *
     * @param userCommandSplit Parsed / Splitted user input.
     * @param tasklist         List of tasks.
     * @return
     */
    public static String handleTaskDeletion(String[] userCommandSplit, TaskList tasklist) {
        assert userCommandSplit.length == 2 : "Something went wrong when splitting user input for task deletion.";
        // Take serial number e.g 1 "delete 1" and delete
        int serialNumber = Integer.parseInt(userCommandSplit[1]);
        int index = serialNumber - 1;

        // Mark as deleted and report that the task is deleted
        List<Task> tasks = tasklist.getTasks();
        Task deletedTask = tasks.get(index);
        int initialSize = tasks.size();
        tasks.remove(index);
        assert tasks.size() == initialSize - 1 : "Size of tasks list is incorrect after deleting task.";
        Storage.deleteFromFile(deletedTask.toString());
        return TaskDescription.deletedTaskDescription(tasks, deletedTask);
    }

    /**
     * Finds a task.
     *
     * @param userCommandSplit Parsed / Splitted user input.
     * @param tasklist         List of tasks.
     * @return
     */
    public static String handleTaskFinding(String[] userCommandSplit, TaskList tasklist) {
        assert userCommandSplit.length == 2 : "Something went wrong when splitting user input for task finding.";
        String keyword = userCommandSplit[1];
        // Make a copy of the existing tasks and remove a task if keyword is not found
        List<Task> tasks = tasklist.getTasks();
        List<Task> tasksCopy = tasks;
        tasksCopy.removeIf(task -> !task.getDescription().contains(keyword));
        return TaskDescription.searchedTaskDescription(tasksCopy);
    }
}

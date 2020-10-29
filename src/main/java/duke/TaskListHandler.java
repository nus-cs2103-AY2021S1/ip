package duke;

import java.util.ArrayList;
import java.util.Stack;

import duke.task.Task;

/**
 * Contains the task list and handles any operations regarding the task list.
 */
public class TaskListHandler {
    protected ArrayList<Task> tasks;
    protected Stack<ArrayList<Task>> undoTaskLists;
    protected Stack<String> inputsToUndo;

    /**
     * Stores tasks from save file or empty task list if save file unavailable.
     *
     * @param list Task list.
     */
    public TaskListHandler(ArrayList<Task> list) {
        this.tasks = list;
        this.inputsToUndo = new Stack<>();
        this.undoTaskLists = new Stack<>();
    }

    /**
     * Retrieves the task list.
     *
     * @return Task list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds specified task to the task list.
     *
     * @param task Task
     */
    public void addToList(Task task) {
        tasks.add(task);
    }

    /**
     * Removes all tasks from task list.
     *
     * @return An empty task list.
     */
    public ArrayList<Task> clearList() {
        this.tasks = new ArrayList<>();
        Ui.indent(1);
        System.out.println("The list of tasks has successfully been cleared.");
        return tasks;
    }

    /**
     * Prints the list and the number of items in the list.
     *
     * @throws DukeException if task list is currently empty.
     */
    public void printList() throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("\u2639 Whoops, the list of tasks is empty, pls add tasks first!");
        }
        int listPos = 1;
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++, listPos++) {
            Ui.indent(1);
            System.out.println(listPos + ". " + tasks.get(i));
        }
        assert listPos > 0 : "Error printing task at position " + listPos + " when printing task list";
        System.out.println();
        System.out.println("You have " + tasks.size() + " task(s) in the list");
    }

    /**
     * Finds tasks in the task list matching the keyword both entirely and partially.
     *
     * @param keyword The word to search for.
     * @return Task list containing the tasks containing the keyword.
     * @throws DukeException if keyword input by the user is empty.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) throws DukeException {
        boolean hasFoundTask = false;
        ArrayList<Task> foundTasks = new ArrayList<>();
        try {
            for (Task task : tasks) {
                if (task.getDescription().contains(keyword)) {
                    foundTasks.add(task);
                    hasFoundTask = true;
                }
            }
            if (!hasFoundTask) {
                // Unable to find any matching task
                return new ArrayList<>();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 Whoops, the keyword to search for cannot be empty!");
        }
        return foundTasks;
    }

    /**
     * Provides the functionality of undo-ing a previous command by reverting task list to the state
     * before the previous command was given.
     *
     *
     * @throws DukeException If there are no more previous modifications to the task list.
     */
    public void retrievePreviousTaskList() throws DukeException {
        if (undoTaskLists.empty()) {
            throw new DukeException("\u2639 Whoops, there are no more commands to undo!");
        }
        String previousCommand = inputsToUndo.pop();
        System.out.println("The previous command: " + '"' + previousCommand + '"' + " has been undone!");
        ArrayList<Task> previousTaskList = undoTaskLists.pop();
        tasks = previousTaskList;
    }

    /**
     * Provides the history for undo command by saving current task list.
     * Keeps a deep-copy of all the tasks in the current task list and the given user input for future retrieval.
     * This is only called prior to commands that modify the task list.
     *
     * @param command Current user input given.
     */
    public void saveCurrentTaskList(String command) {
        ArrayList<Task> savedTaskList = new ArrayList<>();
        for (Task t: tasks) {
            savedTaskList.add(t.deepCopy());
        }
        undoTaskLists.push(savedTaskList);
        inputsToUndo.push(command);
    }
}

package duke;

import java.util.ArrayList;
import java.util.List;

import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.task.ToDos;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Deals with manipulation on the list of tasks
 */
public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    /**
     * Removes a task from List of tasks based on the index.
     *
     * @param idx Index of task to be removed.
     * @return String which represents the task removed.
     */
    public String removeTask (int idx) {
        Task task = taskList.get(idx);
        taskList.remove(idx);
        return task.toString();
    }

    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    public int getSize() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Creates a task and adds it into the List of tasks.
     *
     * @param taskType Type of task.
     * @param description Description of task.
     * @param deadline Deadline of task.
     * @return a String representation of the task added.
     */
    public String addTask(Task.TaskType taskType, String description, String deadline) {
        Task task = null;
        switch (taskType) {
        case TODOS:
            task = new ToDos(description.trim(), deadline);
            taskList.add(task);
            break;
        case DEADLINE:
            task = new Deadline(description.trim(), deadline);
            taskList.add(task);
            break;
        case EVENT:
            task = new Event(description.trim(), deadline);
            taskList.add(task);
            break;
        }

        return "Got it, here yur task bij\n" + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String addTask(Task.TaskType taskType, String description) {
        Task task = null;
        switch (taskType) {
        case TODOS:
            task = new ToDos(description);
            taskList.add(task);
            break;
        case DEADLINE:
            task = new Deadline(description);
            taskList.add(task);
            break;
        case EVENT:
            task = new Event(description);
            taskList.add(task);
            break;
        }

        return "Got it, here yur task bij\n" + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Returns a list of all tasks at the moment.
     *
     * @return String which lists all tasks at the moment.
     */
    public String findTask(String str) throws InvalidIndexException {
        TaskList match = new TaskList();
        for (Task i: taskList) {
            String taskDescription = i.getDescription().toLowerCase();
            String toCompare = str.toLowerCase();
            if (taskDescription.contains(toCompare)) {
                match.addTask(i);
            }
        }
        if (match.getSize() == 0) {
            throw new InvalidIndexException("No such tasks leh...");
        }
        return "Got it, here yur task bij\n" + match.toString();
    }

    public String toString() {
        String string = "";
        if (getSize() >= 1) {
            string = "1." + taskList.get(0).toString();
            for (int num = 2; num <= getSize(); num++) {
                string = string + "\n" + num + "." + taskList.get(num - 1).toString();
            }
        } else {
            string = "empty";
        }
        return string;
    }
}

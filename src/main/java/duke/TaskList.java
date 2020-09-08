package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskList {
    protected List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a task list with a list of task input in string format.
     */
    public TaskList(List<String> tasks) throws DukeException {
        taskList = new ArrayList<>();
        generateTaskList(tasks);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a task into the task list.
     *
     * @param task The task to be added.
     * @return The task added to the list.
     */
    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    /**
     * Returns a string of task list in the correct format.
     *
     * @return String of formatted task list.
     */
    public String printList() {
        String output = "Here are the tasks in your list:\n";
        int count = taskList.size();
        if (count <= 0) {
            return "There is no task in your list currently.";
        } else {
            for (int i = 0; i < count; i++) {
                output += (i + 1) + ". " + taskList.get(i) + "\n";
            }
            return output.strip();
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param num Index of the task that the user wants to delete.
     * @return Deleted task.
     */
    public Task deleteTask(int num) {
        Task deletedTask = taskList.get(num - 1);
        taskList.remove(num - 1);
        return deletedTask;
    }

    /**
     * Marks a task as done.
     *
     * @param idx Index of the task that the user wants to mark as done.
     * @return The task marked as done.
     */
    public Task markTaskAsDone(int idx) {
        return taskList.get(idx - 1).markAsDone();
    }

    /**
     * Snoozes a task.
     *
     * @param idx Index of the task that the user wants to mark as done.
     * @return The task marked as done.
     * @throws DukeException If there is error in the input.
     */
    public Task snoozeTask(int idx) throws DukeException {
        Task task = taskList.get(idx - 1);
        if (task instanceof ToDo) {
            throw new DukeException("OOPS! A ToDo cannot be snoozed!");
        } else if (task instanceof Event) {
            Event event = (Event) task;
            event.snoozeEvent();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            deadline.snoozeDeadline();
        }
        return task;
    }

    /**
     * Finds the tasks that matches the keyword.
     *
     * @param keyword Keyword for finding matching tasks.
     * @return List of matching tasks.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = taskList
                .stream()
                .filter(Task -> Task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return matchingTasks;
    }

    /**
     * Prints the tasks found by search.
     *
     * @param tasks Tasks to be printed.
     * @return String to be printed.
     */
    public String printMatchingTasks(List<Task> tasks) {
        String output = "Here are the matching tasks in your list:\n";
        int count = tasks.size();
        if (count <= 0) {
            return "There is no matching task in your list. ";
        } else {
            for (int i = 0; i < count; i++) {
                output += (i + 1) + ". " + tasks.get(i) + "\n";
            }
            return output.strip();
        }
    }

    /**
     * Generates a list of tasks from strings read from file.
     *
     * @param tasks List of tasks in strings.
     * @throws DukeException If there is error in the input.
     */
    private void generateTaskList(List<String> tasks) throws DukeException {
        for (String task : tasks) {
            if (task.length() < 1) {
                continue;
            }
            String[] taskDetail = task.split(" \\| ");
            String type = taskDetail[0];
            boolean isDone = Integer.parseInt(taskDetail[1]) == 1;
            String description = taskDetail[2];
            switch (type) {
            case "T":
                taskList.add(new ToDo(isDone, description));
                break;
            case "E":
                taskList.add(new Event(isDone, description, taskDetail[3]));
                break;
            case "D":
                taskList.add(new Deadline(isDone, description, taskDetail[3]));
                break;
            default:
                throw new DukeException("OOPS! Invalid input from file! ");
            }
        }
    }

    /**
     * Returns the list of task to save to storage.
     *
     * @return List of task to save to storage.
     */
    public String getTaskListForSave() {
        String allTasks = "";
        for (Task task : taskList) {
            allTasks += task.getTaskDetailsForSave() + "\n";
        }
        return allTasks;
    }



}

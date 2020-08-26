package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<String> tasks) throws DukeException {
        taskList = new ArrayList<>();
        generateTaskList(tasks);
    }

    /**
     * Returns the number of tasks in the task list.
     * @return Number of tasks.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a task into the task list.
     * @param task The task to be added.
     * @return The task added to the list.
     */
    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    /**
     * Returns a string of task list in the correct format.
     * @return String of formatted task list.
     */
    public String printList() {
        String output = "Here are the tasks in your list:\n";
        int count = taskList.size();
        if (count <= 0) {
            return "There is no task in your list currently. ";
        } else {
            for (int i = 0; i < count; i++) {
                output += (i + 1) + ". " + taskList.get(i) + "\n";
            }
            return output.strip();
        }
    }

    /**
     * Deletes a task from the list.
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
     * @param idx Index of the task that the user wants to mark as done.
     * @return The task marked as done.
     */
    public Task markTaskAsDone(int idx) {
        return taskList.get(idx - 1).markAsDone();
    }

    /**
     * Finds the tasks that matches the keyword. 
     * @param keyword Keyword for finding matching tasks. 
     * @return List of matching tasks. 
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

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
            }
        }
    }

    public String getTaskListForSave() {
        String allTasks = "";
        for (Task task : taskList) {
            allTasks += task.getTaskDetailsForSave() + "\n";
        }
        return allTasks;
    }



}

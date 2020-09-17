import java.util.List;

/**
 * TaskList contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns search results that contain the search from commands
     * @param commands
     * @return string of search results
     */
    public String getSearchResult(String[] commands) throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();
        if (commands.length < 2) {
            throw new DukeException("Please give a search term!");
        }
        assert commands.length == 2 : "command array length should be 2";
        String searchTerm = commands[1];
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.name.contains(searchTerm)) {
                stringBuilder.append((i + 1) + "." + currentTask.toString() + '\n');
            }
        }
        if (stringBuilder.length() == 0) {
            return "";
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * Returns the number of the tasks in the TaskList
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list
     * @param task
     * @return the task that has been added to the task list
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Marks a task in the TaskList as done
     * @param commands
     * @return the task that has been marked as done
     * @throws DukeException
     */
    public Task markTaskDone(String[] commands) throws DukeException {
        int index = Parser.getTaskIndex(commands, tasks);
        Task task = tasks.get(index);
        task.markDone();
        assert task.isCompleted == true : "Task should be completed";
        return task;
    }

    /**
     * Deletes a task that is in the index specified by the command
     * @param commands
     * @return the deleted task
     * @throws DukeException
     */
    public Task deleteTask(String[] commands) throws DukeException {
        int index = Parser.getTaskIndex(commands, tasks);
        Task task = tasks.remove(index);
        return task;
    }

    /**
     * Gets the tasks
     * @return a List of Tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "No tasks!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}

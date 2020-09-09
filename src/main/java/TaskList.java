import java.util.List;

/**
 * TaskList contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> taskList;

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of the tasks in the TaskList
     * @return the size of the task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list
     * @param task
     * @return the task that has been added to the task list
     */
    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    /**
     * Marks a task in the TaskList as done
     * @param commands
     * @return the task that has been marked as done
     * @throws DukeException
     */
    public Task markTaskDone(String[] commands) throws DukeException {
        int index = Parser.getTaskIndex(commands, taskList);
        Task task = taskList.get(index);
        task.markDone();
        return task;
    }

    /**
     * Deletes a task that is in the index specified by the command
     * @param commands
     * @return the deleted task
     * @throws DukeException
     */
    public Task deleteTask(String[] commands) throws DukeException {
        int index = Parser.getTaskIndex(commands, taskList);
        Task task = taskList.remove(index);
        return task;
    }

    /**
     * Gets the tasks
     * @return a List of Tasks
     */
    public List<Task> getTasks() {
        return taskList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            stringBuilder.append((i+1) + "."+ taskList.get(i) +"\n");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

}

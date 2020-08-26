import java.util.List;

public class TaskList {
    private List<Task> storedTasks;

    public TaskList(List<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }

    public List<Task> getStoredTasks() {
        return storedTasks;
    }

    public int getCount() {
        return storedTasks.size();
    }

    /**
     * Adds input task into stored_task.
     *
     * @param newTask Input task from user to be stored.
     **/
    public void addTask(Task newTask) {
        storedTasks.add(newTask);
    }

    /**
     * Marks task as done.
     *
     * @param taskNumber Task number of task to be marked as done.
     **/
    public Task markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > storedTasks.size()) {
            throw new DukeException("Wrong task number!");
        } else {
            Task task = storedTasks.get(taskNumber - 1);
            if (task.isDone()) {
                throw new DukeException("This task is already done: " + task.getDescription());
            } else {
                task.markAsDone();
                return task;
            }
        }
    }

    /**
     * Deletes input task from stored_task.
     *
     * @param taskNumber Task number of task to be deleted.
     **/
    public Task deleteTask(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > storedTasks.size()) {
            throw new DukeException("Wrong task number!");
        } else {
            Task taskToDelete = storedTasks.get(taskNumber - 1);
            storedTasks.remove(taskToDelete);
            return taskToDelete;
        }
    }

}

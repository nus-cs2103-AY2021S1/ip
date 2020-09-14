package luke;

import java.util.ArrayList;
import java.util.List;

import luke.exception.LukeEmptyCommandException;
import luke.exception.LukeException;
import luke.exception.LukeNoResultException;
import luke.task.Task;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves the task that corresponds to the given index.
     *
     * @param i index of the task
     * @return The task that corresponds to the given index
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Retrieves the number of tasks inside the current tasklist.
     *
     * @return The size of tasks inside the current tasklist
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Removes the task with the specified index
     * within the current list of tasks.
     *
     * @param taskNumber the index of the task to be deleted
     */
    public Task deleteTask(int taskNumber) {
        int idx = taskNumber - 1;
        Task deletedTask = this.tasks.get(idx);
        this.tasks.remove(idx);
        return deletedTask;
    }

    /**
     * Marks the task with the specified index as done
     * within the current list of tasks.
     *
     * @param taskNumber the index of the task to be deleted
     */
    public Task doTask(int taskNumber) {
        int idx = taskNumber - 1;
        Task doneTask = this.tasks.get(idx);
        doneTask.markAsDone();
        return doneTask;
    }

    /**
     * Finds the task containing the given keyword
     * within the current list of tasks.
     *
     * @param input the given keyword used to find a task
     */
    public List<Task> findTask(String input) throws LukeNoResultException {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task current = this.tasks.get(i);
            if (current.getDescription().contains(input)) {
                result.add(current);
            }
        }
        if (result.size() <= 0) {
            throw new LukeNoResultException(input);
        }
        return result;
    }

}

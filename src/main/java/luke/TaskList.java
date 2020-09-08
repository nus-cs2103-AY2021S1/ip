package luke;

import luke.task.Task;

import java.util.ArrayList;
import java.util.List;

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

    public Task deleteTask(int taskNumber) {
        int idx = taskNumber - 1;
        Task deletedTask = this.tasks.get(idx);
        this.tasks.remove(idx);
        return deletedTask;
    }

    public Task doTask(int taskNumber) {
        int idx = taskNumber - 1;
        Task doneTask = this.tasks.get(idx);
        doneTask.markAsDone();
        return doneTask;
    }

    public void findTask(String input) {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task current = this.tasks.get(i);
            if (current.getDescription().contains(input)) {
                result.add(current);
            }
        }
        if (result.size() < 1) {
            //return error message
        } else {
            String todoSummary = "Here are the tasks in your list.";
            for (int i = 0; i < result.size(); i++) {
                Task current = result.get(i);
                todoSummary += String.format("%d.%s\n", i + 1, current);
            }
            System.out.printf(todoSummary);
        }
    }

}

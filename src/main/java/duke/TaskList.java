package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList(List<Task> planner) {
        this.tasks = planner;
    }

    public void addToPlanner(Task task) {
        tasks.add(task);
    }

    public Integer getSize() {
        return tasks.size();
    }

    public List<Task> getPlanner() {
        return this.tasks;
    }

    public Task markAsDone(Integer index) {
        this.tasks.get(index).done();
        return this.tasks.get(index);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public Task getTask(Integer index) {
        return this.tasks.get(index);
    }

    public List<Task> findTasks(String keyword) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}

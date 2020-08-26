package duke;

import duke.task.Task;

import java.util.List;

public class TaskList {
    protected List<Task> planner;

    public TaskList(List<Task> planner) {
        this.planner = planner;
    }

    public void addToPlanner(Task task) {
        planner.add(task);
    }

    public Integer getSize() {
        return planner.size();
    }

    public List<Task> getPlanner() {
        return this.planner;
    }

    public Task markAsDone(Integer index) {
        this.planner.get(index).done();
        return this.planner.get(index);
    }

    public Task deleteTask(int index) {
        return this.planner.remove(index);
    }

    public Task getTask(Integer index) {
        return this.planner.get(index);
    }



}

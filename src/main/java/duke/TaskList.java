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

    public void markAsDone(Integer index) {
        this.planner.get(index).done();
    }

    public void deleteTask(int index) {
        this.planner.remove(index);
    }

    public Task getTask(Integer index) {
        return this.planner.get(index);
    }



}

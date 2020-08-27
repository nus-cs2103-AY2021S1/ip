package duke.task;

import duke.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getList() {
        return tasks;
    }

    public void addList(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int i) throws DukeException {
        try {
            tasks.remove(i);
        } catch (Exception e) {
            throw new DukeException("You don't have such task in your list...");
        }
    }

    public int getListSize() {
        return tasks.size();
    }
}

package duke.task;

import duke.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }
    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }

    public void addList(Task task) {
        list.add(task);
    }

    public void deleteTask(int i) throws DukeException {
        try {
            list.remove(i);
        } catch (Exception e) {
            throw new DukeException("You don't have such task in your list...");
        }
    }

    public int getListSize() {
        return list.size();
    }
}

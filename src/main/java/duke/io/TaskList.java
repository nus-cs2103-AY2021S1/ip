package duke.io;

import duke.DukeException;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArrayList) throws DukeException {
        this.taskArrayList = taskArrayList;
    }

    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    public int sizeOfList() {
        return this.taskArrayList.size();
    }

    public Task retrieveTask(int index) {
        return this.taskArrayList.get(index);
    }

    public void deleteTask(int index) {
        this.taskArrayList.remove(index);
    }
}

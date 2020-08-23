package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

public class TaskList {

    private ArrayList<Task> taskStore;

    public TaskList() {
        this.taskStore = new ArrayList<>();
    }

    public TaskList(ArrayList<String> data) throws DukeException {
        this.taskStore = new ArrayList<>();
        for (String s : data) {
            Task t = Parser.parseTaskData(s);
            taskStore.add(t);
        }
    }

    public void add(Task newTask) {
        taskStore.add(newTask);
    }

    public Task markTaskAsDone(int i) throws DukeException {
        Task doneTask = taskStore.get(i - 1).markAsDone();
        taskStore.set(i - 1, doneTask);
        return doneTask;
    }

    public Task delete(int i) {
        Task deletedTask = taskStore.remove(i - 1);
        return deletedTask;
    }

    public ArrayList<String> getListRepr() {
        ArrayList<String> res = new ArrayList<>();
        for (Task t : taskStore) {
            res.add(t.toString());
        }
        return res;
    }

    public String getListStatus() {
        int storeSize = taskStore.size();
        return "There " + (storeSize > 1 ? "are " : "is ") + "now " + storeSize + " " +
                (storeSize > 1 ? "tasks " : "task ") + "in your list!";
    }

    public ArrayList<String> getData() {
        ArrayList<String> res = new ArrayList<>();
        for (Task t : taskStore) {
            res.add(t.getData());
        }
        return res;
    }
}

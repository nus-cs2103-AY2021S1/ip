package duckie.task;

import java.util.ArrayList;

import duckie.exception.*;
import duckie.Ui;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public ArrayList<Task> getTaskList() {
        return lst;
    }

    public int getIndex(Task task) {
        return lst.indexOf(task);
    }

    public void addTask(Task t1) {
        lst.add(t1);
    }

    public void deleteTask(int ind) {
        lst.remove(ind);
    }

    public void deleteAllTasks() {
        lst.clear();
    }

    public void markTaskDone(int ind) {
        Task t1 = lst.get(ind - 1);
        t1.checked();
    }

    public void displayList() throws DuckieException {
        if (lst.size() == 0) {
            throw new DuckieNoListException();
        }

        Ui.displayListReply(lst);
    }
}

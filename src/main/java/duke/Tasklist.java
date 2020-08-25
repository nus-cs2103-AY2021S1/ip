package duke;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Tasklist {

    private Storage storage;
    List<Task> taskList;

    public Tasklist(Storage storage) {
       this.storage = storage;
    }

    public void clearList() {
        taskList = new ArrayList<>();
    }

    public void loadList() throws IOException {
        this.taskList = storage.load();
    }

    public void updateStorage() throws IOException {
        this.storage.writeData(this.taskList);
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public void makeTaskDone(int index) {
        this.taskList.get(index).makeDone();
    }

    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int getTaskSize() {
        return this.taskList.size();
    }
}


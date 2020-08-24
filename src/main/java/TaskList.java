package main.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;
    private Storage storage;

    public TaskList(Storage storage) {
        this.taskList = storage.load();
        this.storage = storage;
    }

    public int taskCount() {
        return this.taskList.size();
    }

    public void addNewTask(Task newTask) {
        this.taskList.add(newTask);
        this.storage.save(taskList);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return taskList.get(index);
    }

    public void markDone(int index) {
        this.taskList.get(index).done();
        this.storage.save(taskList);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
        this.storage.save(taskList);
    }

    public List<Task> find(String keyWord) {
        List<Task> resultList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getContent().contains(keyWord)) {
                resultList.add(taskList.get(i));
            }
        }
        return resultList;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i <= taskList.size(); i++) {
            result += (i + ". " + taskList.get(i - 1).toString() + "\n");
        }
        return result;
    }
}

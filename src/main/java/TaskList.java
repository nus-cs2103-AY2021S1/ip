package main.java;

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

    public Task getTask(int index) throws IndexOutOfBoundsException {
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

    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i <= taskList.size(); i++) {
            result += (i + ". " + taskList.get(i - 1).toString() + "\n");
        }
        return result;
    }
}

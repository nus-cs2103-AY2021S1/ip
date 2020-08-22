package main.java;

import java.util.LinkedList;
import java.util.List;
import Exception.DoneOutOfBoundException;
import Exception.DeleteOutOfBoundException;
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new LinkedList<>();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void printList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println( (i+1) + ". " + this.taskList.get(i));
        }
    }

    public void changeIsDone(int index) throws DoneOutOfBoundException {
        try {
            this.taskList.get(index - 1).changeIsDone();
            System.out.println("This task has been mark as done.");
            System.out.println(this.taskList.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new DoneOutOfBoundException();
        }
    }

    public void delete(int index) throws DeleteOutOfBoundException {
        try {
            Task cur = this.taskList.get(index - 1);
            this.taskList.remove(index - 1);
            System.out.println("This task has been deleted.");
            System.out.println(cur);
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteOutOfBoundException();
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Added new task " + task);
    }

    public int size() {
        return this.taskList.size();
    }

    public void removeAll() {
        this.taskList.clear();
    }
}

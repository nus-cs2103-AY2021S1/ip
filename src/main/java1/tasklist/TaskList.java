package java1.tasklist;

import java.util.ArrayList;

import java1.storage.Storage;

public class TaskList {
    private ArrayList<Task> todoList;

    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> allTask) {
        this.todoList = allTask;
    }

    public void addTask(Task task) {
        String path = "/Users/joshua/Desktop/ip/data/duke.txt";

        Storage.saveTask(path, this.todoList, task);
        this.todoList.add(task);
        System.out.println("     Got it. I've added this task:" + "\n" + "       " + task.toString());
        System.out.println("     Now you have " + this.todoList.size() + " tasks in the list.");
    }

    public Task get(int index) {
        return this.todoList.get(index);
    }

    public int getLength() {
        return this.todoList.size();
    }

    public void removeTask(int index) {
        String path = "/Users/joshua/Desktop/ip/data/duke.txt";

        Task removedTask = this.todoList.get(index);
        this.todoList.remove(index);
        System.out.println("     Noted. I've removed this task:" + "\n" + "      " + removedTask.toString());
        System.out.println("     Now you have " + this.todoList.size() + " tasks in the list.");

        Storage.removeTask(path, this.todoList);

    }

    public void listTasks() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < this.todoList.size(); i++) {
            int listNumber = i + 1;
            Task currentTask = todoList.get(i);

            System.out.println("     " + listNumber + "." + currentTask.toString());
        }
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i  = 0; i < this.todoList.size(); i++) {
            if(this.todoList.get(i).getDescription().contains(keyword)) {
                temp.add(this.todoList.get(i));
            }
        }
        return temp;
    }

    public void updateDone() {
        String path = "/Users/joshua/Desktop/ip/data/duke.txt";
        Storage.updateTask(path, this.todoList);
    }
}

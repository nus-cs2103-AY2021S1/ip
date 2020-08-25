package main.java.manager;
import main.java.tasks.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + "." + this.taskList.get(i).toString());
        }
    }

    public void addTask(Task task) {
        if (task != null) {
            this.taskList.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            printNumberOfTasks();
        }
    }

    public void markTaskAsDone(int index) {
        this.taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.taskList.get(index).toString());
    }

    public void deleteTask(int index) {
        Task removedTask = this.taskList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        printNumberOfTasks();
    }

    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    public void printNumberOfTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public List<Task> getList() {
        return this.taskList;
    }

    public void setList(List<Task> taskList) {
        this.taskList = taskList;
    }

}

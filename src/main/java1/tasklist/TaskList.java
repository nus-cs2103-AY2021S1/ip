package java1.tasklist;

import java.util.ArrayList;

import java1.storage.Storage;

/**
 * The TaskList class encapsulates information regarding the list of tasks in Duke.
 * This class can (i) add tasks to the list, (ii) remove tasks from the list, (iii)
 * list all tasks in Duke, (iv) update the status of a task as done.
 */
public class TaskList {
    private ArrayList<Task> todoList;

    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> allTask) {
        this.todoList = allTask;
    }

    /**
     * Adds a task to the list and store it in storage.
     * @param task The task to be added.
     */
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

    /**
     * Removes a task from the list and update storage.
     * @param index The task number to be removed.
     */
    public void removeTask(int index) {
        String path = "/Users/joshua/Desktop/ip/data/duke.txt";

        Task removedTask = this.todoList.get(index);
        this.todoList.remove(index);
        System.out.println("     Noted. I've removed this task:" + "\n" + "      " + removedTask.toString());
        System.out.println("     Now you have " + this.todoList.size() + " tasks in the list.");

        Storage.rewriteList(path, this.todoList);

    }

    /**
     * Lists all the tasks in the TaskList.
     */
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

    /**
     * Updates a task as done and update storage.
     */
    public void updateDone() {
        String path = "/Users/joshua/Desktop/ip/data/duke.txt";
        Storage.rewriteList(path, this.todoList);

    }
}

package data;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void list() {
        int counter = 1;
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println(counter + ". " + tasks.get(i));
                counter++;
            } else {
                break;
            }
        }
    }

    public void delete(int num) {

        try {
            Task task = tasks.get(num - 1);
            tasks.remove(num - 1);
            System.out.println("Noted. I've removed the task: \n"
                    + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There isn't such a task!");
        }
    }

    public void doTask(int num) {
        Task task = tasks.get(num - 1);
        task.doTask();
        System.out.println("Noted. I've done the task: \n"
                + task);
    }

    public void add(Task task) {

        tasks.add(task);
        System.out.println("Got it. I've added this task: \n"
                + task
                + "\nNow you have " + tasks.size() + " task(s) in the list.");
    }

    public Task get(int index) {
        if (index < size() && index >= 0) {
            return tasks.get(index);
        } else {
            return null;
        }

    }

    public int size() {
        return tasks.size();
    }


}

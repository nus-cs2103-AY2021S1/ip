package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (list.size() > 1) {
            System.out.println("Now you have " + list.size() + " tasks in your list");
        } else {
            System.out.println("Now you have " + list.size() + " task in your list");
        }
    }

    public void markAsDone(int item) {
        list.get(item).done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(item));
    }

    public void deleteTask(int number) {
        Task deletedTask = list.get(number);
        list.remove(number);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("You now have " + list.size() + " tasks in your list");
    }
}
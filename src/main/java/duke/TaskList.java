package duke;

import duke.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        list.add(task);
        String strToPrint = "Got it. I've added this task:" + "\n" + task.toString() + "\n" + "Now you have " + list.size() + " task in the list.";
        System.out.println(strToPrint);
    }

    public void remove(int num) {
        Task taskToDelete = list.get(num);
        this.list.remove(num);
        System.out.println("Noted. I've removed this task:" + "\n" + taskToDelete.toString() + "\n" + "Now you have " + list.size() +
                " tasks in the list.");
    }

    public ArrayList<Task> getList() {
        return list;
    }
}

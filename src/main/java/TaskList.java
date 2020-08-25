import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> lst) {
        ArrayList<Task> temp = new ArrayList<>();
        for (String s : lst) {
            String[] st = s.split(" \\| ");
            if (st[0].equals("T")) {
                ToDo t = new ToDo(st[2]);
                if (st[1].equals("1")) {
                    t.markAsDone();
                }
                temp.add(t);
            } else if (st[0].equals("D")) {
                Deadline d = new Deadline(st[2], st[3]);
                if (st[1].equals("1")) {
                    d.markAsDone();
                }
                temp.add(d);
            } else {
                Event e = new Event(st[2], st[3]);
                if (st[1].equals("1")) {
                    e.markAsDone();
                }
                temp.add(e);
            }
        }
        this.tasks = temp;
    }

    public int getLength() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void showList(Ui ui) {
        ArrayList<String> lst = new ArrayList<>();
        for (Task t : tasks) {
            lst.add(t.toString());
        }
        ui.showList(lst);
    }

    public void markDone(int i, Ui ui) {
        tasks.get(i).markAsDone();
        ui.showDone(tasks.get(i));
    }

    public void deleteTask(int i, Ui ui) {
        Task t = tasks.remove(i);
        ui.showDelete(t, tasks.size());
    }

    public void addTask(Task t, Ui ui) {
        tasks.add(t);
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }
}

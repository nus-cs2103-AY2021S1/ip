package duke.task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> al;

    protected TaskList() {
        al = new ArrayList<>();
    }

    protected TaskList(ArrayList<Task> al) {
        this.al = al;
        if(al.size() > 0) {
            System.out.println("Here is your list of saved tasks:\n" + this.toString() +"\n");
        }
    }

    protected int length() {
        return al.size();
    }

    protected Task delete(int index) {
        return al.remove(index);
    }

    public void add(Task t) {
        al.add(t);
    }

    public Task get(int index) {
        return al.get(index);
    }

    @Override
    public String toString() {
        String s = "1. " + al.get(0);
        for(int i = 1; i < al.size(); i ++) {
            s = s + "\n" + (i + 1) + ". " + al.get(i);
        }
        return s;
    }
}

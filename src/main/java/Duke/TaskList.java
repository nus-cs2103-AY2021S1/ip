package Duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> dukeList = new ArrayList<>();

    public TaskList(ArrayList<Task> list) {
        this.dukeList.addAll(list);
    }

    public void add(Task task) {
        // Add to List
        dukeList.add(task);
    }

    public Task remove(int order) {
        return dukeList.remove(order - 1);
    }

    public Task markDone(int order) {
        Task task = dukeList.get(order - 1);
        task.setDone();
        return task;
    }

    public String listToString() {
        String output = "";
        for (int i = 0; i < dukeList.size(); i++) {
            output = output + (i + 1) + ". " + dukeList.get(i).toString() + "\n";
        }

        return output;
    }

    public int getSize() {
        return dukeList.size();
    }
}
package Duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> lines;
    private int numberOfItems;

    public TaskList(ArrayList<String> lines) {
        this.lines = lines;
        this.numberOfItems = lines.size();
    }

    public void addTask(String task) {
        if (numberOfItems < 100) {
            lines.add(task);
            numberOfItems += 1;
        }
    }

    public void removeTask(int position) {
        lines.remove(position);
        numberOfItems -= 1;
    }

    public void updateTask(String task, int position) {
        lines.set(position, task);
    }

    public ArrayList<String> getList() {
        return lines;
    }

    public String getTask(int position) {
        return lines.get(position);
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * Finds all tasks that contains the keyword
     *
     * @param keyword The keyword that we want our resulting tasks to have
     * @return An arrayList containing the tasks that contains the keyword
     */
    public ArrayList<String> find(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String task = lines.get(i);
            if (task.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}

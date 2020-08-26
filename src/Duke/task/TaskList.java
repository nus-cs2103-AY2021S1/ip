package Duke.task;

import java.util.ArrayList;

public class TaskList<T> {
    private ArrayList<T> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addMemory(T event) {
        this.taskList.add(event);
    }

    public ArrayList<T> getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        String results = "";
        int size = this.taskList.size();
        if (size != 0) {
            for (int i = 1; i < size; i++) {
                results += "    " + i + ". " + taskList.get(i - 1) + "\n";
            }
            results += "    " + size + ". " + taskList.get(size - 1);
        }
        return results;
    }
}

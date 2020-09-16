package dude.util;

import java.util.ArrayList;
import java.util.List;

import dude.task.Task;

/**
 * The class that handles all of the tasks while the program is running.
 */
public class TaskList {
    private static final int FALSE = -1;
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getCount() {
        return tasks.size();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int detectDuplicates(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (task.equals(tasks.get(i))) {
                return i + 1;
            }
        }
        return FALSE;
    }

    public ArrayList<Task> search(String keywords) {
        ArrayList<Task> matchList = new ArrayList<>();
        String[] keywordArray = keywords.split(" ");
        boolean allMatch;
        String curr;
        for (Task task : tasks) {
            allMatch = true;
            for (int i = 0; i < keywordArray.length; i++) {
                curr = keywordArray[i];
                if (!task.getDescription().toLowerCase().contains(curr.toLowerCase())) {
                    allMatch = false;
                }
            }
            if (allMatch) {
                matchList.add(task);
            }
        }
        return matchList;
    }
}

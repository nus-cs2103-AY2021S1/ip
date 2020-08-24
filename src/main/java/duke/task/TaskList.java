package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);

    }
    public Task delete(int index) {
        return this.taskList.remove(index);
    }

    public void showAllItems() {
        ArrayList<Task> currList = this.taskList;
        currList.forEach(item ->
                System.out.println((currList.indexOf(item) + 1) + "." + item));
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Prints out all the Tasks in the TaskList that contains the keyword specified by the user.
     *
     * @param keyword the key to filter the Tasks in the TaskList by.
     */
    public void showSpecifiedItems(String keyword) {
        TaskList filteredTasks = new TaskList();

        ArrayList<Task> currList = this.taskList;
        currList.forEach(item -> {
            if (item.description.contains(keyword)) {
                filteredTasks.add(item);
            }
        });

        filteredTasks.showAllItems();
    }
}

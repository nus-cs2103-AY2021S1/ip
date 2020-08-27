package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * constructor for tasklist if no list was retrieved from storage.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * overloaded constructor for tasklist if list was retrieved from storage.
     * @param newList list generated from tasks in storage.
     */
    public TaskList(ArrayList<Task> newList) {
        this.list = newList;
    }

    public void addItem(Task i) {
        list.add(i);
    }

    public void deleteItem(int itemIndex) {
        list.remove(itemIndex);
    }

    public Task getItem(int index) {
        return list.get(index);
    }

    public int getTasksLeft() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void setList(ArrayList<Task> newList) {
        this.list.addAll(newList);
    }

}
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList () {
        this.list = new ArrayList<>();
    }

    // overloaded constructor
    public TaskList (ArrayList<Task> newList) {
        this.list = newList;
    }

    public void addItem(Task i) {
        list.add(i);
    }

    public void deleteItem(int itemIndex) {
        Task t = list.get(itemIndex);
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
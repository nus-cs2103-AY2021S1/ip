import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> list = new ArrayList<>();

    public void addItem(Task i) {
        list.add(i);
        System.out.println("  added: " + i);
    }

    public Task getItem(int index) {
        return list.get(index);
    }

    public void print() {
        int counter = 1;
        for (Task i : list) {
            System.out.println("  " + counter + ". " + i);
            counter++;
        }
    }
}
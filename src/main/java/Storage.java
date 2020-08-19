import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> list = new ArrayList<>();

    public void addItem(Task i) {
        list.add(i);
        String indents = "  ";
        System.out.println(indents + "Got it, the following task has been added:\n" + indents + indents + i + this.displayTasksLeft());
    }

    public void deleteItem(int itemIndex) {
        Task t = list.get(itemIndex);
        list.remove(itemIndex);
        String indents = " ";
        System.out.println(indents + "Noted. I have removed this task:\n" + indents + indents + t + this.displayTasksLeft());
    }

    public Task getItem(int index) {
        return list.get(index);
    }

    public String displayTasksLeft() {
        String indents = "  ";
        return "\n" + indents + "Now you have " + this.list.size() + " tasks in the list.";
    }

    public void print() {
        int counter = 1;
        for (Task i : list) {
            System.out.println("  " + counter + "." + i);
            counter++;
        }
    }
}
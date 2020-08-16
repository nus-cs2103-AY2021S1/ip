import java.util.ArrayList;
import java.util.Iterator;

public class TaskPrinter implements Printable<Task> {
    public void display(String message) {
        System.out.println(message);
    }

    public void list(ArrayList<Task> ls) {
        System.out.println("____________________________________________________________");
        System.out.println("|###################### Your Tasks: #######################|");
        System.out.println("------------------------------------------------------------");

        if (ls.size() == 0 ) {
            System.out.println("No tasks.");
            return;
        }

        Iterator<Task> iter = ls.iterator();
        int index = 1;
        while (iter.hasNext()) {
            System.out.println(index + ". " + iter.next());
            index++;
        }
    }
}

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class TaskList implements Iterable<Task> {

    public ArrayList<Task> listOfItems;
    public String line = "____________________________________________________________";

    TaskList() {
        this.listOfItems = new ArrayList<>();
    }

    public String add(Task item) {
        this.listOfItems.add(item);

        return String.format("\nGot it. I've added this task:\n  %s\nNow you have %d tasks in your list.\n",
                item.toString(),
                this.listOfItems.size());
    }

    public String markCompleted(int index) throws DukeException {
        try {
            Task item = listOfItems.get(index);
            item.markAsDone();

            return String.format("\nNice! I've marked this task as done:\n  %s\n", item.toString());

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("List does not contain the number specified");
        }
    }

    public String deleteTask(int index) throws DukeException {
        try {
            Task item = listOfItems.remove(index);

            return String.format("\nNoted. I've removed this task:\n  %s\nNow you have %d tasks in your list.\n",
                    item.toString(),
                    this.listOfItems.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("List does not contain the number specified");
        }
    }

    public String printOutList() {
        String list = "\nHere are the tasks in your list:\n";
        for (int i = 0; i < this.listOfItems.size(); i++) {
            list += String.format("%d.%s\n", i + 1, this.listOfItems.get(i).toString());
        }
        return list;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.listOfItems.iterator();
    }

}

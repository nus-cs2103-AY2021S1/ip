import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;
    private static final String starline = "**************************************************************************";

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void list() {
        System.out.println(starline + "\nHere are the tasks in your list:");
        for (int i=0; i < this.list.size(); i++) {
            printTask(i);
        }
        System.out.println(starline);
    }

    public int getLength() {
        return this.list.size();
    }

    public void printTask(int listIndex) {
        String task = String.format("%d.%s", listIndex+1, this.list.get(listIndex));
        System.out.println(task);
    }

    public void echo(String input) {
        System.out.println("added: " + input);
    }

    public void add(String input) {
        Task newTask = new Task(input);
        this.list.add(newTask);
        echo(input);
    }

    public void markTaskAsDone(int listIndex) throws IndexOutOfBoundsException {
        Task task = this.list.get(listIndex);
        task.markDone();
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }
}

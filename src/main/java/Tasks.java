import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> list;

    Tasks() {
        this.list = new ArrayList<>();
    }

    void addTask(String task) {
        this.list.add(new Task(task));
        echo(task);
    }

    void listTasks() {
        System.out.println("\t___________________________________________________________________________");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
        }
        System.out.println("\t___________________________________________________________________________\n");
    }

    void echo(String s) {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\tadded: " + s);
        System.out.println("\t___________________________________________________________________________\n");
    }

    void markDone(int index) {
        System.out.println("\tNice! I've marked this task as done:");
        this.list.get(index - 1).markAsDone();
        System.out.println("\t  " + this.list.get(index - 1) + "\n");
    }
}

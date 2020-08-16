import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<String> list;

    Tasks() {
        this.list = new ArrayList<>();
    }

    void addTask(String task) {
        this.list.add(task);
        echo(task);
    }

    void printTasks() {
        System.out.println("\t___________________________________________________________________________");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
        System.out.println("\t___________________________________________________________________________\n");
    }

    void echo(String s) {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t" + "\tadded: " + s);
        System.out.println("\t___________________________________________________________________________\n");
    }
}

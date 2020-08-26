import java.util.ArrayList;

public class Ui {

    public void Greet() {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void print(String string) {
        showLine();
        System.out.println(string);
        showLine();
    }

    public static void printList(ArrayList<Task> TaskList) {
        showLine();
        for (Task task : TaskList) {
            System.out.println("     " + (TaskList.indexOf(task) + 1) + "." + task);
        }
        showLine();
    }

    public void Bye() {
        showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        showLine();
    }

    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }
}

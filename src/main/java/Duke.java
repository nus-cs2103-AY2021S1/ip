import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<String> tasks = new ArrayList<String>();

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    void addTask(String task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }

    void listTasks() {
        for (int i = 1; i <= tasks.size(); i ++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                duke.exit();
                break;
            } else if (command.equals("list")) {
                duke.listTasks();
            } else {
                duke.addTask(command);
            }
        }
        sc.close();
    }
}

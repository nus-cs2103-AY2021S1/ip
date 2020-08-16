import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> tasks = new ArrayList<>();

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    void addTask(String task) {
        Task t = new Task(task);
        tasks.add(t);
        System.out.println("added: " + task);
    }

    void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i ++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }
    }

    void completeTask(int index) {
        Task completedTask = tasks.get(index -1 );
        completedTask.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(completedTask.toString());
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void initializeChatbot() {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("done")) {
                String index = command.split(" ")[1];
                completeTask(Integer.valueOf(index));
            } else {
                addTask(command);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initializeChatbot();
    }
}

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
        System.out.println("Got it. I've added this task:\n " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i ++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    void completeTask(int index) {
        Task completedTask = tasks.get(index -1 );
        completedTask.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(completedTask);
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void initializeChatbot() {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("done")) {
                String index = command.split(" ")[1];
                completeTask(Integer.valueOf(index));
            } else if (command.equals("todo")){
                String task = sc.nextLine().trim();
                addTask(task);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initializeChatbot();
    }
}

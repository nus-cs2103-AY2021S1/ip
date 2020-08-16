import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> tasks = new ArrayList<>();

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you soon!");
    }

    void addTask(String task) {
        Task newTask = new Task(task);
        tasks.add(newTask);
        System.out.println("added: " + task);
    }

    void completeTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        task.done();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            String message = String.valueOf(i) + ".";
            message += task;
            System.out.println(message);
        }
    }

    void start() {
        greet();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning) {
            String command = sc.next();
            switch (command) {
                case "bye": {
                    exit();
                    isRunning = false;
                    break;
                }
                case "list":
                    list();
                    break;
                case "done":
                    int taskNo = sc.nextInt();
                    completeTask(taskNo);
                    break;
                default:
                    addTask(command + sc.nextLine());
                    break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}

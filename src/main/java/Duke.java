import java.util.Scanner;

public class Duke {

    private final Task[] tasks;

    public Duke() {
        this.tasks = new Task[100];
    }

    public void greet() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }

    public void handleInput() {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (! exit) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                exit = true;
                sc.close();
            } else if (command.startsWith("done")) {
                int position = Integer.parseInt(command.substring(5));
                this.markTaskAsDone(position);
            } else if (command.equals("list")) {
                this.displayTasks();
            } else {
                Task task = new Task(command);
                this.addTask(task);
            }
        }
    }

    public void addTask(Task task) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                tasks[i] = task;
                break;
            }
        }
        System.out.println("added: " + task + "\n");
    }

    public void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                Task task = tasks[i];
                String symbol = task.getStatusIcon();
                System.out.println(i + 1 + ".[" + symbol + "] " + task);
            } else {
                break;
            }
        }
        System.out.println("");
    }

    public void markTaskAsDone(int position) {
        Task task = this.tasks[position - 1];
        task.markAsDone();
        String symbol = task.getStatusIcon();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + symbol + "] " + task + "\n");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.handleInput();
        duke.exit();
    }
}

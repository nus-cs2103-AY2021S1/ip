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
            if (command.equals("bye")) { // Exits while loop
                exit = true;
                sc.close();
            } else if (command.equals("list")) {
                this.displayTasks();
            } else if (command.startsWith("done")) {
                int position = Integer.parseInt(command.substring(5));
                this.markTaskAsDone(position);
            } else if (command.startsWith("todo")) {
                String description = command.substring(5);
                Task task = new Todo(description);
                this.addTask(task);
            } else if (command.startsWith("deadline")) {
                String[] deadline = command.substring(9).split("/by");
                String description = deadline[0].trim();
                String by = deadline[1].trim();
                Task task = new Deadline(description, by);
                this.addTask(task);
            } else if (command.startsWith("event")) {
                String[] event = command.substring(6).split("/at");
                String description = event[0].trim();
                String at = event[1].trim();
                Task task = new Event(description, at);
                this.addTask(task);
            }
        }
    }

    public void addTask(Task task) {
        System.out.println("Okay! Task added for you!");
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                tasks[i] = task;
                break;
            }
        }
        System.out.println(task + "\n");
        // System.out.println("Now you have " + task + " task in the list." + "\n");
    }

    public void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                System.out.println(i + 1 + ". " + tasks[i]);
            } else {
                break;
            }
        }
        System.out.println("");
    }

    public void markTaskAsDone(int position) {
        Task task = this.tasks[position - 1];
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task + "\n");
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

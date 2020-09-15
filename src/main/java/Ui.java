import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private String line = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructor for the class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Generate a greeting when user first launch the app.
     */
    public void greeting() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    /**
     * Generate a line to divide between replies.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Read user input.
     * @return a String of the user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Generate the list of tasks.
     */
    public String generateList(TaskList taskList) {
        String list = "Here are the tasks in your list:\n";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getList().size(); i++) {
            Task t = taskList.getList().get(i);
            System.out.println((i + 1) + ". " + t.printTask());
            list += (i + 1) + ". " + t.printTask() + "\n";
        }
        return list;
    }

    /**
     * Generate a message to inform user task is successfully added.
     * @param task
     * @return a String of the message.
     */
    public String addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printTask());
        return "Got it. I've added this task: \n" + task.printTask();
    }

    /**
     * Generate a message to inform user task is successfully deleted.
     * @param task
     * @return a String of the message.
     */
    public String deleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.printTask());
        return "Noted. I've removed this task:\n" + task.printTask();
    }

    /**
     * Generate a message to inform user task is marked as completed.
     * @param task
     * @return a String of the message.
     */
    public String completedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.printTask());
        return "Nice! I've marked this task as done:\n" + task.printTask();
    }

    /**
     * Generate a message to bid farewell to user.
     * @return a String of the message.
     */
    public String exit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.scanner.close();
        return "Bye. Hope to see you again soon!";
    }
}

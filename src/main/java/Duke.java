import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Duke, more commonly known as Duck, is a Personal Assistant Chat Bot that
 * helps a person to keep track of various tasks.
 * Contains static attribute stored_task which stores task input from user.
 **/
public class Duke {

    public static List<Task> stored_task = new ArrayList<>();

    /**
     * Lists stored task by looping through stored_task, along with their status.
     **/
    public static void listStoredTasks() {
        if (stored_task.isEmpty()) {
            System.out.println("No text stored...");
        } else {
            int count = 1;
            for (Task task : stored_task) {
                System.out.println(count + ". " + task.getStatusIcon() + " " + task.getDescription());
                count++;
            }
        }
    }

    /**
     * Adds input task into stored_task.
     *
     * @param input Input task from user to be stored.
     **/
    public static void addTask(String input) {
        stored_task.add(new Task(input));
        System.out.println("Quack! I have added: " + input + " \uD83C\uDF0A");
    }

    /**
     * Marks task as done.
     *
     * @param taskNumber Task number of task to be marked as done.
     **/
    public static void markTaskAsDone(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > stored_task.size()) {
            System.out.println("Wrong task number!");
        } else {
            Task t = stored_task.get(taskNumber - 1);
            if (t.checkIfDone()) {
                System.out.println("This task is already done: " + t.getDescription());
            } else {
                t.markAsDone();
                System.out.println("Quack! I have marked this task as done: \n" +
                        t.getStatusIcon() + " " + t.getDescription() + " \uD83C\uDF0A");
            }
        }
    }

    /**
     * Prints greeting message.
     * Scans for commands entered by the user, then stores input task into stored_task.
     * Upon user command input "done" followed by the task number, task will be marked as done.
     * Upon user command input "list", stored task will be listed.
     * Upon user command input "bye", system is exited.
     **/
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String greeting_message = line +
                "\n Quack! I am Duck \uD83E\uDD86" +
                "\n How can I help you today?\n" + line;
        String exit_message = line +
                "\n Waddling off now. See you soon! \uD83D\uDC4B\uD83C\uDFFB \n" + line;
        Scanner sc = new Scanner(System.in);

        System.out.println(greeting_message);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit_message);
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                listStoredTasks();
                System.out.println(line);
            } else if (input.startsWith("done ") && input.length() > 5) {
                int taskNumber = Integer.parseInt(input.substring(5));
                System.out.println(line);
                markTaskAsDone(taskNumber);
                System.out.println(line);
            } else {
                System.out.println(line);
                addTask(input);
                System.out.println(line);
            }
        }
        sc.close();
    }
}

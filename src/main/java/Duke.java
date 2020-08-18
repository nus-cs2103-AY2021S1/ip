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
            System.out.println("No tasks stored...");
        } else {
            System.out.println("Quack! Here are the tasks in your list:");
            int count = 1;
            for (Task task : stored_task) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    /**
     * Adds input task into stored_task.
     *
     * @param newTask Input task from user to be stored.
     **/
    public static void addTask(Task newTask) {
        stored_task.add(newTask);
        System.out.println("Quack! I have added: " + newTask);
        int numOfTasks = stored_task.size();
        if (numOfTasks == 1) {
            System.out.println("My duck senses tell me you have 1 task in the list.");
        } else {
            System.out.println("My duck senses tell me you have " + numOfTasks + " tasks in the list.");
        }
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
                System.out.println("Quack! I have marked this task as done: \n" + t);
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
                "\n Quack! I am Duck" +
                "\n How can I help you today?\n" + line;
        String exit_message = line +
                "\n Waddling off now. See you soon! \n" + line;
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
            } else if (input.startsWith("todo ") && input.length() > 5) {
                ToDo newTask = new ToDo(input.substring(5));
                System.out.println(line);
                addTask(newTask);
                System.out.println(line);
            } else if (input.startsWith("deadline ") && input.length() > 9){
                int indexOfBy = input.indexOf("/by");
                if (indexOfBy == -1) {
                    System.out.println(line);
                    System.out.println("Did you include /by?");
                    System.out.println(line);
                } else {
                    String description = input.substring(9, indexOfBy);
                    String by = input.substring(indexOfBy + 3);
                    Deadline newTask = new Deadline(description, by);
                    System.out.println(line);
                    addTask(newTask);
                    System.out.println(line);
                }
            } else if (input.startsWith("event ") && input.length() > 6) {
                int indexOfAt = input.indexOf("/at");
                if (indexOfAt == -1) {
                    System.out.println(line);
                    System.out.println("Did you include /at?");
                    System.out.println(line);
                } else {
                    String description = input.substring(6, indexOfAt);
                    String at = input.substring(indexOfAt + 3);
                    Event newTask = new Event(description, at);
                    System.out.println(line);
                    addTask(newTask);
                    System.out.println(line);
                }
            } else {
                System.out.println(line);
                System.out.println("My duck instincts tell me your input makes no sense...");
                System.out.println(line);
            }
        }
        sc.close();
    }
}

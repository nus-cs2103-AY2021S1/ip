import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String helloMsg = "Hello, I'm Eggy!\n" + "How may I help you?";
    private static final String byeMsg = "Bye, see you soon!";
    private static final String addTaskMsg = "Added this task to your list:\n";
    private static final String typeTask = "Please include the type of your task";

    private static final ArrayList<Task> taskList = new ArrayList<>();

    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println(addTaskMsg + task);
        if (taskList.size() > 1) {
            System.out.println("You now have " + taskList.size() + " tasks in the list!");
        } else {
            System.out.println("You now have " + taskList.size() + " task in the list!");
        }
    }

    public static void main(String[] args) {
        System.out.println(helloMsg);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ": " + taskList.get(i));
                }
            } else if (command.startsWith("done ")) {
                String str = command.substring(5);
                int number = Integer.parseInt(str) - 1;
                Task task = taskList.get(number);
                task.completeTask();
                System.out.println("Nice! I've marked this task as done: \n" + task);
            } else {
                if (command.startsWith("todo ")) {
                    String str = command.substring(5);
                    addTask(new ToDo(str));
                } else if (command.startsWith("deadline ")) {
                    String str = command.substring(9);
                    String[] split = str.split("/by ");
                    String description = split[0];
                    String by = split[1];
                    addTask(new Deadline(description, by));
                } else if (command.startsWith("event ")) {
                    String str = command.substring(6);
                    String[] split = str.split("/at ");
                    String description = split[0];
                    String at = split[1];
                    addTask(new Event(description, at));
                } else {
                    System.out.println(typeTask);
                }
            }
            command = sc.nextLine();
        }
        System.out.println(byeMsg);
    }
}
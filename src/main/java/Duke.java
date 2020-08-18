import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Eggy\n" + "How may I help you?");
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
                taskList.add(new Task(command));
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. See you soon!");
    }
}
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    String stringCount = String.valueOf(i + 1);
                    System.out.println(stringCount + ". " + taskList.get(i));
                }
            } else if (input.length() >= 6 && input.substring(0, 4).equals("done")) {
                int index = Integer.valueOf(input.substring(5)) - 1;
                Task currentTask = taskList.get(index);
                currentTask.markDone();
                taskList.set(index, currentTask);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + currentTask);

            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                //Add a new task in the list.
                taskList.add(new Task(input));
            }
        }
    }
}

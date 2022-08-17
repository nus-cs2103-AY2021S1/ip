import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner nextCommand = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String command = nextCommand.nextLine();
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task nextTask = tasks.get(i);
                    String completion = nextTask.isComplete() ? "[✓]" : "[✗]";
                    System.out.println((i + 1) + ". " +  completion + " " + nextTask.getTaskName());
                }
            } else if (command.length() == 6 && command.substring(0,4).equals("done")){
                int completedIndex = Character.getNumericValue(command.charAt(5));
                Task currentTask = tasks.get(completedIndex - 1);
                currentTask.setComplete(true);
                System.out.println("Nice! I've marked this task as done: [✓] " + currentTask.getTaskName());
            } else {
                tasks.add(new Task(command));
                System.out.println("added: " + command);
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

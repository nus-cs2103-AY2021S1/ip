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
                    System.out.println((i + 1) + ". " + nextTask.toString());
                }
            } else if (command.length() == 6 && command.substring(0,4).equals("done")){
                int completedIndex = Character.getNumericValue(command.charAt(5));
                Task currentTask = tasks.get(completedIndex - 1);
                currentTask.setComplete(true);
                System.out.println("Nice! I've marked this task as done: [✓] " + currentTask.getTaskName());
            } else {
                if (command.length() >= 4 && command.substring(0,4).equals("todo")) {
                    Task newTask = new Todo(command);
                    tasks.add(newTask);

                } else if (command.length() >= 5 && command.substring(0,5).equals("event")) {
                    Task newTask = new Event(command);
                    tasks.add(newTask);

                } else if (command.length() >= 8 && command.substring(0,8).equals("deadline")) {
                    Task newTask = new Deadline(command);
                    tasks.add(newTask);
                }
                Task addedTask = tasks.get(tasks.size() - 1);
                System.out.println("Got it. I've added this task: \n" +
                        addedTask.toString() +
                        "\nNow you have " +  tasks.size() +  " tasks in the list.");
            }


        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

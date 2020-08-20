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
            } else if (input.length() >= 6 && input.substring(0, 4).equals("todo")){
                Task newTask = new Task("todo", input);
                taskList.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + newTask);
                System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in your list.");

            } else if (input.length() >= 10 && input.substring(0, 8).equals("deadline")) {
                String[] descDue = input.substring(9).split("/");
                Task newTask = new Task("deadline", descDue[0], descDue[1]);
                taskList.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + newTask);
                System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in your list.");

            } else if (input.length() >= 7 && input.substring(0, 5).equals("event")) {
                String[] descDue = input.substring(6).split("/");
                Task newTask = new Task("event", descDue[0], descDue[1]);
                taskList.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + newTask);
                System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in your list.");
            } else {
                System.out.println("unknown input");
            }
        }
    }
}

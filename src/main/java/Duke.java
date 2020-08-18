import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("______________________");

        String command;
        String[] inputs,tokens;
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        inputs = sc.nextLine().split(" ",2);
        command = inputs[0];

        while(!command.equals("bye")) {
            switch(command) {
                case "list":
                    System.out.println("Task list");
                    int i = 1;
                    for (Task task : tasks) {
                        System.out.println(i++ + ". " + task);
                    }
                    break;
                case "done":
                    tasks.get(Integer.parseInt(inputs[1]) - 1).markDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(tasks.get(Integer.parseInt(inputs[1]) - 1));
                    break;
                case "deadline":
                    tokens = inputs[1].split(" /by ");
                    tasks.add(new Deadline(tokens[0],tokens[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "event":
                    tokens = inputs[1].split(" /at ");
                    tasks.add(new Event(tokens[0],tokens[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "todo":
                    tasks.add(new ToDo(inputs[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    tasks.add(new Task(String.join(" ",inputs)));
                    System.out.println("added: " + String.join(" ",inputs));
            }
            inputs = sc.nextLine().split(" ",2);
            command = inputs[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }
}


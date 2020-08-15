import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> storage = new ArrayList<>();
        System.out.println("Hello Boss! How can I help you?");

        Scanner sc = new Scanner(System.in);
        String toEcho = sc.nextLine();
        while (!toEcho.equals("bye")) {
            System.out.println("--------------------------------------");
            if (toEcho.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < storage.size(); i++) {
                    int number = i + 1;
                    System.out.println(number + "." + storage.get(i));
                }
                System.out.println("--------------------------------------");
                toEcho = sc.nextLine();
                continue;
            }
            String[] command = toEcho.split(" ");
            if (command[0].equals("done")) {
                int index = Integer.parseInt(command[1]);
                System.out.println("Nice! I've marked this task as done:");
                storage.get(index - 1).markAsDone();
                System.out.println("   " + storage.get(index - 1));
                System.out.println("--------------------------------------");
                toEcho = sc.nextLine();
            } else {
                System.out.println("Got it. I've added this task:");
                if (command[0].equals("todo")) {
                    Todo todo = new Todo(toEcho.substring(4));
                    storage.add(todo);
                    System.out.println("   " + todo);
                } else if (command[0].equals("deadline")) {
                    Deadline deadline = new Deadline(toEcho.substring(8));
                    storage.add(deadline);
                    System.out.println("   " + deadline);
                } else if (command[0].equals("event")) {
                    Event event = new Event(toEcho.substring(5));
                    storage.add(event);
                    System.out.println("   " + event);
                }
                System.out.println("Now you have " + storage.size() + " tasks in the list.");
                System.out.println("--------------------------------------");
                toEcho = sc.nextLine();
            }
        }
        System.out.println("--------------------------------------\n" +
            "Bye Boss! Hope to see you again!" + "\n"
            + "--------------------------------------");
    }
}

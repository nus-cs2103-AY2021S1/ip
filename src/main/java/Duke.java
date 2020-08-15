import exceptions.DukeException;
import exceptions.DukeUnknownCommandException;
import exceptions.DukeEmptyTodoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static final String HORIZONTAL_LINE = "--------------------------------------";
    static final String GREETING = "Hello Boss! How can I help you?";
    static final String BYE = "Bye Boss! Hope to see you again!";

    public static void main(String[] args) throws DukeException {

        System.out.println(GREETING);
        Scanner sc = new Scanner(System.in);
        List<Task> storage = new ArrayList<>();
        while (sc.hasNext()) {
            try {
                String toEcho = sc.nextLine();
                System.out.println(HORIZONTAL_LINE);
                String[] command = toEcho.split(" ");
                if (toEcho.equals("bye")) {
                    System.out.println(BYE + "\n" + HORIZONTAL_LINE);
                } else if (toEcho.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < storage.size(); i++) {
                        int number = i + 1;
                        System.out.println(number + "." + storage.get(i));
                    }
                    System.out.println(HORIZONTAL_LINE);
                    continue;
                } else if (command[0].equals("done")) {
                    int index = Integer.parseInt(command[1]);
                    System.out.println("Nice! I've marked this task as done:");
                    storage.get(index - 1).markAsDone();
                    System.out.println("   " + storage.get(index - 1));
                    System.out.println(HORIZONTAL_LINE);
                } else if (command[0].equals("todo")) {
                    if (command.length == 1) {
                        throw new DukeEmptyTodoException();
                    }
                    System.out.println("Got it. I've added this task:");
                    Todo todo = new Todo(toEcho.substring(4));
                    storage.add(todo);
                    System.out.println("   " + todo);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (command[0].equals("deadline")) {
                    System.out.println("Got it. I've added this task:");
                    Deadline deadline = new Deadline(toEcho.substring(8));
                    storage.add(deadline);
                    System.out.println("   " + deadline);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (command[0].equals("event")) {
                    System.out.println("Got it. I've added this task:");
                    Event event = new Event(toEcho.substring(5));
                    storage.add(event);
                    System.out.println("   " + event);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else {
                    throw new DukeUnknownCommandException();
                }
            } catch (DukeEmptyTodoException | DukeUnknownCommandException e) {
                System.out.println(e.getMessage() + "\n" + HORIZONTAL_LINE);
            }
        }
    }
}

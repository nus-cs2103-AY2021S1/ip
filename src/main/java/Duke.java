import exceptions.DukeException;
import exceptions.DukeInvalidMessageException;
import exceptions.DukeUnknownCommandException;
import exceptions.DukeEmptyMessageException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static final String HORIZONTAL_LINE = "--------------------------------------";
    static final String GREETING = "Hello Boss! How can I help you?";
    static final String BYE = "Bye Boss! Hope to see you again!";
    static final String SHOW_TASK = "Here are the tasks in your list:";
    static final String TAB = "   ";

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
                    System.out.println(SHOW_TASK);
                    for (int i = 0; i < storage.size(); i++) {
                        int number = i + 1;
                        System.out.println(number + "." + storage.get(i));
                    }
                    System.out.println(HORIZONTAL_LINE);
                } else if (toEcho.startsWith("done")) {
                    if (toEcho.length() == 4) {
                        throw new DukeEmptyMessageException("Done");
                    } else if (Integer.parseInt(command[1]) > storage.size()) {
                        throw new DukeInvalidMessageException();
                    } else {
                        int index = Integer.parseInt(command[1]) - 1;
                        System.out.println("Nice! I've marked this task as done:");
                        storage.get(index).markAsDone();
                        System.out.println(TAB + storage.get(index));
                        System.out.println(HORIZONTAL_LINE);
                    }
                } else if (toEcho.startsWith("todo")) {
                    if (toEcho.length() == 4) {
                        throw new DukeEmptyMessageException("Todo");
                    }
                    System.out.println("Got it. I've added this task:");
                    Todo todo = new Todo(toEcho.substring(4));
                    storage.add(todo);
                    System.out.println(TAB + todo);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (toEcho.startsWith("deadline")) {
                    if (toEcho.length() == 8) {
                        throw new DukeEmptyMessageException("Deadline");
                    }
                    System.out.println("Got it. I've added this task:");
                    Deadline deadline = new Deadline(toEcho.substring(8));
                    storage.add(deadline);
                    System.out.println(TAB + deadline);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (toEcho.startsWith("event")) {
                    if (toEcho.length() == 5) {
                        throw new DukeEmptyMessageException("Event");
                    }
                    System.out.println("Got it. I've added this task:");
                    Event event = new Event(toEcho.substring(5));
                    storage.add(event);
                    System.out.println(TAB + event);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (toEcho.startsWith("delete")) {
                    if (toEcho.length() == 6) {
                        throw new DukeEmptyMessageException("Delete");
                    } else if (Integer.parseInt(command[1]) > storage.size() ||
                        Integer.parseInt(command[1]) < 0) {
                        throw new DukeInvalidMessageException();
                    }
                    System.out.println("Noted. I've removed this task:");
                    int indexToDelete = Integer.parseInt(command[1]) - 1;
                    System.out.println(TAB + storage.get(indexToDelete));
                    storage.remove(indexToDelete);
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else {
                    throw new DukeUnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage() + "\n" + HORIZONTAL_LINE);
            }
        }
    }
}

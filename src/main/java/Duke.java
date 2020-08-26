import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scan1 = new Scanner(System.in);
        ArrayList<Task> storage = new ArrayList<>();
        int count = 1;


        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (scan1.hasNext()) {
            try {
                String command = scan1.next();
                if (command.equals("")) {
                    throw new DukeException(command);
                } else if (command.equals("list")) {
                    if (storage.isEmpty()) {
                        throw new DukeException(command);
                    }
                    for (Task item : storage) {
                        int position = storage.indexOf(item) + 1;
                        System.out.println(position + "." + item);

                    }
                } else if (command.equals("done")) {
                    try {
                        int number = scan1.nextInt();
                        Task current = storage.get(number - 1);
                        current.setDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(current.getStatusIcon() + " " + current.getDescription());
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("delete2");
                    } catch (InputMismatchException e) {
                        throw new DukeException(command);
                    }
                } else if (command.equals("todo")) {
                    String desc = scan1.nextLine();
                    if (desc.equals("")) {
                        throw new DukeException(command);
                    }
                    Todo todo = new Todo(desc);
                    storage.add(todo);
                    int size = storage.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + size + " tasks in the list.");
                } else if (command.equals("deadline")) {
                    String desc = scan1.nextLine();
                    if (desc.equals("")) {
                        throw new DukeException(command);
                    }
                    String[] string = desc.split("/by ");
                    if (string.length < 2) {
                        throw new DukeException(command);
                    }
                    Deadline deadline = new Deadline(string[0], string[1]);
                    storage.add(deadline);
                    int size = storage.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + size + " tasks in the list.");
                } else if (command.equals("event")) {
                    String desc = scan1.nextLine();
                    if (desc.equals("")) {
                        throw new DukeException(command);
                    }
                    String[] string = desc.split("/at ");
                    if (string.length < 2) {
                        throw new DukeException(command);
                    }
                    Events event = new Events(string[0], string[1]);
                    storage.add(event);
                    int size = storage.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + size + " tasks in the list.");
                } else if (command.equals("bye")) {
                    String bye = "Bye. Hope to see you again soon!";
                    System.out.println(bye);
                    scan1.close();
                    break;
                } else if (command.equals("delete")) {
                    try {
                        int number = scan1.nextInt();
                        Task task = storage.get(number - 1);
                        storage.remove(number - 1);
                        int size = storage.size();
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + task);
                        System.out.println("Now you have " + size + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("delete2");
                    } catch (InputMismatchException e) {
                        throw new DukeException(command);
                    }
                }
                else {
                    throw new DukeException(command);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Parser {
    
    Parser() {
        
    }

    /**
     * Helps to parse commands from the scanner, that tells the bot what actions
     * to take
     * @param list
     * @param store
     * @throws DukeException
     * @throws FileNotFoundException
     */
    public void commandParser(TaskList list, Storage store ) throws DukeException, FileNotFoundException {
        ArrayList<Task> storage = list.getTasks();

        Scanner scan1 = new Scanner(System.in);
        
        while (scan1.hasNext()) {
            String command = scan1.nextLine();
            try {
                if (command.equals("")) {
                    throw new DukeException(command);
                } else if (command.equals("list")) {
                    if (list.isEmpty()) {
                        throw new DukeException(command);
                    }
                    for (Task item : storage ) {
                        int position = storage.indexOf(item) + 1;
                        System.out.println(position + "." + item);

                    }
                } else if (command.startsWith("done")) {
                    try {
                        int number = Integer.parseInt(command.split(" ")[1]);
                        Task current = storage.get(number - 1);
                        current.setDone();
                        store.save(storage);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(current.getStatusIcon() + " " + current.getDescription());
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("delete2");
                    } catch (InputMismatchException e) {
                        throw new DukeException(command);
                    }
                } else if (command.startsWith("todo")) {
                    String desc = command.split(" ")[1];
                    if (desc.equals("")) {
                        throw new DukeException(command);
                    }
                    Todo todo = new Todo(desc);

                    store.save(storage, todo);
                    int size = storage.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + size + " tasks in the list.");
                } else if (command.startsWith("deadline")) {
                    String desc = command.split("/by ")[1];
                    if (desc.equals("")) {
                        throw new DukeException(command);
                    }
                    String[] string = command.split("/by ");
                    if (string.length < 2) {
                        throw new DukeException(command);
                    }
                    Deadline deadline = new Deadline(string[0], string[1]);
                    store.save(storage, deadline);
                    int size = storage.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + size + " tasks in the list.");
                } else if (command.startsWith("event")) {
                    String desc = command.split("/at ")[1];
                    if (desc.equals("")) {
                        throw new DukeException(command);
                    }
                    String[] string = command.split("/at ");
                    if (string.length < 2) {
                        throw new DukeException(command);
                    }
                    Events event = new Events(string[0], string[1]);
                    store.save(storage, event);
                    int size = storage.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + size + " tasks in the list.");
                } else if (command.equals("bye")) {
                    String bye = "Bye. Hope to see you again soon!";
                    System.out.println(bye);
                    scan1.close();
                    break;
                } else if (command.startsWith("delete")) {
                    try {
                        int number = Integer.parseInt(command.split(" ")[1]);
                        Task task = storage.get(number - 1);
                        storage.remove(number - 1);
                        store.save(storage);
                        int size = storage.size();
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + task);
                        System.out.println("Now you have " + size + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("delete2");
                    } catch (InputMismatchException e) {
                        throw new DukeException(command);
                    }
                } else {
                    throw new DukeException(command);
                }
            } catch (DukeException | FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
}

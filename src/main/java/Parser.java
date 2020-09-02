import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Parser {
    /**
     * Helps to parse commands from the scanner, that tells the bot what actions
     * to take
     *
     * @param list
     * @param store
     * @throws DukeException
     * @throws FileNotFoundException
     */

    public String commandParser(String command, TaskList list, Storage store)
            throws DukeException, FileNotFoundException {
        ArrayList<Task> storage = list.getTasks();

        //Scanner scan1 = new Scanner(System.in);

        //String command = scan1.nextLine();
        try {
            if (command.equals("")) {
                throw new DukeException(command);
            } else if (command.equals("list")) {
                if (list.isEmpty()) {
                    throw new DukeException(command);
                }
                String tasks = "";
                for (Task item : storage) {
                    int position = storage.indexOf(item) + 1;
                    //System.out.println(position + "." + item);
                    tasks = tasks + position + "." + item + "\n";
                }
                return tasks;
            } else if (command.startsWith("done")) {
                try {
                    int number = Integer.parseInt(command.split(" ")[1]);
                    Task current = storage.get(number - 1);
                    current.setDone();
                    store.save(storage);
                    //System.out.println("Nice! I've marked this task as done:");
                    //System.out.println(current.getStatusIcon() + " " + current.getDescription());
                    return "Nice! I've marked this task as done: " + "\n"
                            + current.getStatusIcon() + " " + current.getDescription();
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("delete2");
                } catch (InputMismatchException e) {
                    throw new DukeException(command);
                }
            } else if (command.startsWith("todo")) {
                String desc = command.split("do ")[1];
                if (desc.equals("")) {
                    throw new DukeException(command);
                }
                Todo todo = new Todo(desc);

                store.save(storage, todo);
                int size = storage.size();
                //System.out.println("Got it. I've added this task:");
                //System.out.println("  " + todo);
                //System.out.println("Now you have " + size + " tasks in the list.");
                return "Got it. I've added this task: " + "\n" + todo + "\n"
                        + "Now you have " + size + " tasks in the list.";
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
                //System.out.println("Got it. I've added this task:");
                //System.out.println("  " + deadline);
                //System.out.println("Now you have " + size + " tasks in the list.");
                return "Got it. I've added this task: " + "\n" + deadline + "\n"
                        + "Now you have " + size + " tasks in the list.";
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
                //System.out.println("Got it. I've added this task:");
                //System.out.println("  " + event);
                //System.out.println("Now you have " + size + " tasks in the list.");
                return "Got it. I've added this task: " + "\n" + event + "\n"
                        + "Now you have " + size + " tasks in the list.";
            } else if (command.equals("bye")) {
                String bye = "Bye. Hope to see you again soon!";
                return bye;
                //System.out.println(bye);
                //scan1.close();
                //break;
            } else if (command.startsWith("delete")) {
                try {
                    int number = Integer.parseInt(command.split(" ")[1]);
                    Task task = storage.get(number - 1);
                    storage.remove(number - 1);
                    store.save(storage);
                    int size = storage.size();
                    //System.out.println("Noted. I've removed this task:");
                    //System.out.println("  " + task);
                    //System.out.println("Now you have " + size + " tasks in the list.");
                    return "Noted, I've removed this task: " + "\n" + task + "\n"
                            + "Now you have " + size + " tasks in the list.";
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("delete2");
                } catch (InputMismatchException e) {
                    throw new DukeException(command);
                }
            } else if (command.startsWith("find")) {
                String[] desc = command.split("find");
                if (desc.length < 2) {
                    throw new DukeException(command);
                }
                String keyword = command.split("nd ")[1];

                int count = 0;
                String tasks = "";
                for (Task item : storage) {
                    if (item.getDescription().contains(keyword)) {
                        //System.out.println(count + "." + item);
                        count++;
                        tasks = tasks + count + "." + item + "\n";
                    }
                }
                if (tasks.equals("")) {
                    throw new DukeException("find2");
                } else {
                    return tasks;
                }
            } else {
                throw new DukeException(command);
            }
        } catch (DukeException | FileNotFoundException e) {
            return e.getMessage();
        }
    }
}

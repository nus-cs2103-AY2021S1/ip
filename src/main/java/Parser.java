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
        try {
            assert !command.equals("") : "Please type in your command.";
            if (command.equals("list")) {
                if (list.isEmpty()) {
                    throw new DukeException(command);
                }
                String tasks = "";
                for (Task item : storage) {
                    int position = storage.indexOf(item) + 1;
                    tasks = tasks + position + "." + item + "\n";
                }
                return tasks;
            } else if (command.startsWith("done")) {
                try {
                    int number = Integer.parseInt(command.split(" ")[1]);
                    Task current = storage.get(number - 1);
                    current.setDone();
                    store.save(storage);
                    return "Nice! I've marked this task as done: " + "\n"
                            + current.getStatusIcon() + " " + current.getDescription();
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("delete2");
                } catch (InputMismatchException e) {
                    throw new DukeException(command);
                }
            } else if (command.startsWith("todo")) {
                if (command.equals("todo")) {
                    throw new DukeException(command);
                }
                String[] string = command.split("do ");
                if (string.length < 2) {
                    throw new DukeException("deadline");
                }
                String desc = string[1];
                Todo todo = new Todo(desc);

                store.save(storage, todo);
                int size = storage.size();
                return "Got it. I've added this task: " + "\n" + todo + "\n"
                        + "Now you have " + size + " tasks in the list.";
            } else if (command.startsWith("deadline")) {
                if (!command.startsWith("deadline /by")) {
                    throw new DukeException("deadline");
                }
                String[] string = command.split("/by ");
                if (string.length < 2) {
                    throw new DukeException("deadline");
                }
                Deadline deadline = new Deadline(string[0], string[1]);
                store.save(storage, deadline);
                int size = storage.size();
                return "Got it. I've added this task: " + "\n" + deadline + "\n"
                        + "Now you have " + size + " tasks in the list.";
            } else if (command.startsWith("event")) {
                if (!command.startsWith("event /at")) {
                    throw new DukeException("event");
                }
                String[] string = command.split("/at ");
                if (string.length < 2) {
                    throw new DukeException("event");
                }
                Events event = new Events(string[0], string[1]);
                store.save(storage, event);
                int size = storage.size();
                return "Got it. I've added this task: " + "\n" + event + "\n"
                        + "Now you have " + size + " tasks in the list.";
            } else if (command.equals("bye")) {
                String bye = "Bye. Hope to see you again soon!";
                return bye;
            } else if (command.startsWith("delete")) {
                try {
                    int number = Integer.parseInt(command.split(" ")[1]);
                    Task task = storage.get(number - 1);
                    storage.remove(number - 1);
                    store.save(storage);
                    int size = storage.size();
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

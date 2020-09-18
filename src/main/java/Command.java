import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javafx.application.Platform;

public class Command {
    private String prevCommand = "";
    private int position = -1;
    private ArrayList<Task> deleted = new ArrayList<>();
    /**
     * Prints out the current tasks in the list
     * @param command
     * @param list
     * @param storage
     * @return List of current tasks in String format
     * @throws DukeException
     * */
    public String list(String command, TaskList list, ArrayList<Task> storage) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException(command);
        }
        String tasks = "";
        for (Task item : storage) {
            int position = storage.indexOf(item) + 1;
            tasks = tasks + position + "." + item + "\n";
        }
        return tasks;
    }

    /**
     * Checks a particular task in the list as done
     * @param command
     * @param store
     * @param storage
     * @return Selected Task marked done in String format
     * @throws DukeException
     */
    public String done(String command, Storage store, ArrayList<Task> storage) throws DukeException {
        try {
            int number = Integer.parseInt(command.split(" ")[1]);
            Task current = storage.get(number - 1);
            current.setDone();
            store.save(storage);
            prevCommand = "done";
            position = number - 1;
            return "Nice! I've marked this task as done: " + "\n"
                    + current.getStatusIcon() + " " + current.getDescription();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("delete2");
        } catch (InputMismatchException e) {
            throw new DukeException(command);
        } catch (FileNotFoundException e) {
            return "Sorry, the list is currently empty!";
        } catch (NumberFormatException e) {
            return "Please enter the position of the item you wish to delete.";
        }
    }

    /**
     * Adds a Todo Task into the current list of Tasks
     * @param command
     * @param store
     * @param storage
     * @return a Todo Task in String format
     * @throws DukeException
     * @throws FileNotFoundException
     */
    public String todo(String command, Storage store, ArrayList<Task> storage)
            throws DukeException, FileNotFoundException {
        String[] string = command.split("do ");
        if (string.length < 2) {
            throw new DukeException("todo");
        }
        if (string[1].isBlank()) {
            throw new DukeException("todo");
        }
        String desc = string[1];
        Todo todo = new Todo(desc);

        store.save(storage, todo);
        int size = storage.size();
        prevCommand = "task";
        position = size - 1;
        return "Got it. I've added this task: " + "\n" + todo + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Adds a Deadline Task into the current list of Tasks
     * @param command
     * @param store
     * @param storage
     * @return a Deadline Task with the time in String format
     * @throws DukeException
     * @throws FileNotFoundException
     */
    public String deadline(String command, Storage store, ArrayList<Task> storage)
            throws DukeException, FileNotFoundException {
        String[] string = command.split("/by ");
        if (string.length < 2) {
            throw new DukeException("deadline");
        }
        if (string[1].isBlank()) {
            throw new DukeException("deadline");
        }
        String[] desc = string[0].split("line ");
        if (desc[1].isBlank()) {
            throw new DukeException("deadline");
        }
        Deadline deadline = new Deadline(string[0], string[1]);
        store.save(storage, deadline);
        int size = storage.size();
        prevCommand = "task";
        position = size - 1;
        return "Got it. I've added this task: " + "\n" + deadline + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Adds an Event Task to the current list of Tasks
     * @param command
     * @param store
     * @param storage
     * @return an Event Task with the time in String format
     * @throws DukeException
     * @throws FileNotFoundException
     */
    public String event(String command, Storage store, ArrayList<Task> storage)
            throws DukeException, FileNotFoundException {
        String[] string = command.split("/at ");
        if (string.length < 2) {
            throw new DukeException("event");
        }
        if (string[1].isBlank()) {
            throw new DukeException("event");
        }
        String[] desc = string[0].split("ent ");
        if (desc[1].isBlank()) {
            throw new DukeException("event");
        }
        Events event = new Events(string[0], string[1]);
        store.save(storage, event);
        int size = storage.size();
        prevCommand = "task";
        position = size - 1;
        return "Got it. I've added this task: " + "\n" + event + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints the good bye message
     * @return the good bye message in String format
     */
    public String bye() {
        Platform.exit();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Deletes an item of choice from the current list of Tasks
     * @param command
     * @param store
     * @param storage
     * @return the selected deleted item in String format
     * @throws DukeException
     */
    public String delete(String command, Storage store, ArrayList<Task> storage) throws DukeException {
        try {
            String[] temp = command.split("e ");
            if (temp[1].length() == 1) {
                int number = Integer.parseInt(command.split(" ")[1]);
                Task task = storage.get(number - 1);
                deleted.add(task);
                prevCommand = "delete";
                storage.remove(number - 1);
                store.save(storage);
                int size = storage.size();
                return "Noted, I've removed this task: " + "\n" + task + "\n"
                        + "Now you have " + size + " tasks in the list.";
            } else {
                String[] range = temp[1].split("-");
                int first = Integer.parseInt(range[0]);
                int second = Integer.parseInt(range[1]);
                String deletedTasks = "";
                for (int i = first - 1; i < second; i++) {
                    Task task = storage.get(first - 1);
                    deleted.add(task);
                    deletedTasks += task + "\n";
                    storage.remove(first - 1);
                }
                store.save(storage);
                prevCommand = "massDelete";
                int size = storage.size();
                return "Noted, I've removed the following tasks: \n" + deletedTasks
                        + "Now you have " + size + " tasks in the list.";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("delete2");
        } catch (InputMismatchException | FileNotFoundException e) {
            throw new DukeException(command);
        } catch (NumberFormatException e) {
            return "Please enter the position of the item you wish to delete.";
        }
    }

    /**
     * Searches all relevant Tasks in the current list based on
     * the keyword provided
     * @param command
     * @param storage
     * @return relevant Tasks based on the keyword in String format
     * @throws DukeException
     * */
    public String find(String command, ArrayList<Task> storage) throws DukeException {
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
    }

    /**
     * Undoes the most recent change to the current list of Tasks
     * @param store
     * @param storage
     * @return The most recent change to the current list of Tasks
     * @throws FileNotFoundException
     */
    public String undo(Storage store, ArrayList<Task> storage) throws FileNotFoundException {
        if (prevCommand.equals("done")) {
            Task current = storage.get(position);
            current.setUndone();
            store.save(storage);
            prevCommand = "";
            return "Nice! I've unchecked this task " + "\n"
                    + current.getStatusIcon() + " " + current.getDescription();
        } else if (prevCommand.equals("task")) {
            Task task = storage.get(position);
            storage.remove(position);
            store.save(storage);
            prevCommand = "";
            position = -1;
            return "Alright! I've undid your previous addition: " + "\n" + task;
        } else if (prevCommand.equals("delete")) {
            Task task = deleted.get(0);
            storage.add(task);
            store.save(storage);
            prevCommand = "";
            deleted = new ArrayList<>();
            return "Alright! I've undid your previous deletion \n" + task;
        } else if (prevCommand.equals("massDelete")) {
            String tasks = "";
            for (Task item : deleted) {
                storage.add(item);
                tasks += item + "\n";
            }
            store.save(storage);
            prevCommand = "";
            deleted = new ArrayList<>();
            return "Alright! I've undid the following deletions: \n" + tasks;
        } else {
            return "Cannot undo any further.";
        }
    }
}

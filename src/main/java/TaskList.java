import java.util.ArrayList;
import java.util.List;

/**
 * Represents the task list handler.
 */
public class TaskList {
    private static final List<Task> storage = new ArrayList<>();
    private final Ui ui;

    /**
     * Constructor for TaskList Object.
     */
    public TaskList() {
        this.ui = new Ui();
    }

    /**
     * Displays the task list.
     */
    public String displayList() {
        if (storage.isEmpty()) {
            return "Your list is empty!\n";
        } else {
            int listLen = storage.size();
            String output = "Here are the tasks in your list:\n";

            for (int i = 1; i <= listLen; i++) {
                Task curr = storage.get(i - 1);
                output = output.concat(i + "." + curr + "\n");
            }
            return output;
        }
    }

    /**
     * Adds task to the task list.
     * @param s The type of task to add
     * @param next The remaining task description
     * @throws DukeException The Exception of Duke bot
     */
    public String addTask(String s, String next) throws DukeException {
        Task toAdd = null;

        if (s.matches("todo|deadline|event|done|delete") && next.equals("")) {
            throw new DukeException("OOPS!!! The description of " + s + " cannot be empty.");
        }

        switch (s) {
        case "todo":
            ToDo todo = new ToDo(next);
            storage.add(todo);
            toAdd = todo;
            break;
        case "deadline":
            if (next.contains("/by ")) {
                String[] ls = next.split(" /by ");
                String date = Parser.parseDateTime(ls[1]);
                if (date.contains("Please input the time and date in\n")) {
                    return date;
                } else {
                    toAdd = new Deadline(ls[0], date);
                    storage.add(toAdd);
                }

            } else {
                throw new DukeException("Sorry, please specify expected deadline after \"/by\".");
            }
            break;
        case "event":
            if (next.contains("/at ")) {
                String[] ls = next.split(" /at ");
                String date = Parser.parseDateTime(ls[1]);
                if (date.contains("Please input the time and date in\n")) {
                    return date;
                } else {
                    toAdd = new Event(ls[0], date);
                    storage.add(toAdd);
                }

            } else {
                throw new DukeException("Sorry, please specify event date after \"/at\".");
            }
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }

        return ui.addTaskLine(toAdd, storage.size());
    }

    /**
     * Completes given from the task list.
     * @param s Index of task to be completed on the list
     * @throws DukeException The Exception of Duke bot
     */
    public String doneTask(String s) throws DukeException {
        try {
            int i = Integer.parseInt(s);
            if (i < 1 || i > storage.size()) {
                throw new DukeException("You have entered an invalid number: " + i
                    + ". Please try again.");
            } else {
                Task t = storage.get(i - 1);
                Task completed = t.setDone(true);
                storage.set(i - 1, completed);
                return "Nice! I've marked this task as done:\n" + "  "
                        + completed + "\n";
            }
        } catch (NumberFormatException nfe) {
            return "Please state the completed task number after \"done\".\n";
        }
    }

    /**
     * Deletes given task from the task list.
     * @param s Index of task to be completed on the list
     * @throws DukeException The Exception of Duke bot
     */
    public String delTask(String s) throws DukeException {
        if (storage.size() == 0 || s.toLowerCase().equals("all")) {
            storage.clear();
            return "All tasks cleared!!\n";
        } else if (s.equals("")) {
            try {
                return addTask("delete", "");
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else {
            try {
                int i = Integer.parseInt(s);
                if (i < 1 || i > storage.size()) {
                    throw new DukeException("You have entered an invalid number: " + i
                        + ". Please try again.");
                } else {
                    Task t = storage.get(i - 1);
                    storage.remove(i - 1);
                    return ui.removeTaskLine(t, storage.size());
                }
            } catch (NumberFormatException e) {
                return "Please state the completed task number after \"delete\".\n";
            }
        }
    }

    /**
     * Prints the list of tasks that contain the given string.
     * @param s The string that should appear in the task
     */
    public String findTask(String s) {
        if (storage.isEmpty()) {
            return "Your list is empty!\n";
        } else {
            int index = 1;
            String output = "Here are the matching tasks in your list:\n";
            for (Task curr : storage) {
                if (curr.getName().toLowerCase().contains(s.toLowerCase())) {
                    output = output.concat(index + "." + curr + "\n");
                    index++;
                }
            }
            if (index == 1) {
                return "There was no match!";
            } else {
                return output;
            }
        }
    }

    /**
     * Getter method for task list.
     * @return The current task list of the bot
     */
    public List<Task> getList() {
        return storage;
    }
}

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
     * @return Task addition string
     * @throws DukeException The Exception of Duke bot
     */
    public String addTask(String s, String next) throws DukeException {
        Task toAdd;

        if (s.matches("todo|deadline|event|done|delete|update") && next.equals("")) {
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

        assert toAdd != null : "Nothing added!";
        return ui.addTaskLine(toAdd, storage.size());
    }

    /**
     * Completes given from the task list.
     * @param s Index of task to be completed on the list
     * @return Task done String
     * @throws DukeException The Exception of Duke bot
     */
    public String doneTask(String s) throws DukeException {
        try {
            int index = Integer.parseInt(s);
            boolean isWithinSizeLimit = index > storage.size();
            boolean isLowerThanSizeMin = index < 1;
            if (isLowerThanSizeMin || isWithinSizeLimit) {
                throw new DukeException("You have entered an invalid number: " + index
                    + ". Please try again.");
            } else {
                Task t = storage.get(index - 1);
                Task completed = t.setDone();
                storage.set(index - 1, completed);
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
     * @return Task deleted String
     * @throws DukeException The Exception of Duke bot
     */
    public String delTask(String s) throws DukeException {
        boolean isEmptyStorage = storage.size() == 0;
        boolean isAll = s.toLowerCase().equals("all");
        boolean isEmptyString = s.equals("");

        if (isEmptyStorage || isAll) {
            storage.clear();
            return "All tasks cleared!!\n";
        } else if (isEmptyString) {
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
     * @return List of tasks found matching input s
     */
    public String findTask(String s) {
        if (storage.isEmpty()) {
            return "Your list is empty!\n";
        } else {
            int count = 1;
            String output = "Here are the matching tasks in your list:\n";
            for (Task curr : storage) {
                if (curr.getName().toLowerCase().contains(s.toLowerCase())) {
                    output = output.concat(count + "." + curr + "\n");
                    count++;
                }
            }
            if (count == 1) {
                return "There was no match!";
            } else {
                return output;
            }
        }
    }

    /**
     * Updates current task with new name or time.
     * @param next The remaining task description
     * @return Task updated String
     * @throws DukeException The Exception of Duke bot
     */
    public String updateTask(String next) throws DukeException {
        try {
            String[] ls = next.split(" ");
            int index = Integer.parseInt(ls[0]) - 1;
            String command = ls[1].toLowerCase();
            Task oldTask = storage.remove(index);
            Task updatedTask;

            switch (command) {
            case "name":
                String[] splitName = next.split(" name ");
                String updatedName = splitName[1];
                updatedTask = oldTask.updateName(updatedName);
                storage.add(index, updatedTask);
                break;
            case "time":
                String[] splitTime = next.split(" time ");
                String updatedInput = splitTime[1];
                String updatedTime = Parser.parseDateTime(updatedInput);
                if (updatedTime.contains("Please input the time and date in\n")) {
                    return updatedTime;
                } else {
                    updatedTask = oldTask.updateTime(updatedTime);
                    storage.add(index, updatedTask);
                }
                break;
            default:
                throw new DukeException("Please choose either \"name\" or \"time\" to update.");
            }
            return ui.updateTaskLine(oldTask, updatedTask);
        } catch (NumberFormatException nfe) {
            return "Please state task number after \"update\".\n";
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

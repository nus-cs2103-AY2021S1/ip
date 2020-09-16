package duke.admin;

import duke.exception.DukeException;
import duke.tag.CommandTag;
import duke.task.*;
import duke.utility.MyString;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent the list that stores all the tasks and handle new tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public ArrayList<Task> getArrayList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Constructor.
     * @param tasks List of previous stored tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Format data to the form of [MMM d yyyy]
     * @param str unformatted date
     * @return formatted date
     */
    private String formatDate(String str) {
        LocalDate d;
        try {
            d = LocalDate.parse(str);
        } catch (Exception e) {
            return str;
        }
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Handle command: list
     * @param response
     */
    public void handleList(MyString response) {
        response.addNewLines("Here are the tasks in your list:");
        // list with index
        for (int i = 1; i <= getSize(); ++i) {
            response.addNewLines(i + "." + tasks.get(i - 1).getStatus());
        }
        // list without index
        // tasks.getArrayList().forEach((n) -> System.out.println(n.getStatus()));
    }

    /**
     * Convert string to index
     * @param value
     * @return
     * @throws DukeException
     */
    private int stringToIndex(String value) throws DukeException {
        int c = 0;
        for (int i = 0; i < value.length(); ++i) {
            if ('0' > value.charAt(i) || value.charAt(i) > '9') throw new DukeException("Index should be an integer");
            c = c * 10 + value.charAt(i) - '0';
        }
        if (0 > c || c > getSize()) throw new DukeException("Index is out of range");
        return c;
    }


    /**
     * get index
     * @param cmd user's command
     * @return index
     * @throws DukeException
     */
    private int parseDone(String cmd) throws DukeException {
        String value = cmd.substring(CommandTag.DONE.length()).trim();
        return stringToIndex(value);
    }

    /**
     * Handle command: done
     * @param cmd
     * @param response
     */
    public void handleDone(String cmd, MyString response) {
        try {
            int index = parseDone(cmd);

            response.addNewLines("Nice! I've marked this task as done:");

            tasks.get(index - 1).done();

            response.addNewLines(tasks.get(index - 1).getStatus());
        } catch (DukeException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get description of the taks
     * @param cmd
     * @return
     * @throws DukeException
     */
    private String parseToDo(String cmd) throws DukeException {
        String getName = cmd.substring(CommandTag.TODO.length()).trim();
        if (getName.length() == 0) throw new DukeException("The description of a todo cannot be empty.");
        return getName;
    }

    /**
     * Handle command: todo
     * @param cmd
     * @param response
     */
    public void handleToDo(String cmd, MyString response) {
        try {
            String getName = parseToDo(cmd);
            Todo todo = new Todo(getName);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + todo.getStatus());

            tasks.add(todo);

            response.addNewLines("Now you have " + getSize() + " tasks in the list.");
        } catch (DukeException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information of deadline.
     * @param cmd
     * @return
     * @throws DukeException
     */
    private ArrayList<String> parseDeadline(String cmd) throws DukeException {
        String getName = "";
        String getDeadline = "";
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.startsWith(CommandTag.BY_TAG, i)) {
                getName = cmd.substring(CommandTag.DEADLINE.length(), i).trim();
                getDeadline = cmd.substring(i + CommandTag.BY_TAG.length()).trim();
                break;
            }
        }
        if (getName.length() == 0) throw new DukeException("The description of a deadline cannot be empty.");
        if (getDeadline.length() == 0) throw new DukeException("The time of a deadline cannot be empty.");
        return new ArrayList<>(Arrays.asList(getName, getDeadline));
    }

    /**
     * Handle command: deadline.
     * @param cmd
     * @param response
     */
    public void handleDeadline(String cmd, MyString response) {
        try {
            ArrayList<String> arr = parseDeadline(cmd);
            String getName = arr.get(0);
            String getDeadline = arr.get(1);
            getDeadline = formatDate(getDeadline);
            Deadline deadline = new Deadline(getName, getDeadline);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + deadline.getStatus());

            tasks.add(deadline);

            response.addNewLines("Now you have " + getSize() + " tasks in the list.");
        }
        catch (DukeException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information on event.
     * @param cmd
     * @return
     * @throws DukeException
     */
    private ArrayList<String> parseEvent(String cmd) throws DukeException {
        String getName = "";
        String getTime = "";
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.startsWith(CommandTag.AT_TAG, i)) {
                getName = cmd.substring(CommandTag.EVENT.length(), i).trim();
                getTime = cmd.substring(i + CommandTag.AT_TAG.length()).trim();
                break;
            }
        }
        if (getName.length() == 0) throw new DukeException("The description of a event cannot be empty.");
        if (getTime.length() == 0) throw new DukeException("The time of a event cannot be empty.");
        return new ArrayList<>(Arrays.asList(getName, getTime));
    }

    /**
     * Handle command: event.
     * @param cmd
     * @param response
     */
    public void handleEvent(String cmd, MyString response) {
        try {
            ArrayList<String> arr = parseEvent(cmd);
            String getName = arr.get(0);
            String getTime = arr.get(1);
            getTime = formatDate(getTime);
            Event event = new Event(getName, getTime);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + event.getStatus());

            tasks.add(event);

            response.addNewLines("Now you have " + getSize() + " tasks in the list.");
        }
        catch (DukeException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information on delete.
     * @param cmd
     * @return
     * @throws DukeException
     */
    private int parseDelete(String cmd) throws DukeException {
        String value = cmd.substring(CommandTag.DELETE.length()).trim();
        return stringToIndex(value);
    }

    /**
     * handle command: delete.
     * @param cmd
     * @param response
     */
    public void handleDelete(String cmd, MyString response) {
        try {
            int index = parseDelete(cmd);

            response.addNewLines("Noted. I've removed this task: ");
            response.addNewLines(tasks.get(index - 1).getStatus());

            tasks.remove(index - 1);

            response.addNewLines("Now you have " + getSize() + " tasks in the list.");
        }
        catch (DukeException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information on find.
     * @param cmd
     * @return
     * @throws DukeException
     */
    private String parseFind(String cmd) throws DukeException {
        String pattern = cmd.substring(CommandTag.FIND.length()).trim();
        if (pattern.length() == 0) throw new DukeException("The pattern of a find cannot be empty.");
        return pattern;
    }

    /**
     * handle command: find
     * @param cmd
     * @param response
     */
    public void handleFind(String cmd, MyString response) {
        try {
            String pattern = parseFind(cmd);

            response.addNewLines("Here are the matching tasks in your list:");
            // show find with index
            for (int i = 1; i <= getSize(); ++i) {
                if (tasks.get(i - 1).getDescription().contains(pattern)) {
                    response.addNewLines(i + "." + tasks.get(i - 1).getStatus());
                }
            }
            /*
            // show find without index
            List<Task> containsList = getArrayList().stream().filter(n -> n.description.contains(pattern))
                                                    .collect(Collectors.toList());
             */
        }
        catch (DukeException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information on dowithin
     * @param cmd
     * @return
     * @throws DukeException
     */
    private ArrayList<String> parseDoWithin(String cmd) throws DukeException {
        String getName = "";
        String from = "";
        String to = "";
        for (int i = CommandTag.DO_WITHIN.length(); i < cmd.length(); ++i) {
            if (cmd.startsWith(CommandTag.BETWEEN_TAG, i)) {
                getName = cmd.substring(CommandTag.DO_WITHIN.length(), i).trim();
                for (int j = i + CommandTag.DO_WITHIN.length(); j < cmd.length(); ++j) {
                    if (!cmd.startsWith(CommandTag.AND_TAG, j)) {
                        continue;
                    }
                    from = cmd.substring(i + CommandTag.DO_WITHIN.length(), j).trim();
                    to = cmd.substring(j + CommandTag.AND_TAG.length()).trim();
                    break;
                }
                break;
            }
        }
        if (getName.length() == 0) throw new DukeException("The description of a dowithin cannot be empty.");
        if (from.length() == 0) throw new DukeException("The start time of a dowithin cannot be empty.");
        if (to.length() == 0) throw new DukeException("The end time of a dowithin cannot be empty.");
        return new ArrayList<>(Arrays.asList(getName, from, to));
    }

    /**
     * handle dowithin
     * @param cmd
     * @param response
     */
    public void handleDoWithin(String cmd, MyString response) {
        try {
            ArrayList<String> arr = parseDoWithin(cmd);
            String getName = arr.get(0);
            String from = arr.get(1);
            String to = arr.get(2);
            DoWithinPeriodTasks tmp = new DoWithinPeriodTasks(getName, from, to);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + tmp.getStatus());

            tasks.add(tmp);

            response.addNewLines("Now you have " + getSize() + " tasks in the list.");
        }
        catch (DukeException ex) {
            response.addNewLines(ex.getMessage());
        }
    }
}

package rock.admin;

import java.util.ArrayList;
import java.util.Arrays;

import rock.exception.RockException;
import rock.tag.CommandTag;
import rock.task.Deadline;
import rock.task.DoWithinPeriodTasks;
import rock.task.Event;
import rock.task.Task;
import rock.task.Todo;
import rock.utility.DateFormatter;
import rock.utility.MyString;

/**
 * Represent the list that stores all the tasks and handle new tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor.
     * @param tasks List of previous stored tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getArrayList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
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
     * @param value String
     * @return c index
     * @throws RockException
     */
    private int stringToIndex(String value) throws RockException {
        int c = 0;
        for (int i = 0; i < value.length(); ++i) {
            if ('0' > value.charAt(i) || value.charAt(i) > '9') {
                throw new RockException("Index should be an integer");
            }
            c = c * 10 + value.charAt(i) - '0';
        }
        if (0 > c || c > getSize()) {
            throw new RockException("Index is out of range");
        }
        return c;
    }


    /**
     * get index
     * @param cmd user's command
     * @return index
     * @throws RockException
     */
    private int parseDone(String cmd) throws RockException {
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
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get description of the taks
     * @param cmd
     * @return getName
     * @throws RockException
     */
    private String parseToDo(String cmd) throws RockException {
        String getName = cmd.substring(CommandTag.TODO.length()).trim();
        if (getName.length() == 0) {
            throw new RockException("The description of a todo cannot be empty.");
        }
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
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information of deadline.
     * @param cmd
     * @return getName + getDeadline
     * @throws RockException
     */
    private ArrayList<String> parseDeadline(String cmd) throws RockException {
        String getName = "";
        String getDeadline = "";
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.startsWith(CommandTag.BY_TAG, i)) {
                getName = cmd.substring(CommandTag.DEADLINE.length(), i).trim();
                getDeadline = cmd.substring(i + CommandTag.BY_TAG.length()).trim();
                break;
            }
        }
        if (getName.length() == 0) {
            throw new RockException("The description of a deadline cannot be empty.");
        }
        if (getDeadline.length() == 0) {
            throw new RockException("The time of a deadline cannot be empty.");
        }
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
            getDeadline = DateFormatter.formatDate(getDeadline);
            Deadline deadline = new Deadline(getName, getDeadline);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + deadline.getStatus());

            tasks.add(deadline);

            response.addNewLines("Now you have " + getSize() + " tasks in the list.");
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information on event.
     * @param cmd User's command
     * @return List of (Name, Time)
     * @throws RockException
     */
    private ArrayList<String> parseEvent(String cmd) throws RockException {
        String getName = "";
        String getTime = "";
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.startsWith(CommandTag.AT_TAG, i)) {
                getName = cmd.substring(CommandTag.EVENT.length(), i).trim();
                getTime = cmd.substring(i + CommandTag.AT_TAG.length()).trim();
                break;
            }
        }
        if (getName.length() == 0) {
            throw new RockException("The description of a event cannot be empty.");
        }
        if (getTime.length() == 0) {
            throw new RockException("The time of a event cannot be empty.");
        }
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
            getTime = DateFormatter.formatDate(getTime);
            Event event = new Event(getName, getTime);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + event.getStatus());

            tasks.add(event);

            response.addNewLines("Now you have " + getSize() + " tasks in the list.");
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information on delete.
     * @param cmd User command
     * @return Index
     * @throws RockException
     */
    private int parseDelete(String cmd) throws RockException {
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
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information on find.
     * @param cmd User command
     * @return Pattern needed
     * @throws RockException
     */
    private String parseFind(String cmd) throws RockException {
        String pattern = cmd.substring(CommandTag.FIND.length()).trim();
        if (pattern.length() == 0) {
            throw new RockException("The pattern of a find cannot be empty.");
        }
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
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }

    /**
     * get information on dowithin
     * @param cmd
     * @return List of (Name, from, to)
     * @throws RockException
     */
    private ArrayList<String> parseDoWithin(String cmd) throws RockException {
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
        if (getName.length() == 0) {
            throw new RockException("The description of a dowithin cannot be empty.");
        }
        if (from.length() == 0) {
            throw new RockException("The start time of a dowithin cannot be empty.");
        }
        if (to.length() == 0) {
            throw new RockException("The end time of a dowithin cannot be empty.");
        }
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
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }
}

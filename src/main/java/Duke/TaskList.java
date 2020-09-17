package duke;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of the todo events.
 */
public class TaskList {
    protected List<Task> data;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        data = new ArrayList<Task>();
    }

    /**
     * Constructs an task list with given data.
     * @param data the existing data needed to initialize the task list.
     */
    public TaskList(List<Task> data) {
        this.data = data;
    }

    /**
     * Returns the task list.
     * @return the task list.
     */
    public List<Task> getData() {
        return data;
    }

    /**
     * Sets the status of a certain event to done.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String done(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 5) {
            throw new DukeException("      OOPS!!! The description of done is incomplete.");
        }
        int n = Integer.parseInt(input.substring(5)) - 1;
        if (n > data.size() || n < 0) {
            throw new DukeException("      OOPS!!! The index is out of bounds.");
        }
        data.get(n).done();
        res = ui.printDone(data, n);
        storage.writeFile(data);
        return res;
    }

    /**
     * Deletes a certain event from the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.

     */
    public String delete(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 7) {
            throw new DukeException("      OOPS!!! The description of delete is incomplete.");
        }
        int n = Integer.parseInt(input.substring(7)) - 1;
        if (n > data.size() || n < 0) {
            throw new DukeException("      OOPS!!! The index is out of bounds.");
        }
        res = ui.printDeletePre(data, n);
        data.remove(n);
        res += ui.printDeletePost(data, n);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds a duration task into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String durationTask(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res = "";
        if (input.length() <= 13) {
            throw new DukeException("      OOPS!!! The description of a duration task cannot be empty.");
        }
        String[] ss = input.split(" ");
        if (ss.length != 3 || ss[2].length() != 2) {
            throw new DukeException("      OOPS!!! Please following the format: durationtask XXX HH");
        }
        DurationTask t = new DurationTask(ss[1], Integer.parseInt(ss[2]));
        data.add(t);
        res = ui.printTask(data, t);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds a todo event into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String todo(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 5) {
            // Exception: eg. todo
            throw new DukeException("      OOPS!!! The description of a todo cannot be empty.");
        }
        Todo task = new Todo(input.substring(5));
        data.add(task);
        res = ui.printTask(data, task);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds an event with a deadline into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String deadline(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 9) {
            throw new DukeException("      OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] ss = input.split("/");
        if (ss.length != 4 || !ss[1].startsWith("by ")) {
            throw new DukeException("      OOPS!!! Please following the format: \n"
                    + "      deadline XXX /by DD/MM/YYYY HHMM");
        }
        int year = Integer.parseInt(ss[3].split(" ")[0]);
        int month = Integer.parseInt(ss[2]);
        int day = Integer.parseInt(ss[1].substring(3));
        String[] time = ss[3].split(" ");
        if (time.length == 1 || time[1].length() != 4) {
            throw new DukeException("      OOPS!!! Please enter the time in correct format: HHMM");
        }
        int hour = Integer.parseInt(ss[3].split(" ")[1].substring(0, 2));
        int minute = Integer.parseInt(ss[3].split(" ")[1].substring(2));
        Deadline task = new Deadline(ss[0].substring(9), LocalDateTime.of(year, month, day, hour, minute));
        data.add(task);
        res = ui.printTask(data, task);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds an event that will happen at a fixed time into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String event(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 6) {
            throw new DukeException("      OOPS!!! The description of an event cannot be empty.");
        }
        String[] ss = input.split("/");
        if (ss.length != 4 || !ss[1].startsWith("at ")) {
            // Exception: eg. event meeting /Mon
            throw new DukeException("      OOPS!!! Please following the format: \n"
                    + "      event XXX /at DD/MM/YYYY HHMM");
        }
        int year = Integer.parseInt(ss[3].split(" ")[0]);
        int month = Integer.parseInt(ss[2]);
        int day = Integer.parseInt(ss[1].substring(3));
        String[] time = ss[3].split(" ");
        if (time.length == 1 || time[1].length() != 4) {
            throw new DukeException("      OOPS!!! Please enter the time in correct format: HHMM");
        }
        int hour = Integer.parseInt(ss[3].split(" ")[1].substring(0, 2));
        int minute = Integer.parseInt(ss[3].split(" ")[1].substring(2));
        Event task = new Event(ss[0].substring(6), LocalDateTime.of(year, month, day, hour, minute));
        data.add(task);
        res = ui.printTask(data, task);
        storage.writeFile(data);
        return res;
    }

    /**
     * Finds all the tasks that contains the keyword.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @return String result for GUI.
     */
    public String find(String input, Ui ui) {
        String res;
        String keyword = input.substring(5);
        List<Task> filteredData = new ArrayList<>();
        for (Task task : data) {
            findMatch(task, filteredData, keyword);
        }
        res = ui.printFind(filteredData);
        return res;
    }

    /**
     * find all the results that match the keyword.
     * @param task is a particular task.
     * @param filteredData stores the matched result.
     * @param keyword is the searching keyword.
     */
    private void findMatch(Task task, List<Task> filteredData, String keyword) {
        if (task.getDescription().contains(keyword)) {
            filteredData.add(task);
        }
    }
}


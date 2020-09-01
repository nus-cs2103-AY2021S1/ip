package Duke;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

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
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     * @return String result for GUI
     */
    public String done(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 5) {
            // Exception: eg. done
            throw new DukeException("      OOPS!!! The description of done is incomplete.");
        }
        int n = Integer.parseInt(input.substring(5)) - 1;
        if (n > data.size() || n < 0) {
            //Exception: eg. done 999
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
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     * @return String result for GUI
     */
    public String delete(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 7) {
            // Exception: eg. delete
            throw new DukeException("      OOPS!!! The description of delete is incomplete.");
        }
        int n = Integer.parseInt(input.substring(7)) - 1;
        if (n > data.size() || n < 0) {
            //Exception: eg. delete 999
            throw new DukeException("      OOPS!!! The index is out of bounds.");
        }
        res = ui.printDeletePre(data, n);
        data.remove(n);
        res += ui.printDeletePost(data, n);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds a todo event into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     * @return String result for GUI
     */
    public String todo(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 5) {
            // Exception: eg. todo
            throw new DukeException("      OOPS!!! The description of a todo cannot be empty.");
        }
        Todo t = new Todo(input.substring(5));
        data.add(t);
        res = ui.printTodo(data, t);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds an event with a deadline into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     * @return String result for GUI
     */
    public String deadline(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 9) {
            // Exception: eg. deadline
            throw new DukeException("      OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] ss = input.split("/");
        if (ss.length != 4 || !ss[1].startsWith("by ")) {
            throw new DukeException("      OOPS!!! Please following the format: deadline XXX /by X/X/XXXX XXXX");
        }
        Deadline t = new Deadline(ss[0].substring(9),
                LocalDateTime.of(Integer.parseInt(ss[3].split(" ")[0]), Integer.parseInt(ss[2]),
                        Integer.parseInt(ss[1].substring(3)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(0, 2)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(2))));
        data.add(t);
        res = ui.printDeadline(data, t);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds an event that will happen at a fixed time into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     * @return String result for GUI
     */
    public String event(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (input.length() <= 9) {
            // Exception: eg. event
            throw new DukeException("      OOPS!!! The description of an event cannot be empty.");
        }
        String[] ss = input.split("/");
        if (ss.length != 4 || !ss[1].startsWith("at ")) {
            // Exception: eg. event meeting /Mon
            throw new DukeException("\"      OOPS!!! Please following the format: event XXX /at X/X/XXXX XXXX");
        }
        Event t = new Event(ss[0].substring(6),
                LocalDateTime.of(Integer.parseInt(ss[3].split(" ")[0]), Integer.parseInt(ss[2]),
                        Integer.parseInt(ss[1].substring(3)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(0, 2)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(2))));
        System.out.println(t.getDescription());
        data.add(t);
        res = ui.printEvent(data, t);
        storage.writeFile(data);
        return res;
    }

    /**
     * Finds all the tasks that contains the keyword.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @return String result for GUI
     */
    public String find(String input, Ui ui) {
        String res;
        String keyword = input.substring(5);
        List<Task> filteredData = new ArrayList<>();
        for(Task t : data) {
            if(t.getDescription().contains(keyword)) {
                filteredData.add(t);
            }
        }
        res = ui.printFind(filteredData);
        return res;
    }
}


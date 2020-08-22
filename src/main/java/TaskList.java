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
        data = new ArrayList();
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
     */
    public void done(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 5) {
            // Exception: eg. done
            throw new DukeException("     ☹ OOPS!!! The description of done is incomplete.");
        }
        int n = Integer.parseInt(input.substring(5)) - 1;
        if (n > data.size() || n < 0) {
            //Exception: eg. done 999
            throw new DukeException("     ☹ OOPS!!! The index is out of bounds.");
        }
        data.get(n).done();
        ui.printDone(data, n);
        storage.writeFile(data);
    }

    /**
     * Deletes a certain event from the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public void delete(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 7) {
            // Exception: eg. delete
            throw new DukeException("     ☹ OOPS!!! The description of delete is incomplete.");
        }
        int n = Integer.parseInt(input.substring(7)) - 1;
        if (n > data.size() || n < 0) {
            //Exception: eg. delete 999
            throw new DukeException("     ☹ OOPS!!! The index is out of bounds.");
        }
        ui.printDeletePre(data, n);
        data.remove(n);
        ui.printDeletePost(data, n);
        storage.writeFile(data);
    }

    /**
     * Adds a todo event into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public void todo(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 5) {
            // Exception: eg. todo
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo t = new Todo(input.substring(5));
        data.add(t);
        ui.printTodo(data, t);
        storage.writeFile(data);
    }

    /**
     * Adds an event with a deadline into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public void deadline(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 9) {
            // Exception: eg. deadline
            throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] ss = input.split("/");
        if (!ss[1].startsWith("by ")) {
            // Exception: eg. deadline do homework /Mon
            throw new DukeException("     ☹ OOPS!!! Please enter the time following the format: by XXX");
        }
        Deadline t = new Deadline(ss[0].substring(9),
                LocalDateTime.of(Integer.parseInt(ss[3].split(" ")[0]), Integer.parseInt(ss[2]),
                        Integer.parseInt(ss[1].substring(3)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(0, 2)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(2))));
        data.add(t);
        ui.printDeadline(data, t);
        storage.writeFile(data);
    }

    /**
     * Adds an event that will happen at a fixed time into the list.
     * @param input the input from the user.
     * @param ui user interaction object.
     * @param storage storage object.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public void event(String input, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (input.length() <= 9) {
            // Exception: eg. event
            throw new DukeException("     ☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] ss = input.split("/");
        if (!ss[1].startsWith("at ")) {
            // Exception: eg. event meeting /Mon
            throw new DukeException("     ☹ OOPS!!! Please enter the time following the format: at XXX");
        }
        Event t = new Event(ss[0].substring(6),
                LocalDateTime.of(Integer.parseInt(ss[3].split(" ")[0]), Integer.parseInt(ss[2]),
                        Integer.parseInt(ss[1].substring(3)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(0, 2)),
                        Integer.parseInt(ss[3].split(" ")[1].substring(2))));
        System.out.println(t.getDescription());
        data.add(t);
        ui.printEvent(data, t);
        storage.writeFile(data);
    }

    /**
     * Finds all the tasks that contains the keyword.
     * @param input the input from the user.
     * @param ui user interaction object.
     */
    public void find(String input, Ui ui) {
        String keyword = input.substring(5);
        List<Task> filteredData = new ArrayList<>();
        for(Task t : data) {
            if(t.getDescription().contains(keyword)) {
                filteredData.add(t);
            }
        }
        ui.printFind(filteredData);
    }
}


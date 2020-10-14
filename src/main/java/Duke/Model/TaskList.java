package duke.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents the list of tasks.
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
     * @param n is the number of which task will be marked done.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String done(int n, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (n < 0 || n >= data.size()) {
            throw new DukeException("      OOPS!!! The index is out of bounds.");
        }
        data.get(n).done();
        res = ui.printDone(data, n);
        storage.writeFile(data);
        return res;
    }

    /**
     * Deletes a certain event from the list.
     * @param n is the number which task will be deleted.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws FileNotFoundException handles the exception of file not found.

     */
    public String delete(int n, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        String res;
        if (n < 0 || n >= data.size()) {
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
     * @param t is a DurationTask object.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String durationTask(DurationTask t, Ui ui, Storage storage) throws FileNotFoundException {
        String res = "";
        data.add(t);
        res = ui.printTask(data, t);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds a todo event into the list.
     * @param task is a Todo object.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String todo(Todo task, Ui ui, Storage storage) throws FileNotFoundException {
        String res;
        data.add(task);
        res = ui.printTask(data, task);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds an event with a deadline into the list.
     * @param task is a Deadline object.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String deadline(Deadline task, Ui ui, Storage storage) throws FileNotFoundException {
        String res;
        data.add(task);
        res = ui.printTask(data, task);
        storage.writeFile(data);
        return res;
    }

    /**
     * Adds an event that will happen at a fixed time into the list.
     * @param task is an Event object.
     * @param ui user interaction object.
     * @param storage storage object.
     * @return String result for GUI.
     * @throws FileNotFoundException handles the exception of file not found.
     */
    public String event(Event task, Ui ui, Storage storage) throws FileNotFoundException {
        String res;
        data.add(task);
        res = ui.printTask(data, task);
        storage.writeFile(data);
        return res;
    }

    /**
     * Finds all the tasks that contains the keyword.
     * @param keyword is the searching keyword.
     * @param ui user interaction object.
     * @return String result for GUI.
     * @throws DukeException handles the exception when running the Duke bot.
     */
    public String find(String keyword, Ui ui) throws DukeException {
        String res = "";
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


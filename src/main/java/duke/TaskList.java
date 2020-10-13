package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;

/**
 * TaskList stores the list of tasks and modifies the list according to commands.
 */
public class TaskList {

    public ArrayList<Task> list;

    /**
     * Default constructor that initialises an empty ArrayList
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Initialises an Arraylist of Tasks if there is saved data present.
     * @param savedData an ArrayList of tasks
     */
    public TaskList(ArrayList<Task> savedData) {
        this.list = savedData;
    }

    /**
     * Adds a new subclass of duke.task.Task based on a String input.
     * @param input Command from user
     * @return a duke.task.Task that is either a duke.task.To-Do, a duke.task.Deadline or an duke.task.Event.
     * @throws DukeException if command is invalid due to missing information.
     */
    public Task addItem(String input) throws DukeException {
        assert input.length() >= 4;
        String[] arr = input.split(" ", 2);
        Task curr = new Task("");
        if (arr.length == 1) {
            throw DukeException.INVALID_TASK_EXCEPTION;
        } else if (arr[0].equals("todo")) {
            curr = addToDo(arr[1].trim());
        } else if (arr[0].equals("deadline")) {
            curr = addDeadline(arr[1].trim());
        } else if (arr[0].equals("event")) {
            curr = addEvent(arr[1].trim());
        }
        return curr;
    }

    /**
     * Adds a {@code To-do} to the TaskList.
     * @param description Description of the task.
     * @return {@code Task} object.
     */
    public Task addToDo(String description) {
        Task toAdd = new ToDo(description);
        list.add(toAdd);
        return toAdd;
    }

    /**
     * Adds a {@code Deadline} to the TaskList.
     * @param info Description and Date of the Deadline.
     * @return {@code Deadline} object.
     * @throws DukeException if deadline is not provided.
     */
    public Task addDeadline(String info) throws DukeException {
        String[] curr = info.split("/by ", 2);
        if (curr.length == 1) {
            throw DukeException.INVALID_DEADLINE_EXCEPTION;
        } else {
            Task toAdd = new Deadline(curr[0].trim(), Parser.dateParser(curr[1]));
            list.add(toAdd);
            return toAdd;
        }
    }

    /**
     * Adds a {@code Event} to the TaskList.
     * @param info Description, Date and Time of the Event.
     * @return {@code Event} object.
     * @throws DukeException if Date and/or Time is not provided.
     */
    public Task addEvent(String info) throws DukeException {
        String[] curr = info.split("/at ", 2);
        if (curr.length == 1) {
            throw DukeException.INVALID_TIME_EXCEPTION;
        } else {
            String[] t = curr[1].split(" ", 2);
            try {
                if (t.length < 2) {
                    throw DukeException.INVALID_TIME_EXCEPTION;
                }
                LocalDate date = Parser.dateParser(t[0]);
                Task toAdd = new Event(curr[0].trim(), date, t[1]);
                list.add(toAdd);
                return toAdd;
            } catch (DukeException | NumberFormatException ex1) {
                throw DukeException.INVALID_TIME_EXCEPTION;
            }
        }
    }

    /**
     * Deletes an item based on the number
     * @param input Command from user
     * @return the duke.task.Task that was deleted
     * @throws DukeException Throws exception if command is invalid due to missing or wrong information.
     */
    public Task deleteItem(String input) throws DukeException{
        assert input.length() >= 6;
        String[] info = input.split(" ", 2);
        Task toBeDeleted = new Task("");
        if (info.length == 1) {
            throw DukeException.INVALID_INDEX_EXCEPTION;
        } else {
            try {
                int index = Integer.parseInt(info[1]);
                if (index > list.size() || index <= 0) {
                    throw DukeException.INVALID_INDEX_EXCEPTION;
                } else {
                    toBeDeleted = list.get(index - 1);
                    list.remove(index - 1);
                    return toBeDeleted;
                }
            } catch (NumberFormatException ex1){
                throw DukeException.INVALID_INDEX_EXCEPTION;
            }
        }
    }

    /**
     * Marks an item as done based on the number
     * @param input Command from user
     * @return the duke.task.Task that was marked as done after it has been marked.
     * @throws DukeException Throws exception if command is invalid due to missing or wrong information.
     */
    public Task doneItem(String input) throws DukeException {
        assert input.length() >= 4;
        String[] info = input.split(" ", 2);
        Task toBeRet = new Task("");
        if (info.length == 1) {
            throw DukeException.INVALID_INDEX_EXCEPTION;
        } else {
            try {
                int index = Integer.parseInt(info[1]);
                if (index > list.size() || index <= 0) {
                    throw DukeException.INVALID_INDEX_EXCEPTION;
                } else {
                    list.get(index - 1).markAsDone();
                    toBeRet = list.get(index - 1);
                }
            } catch (NumberFormatException ex1) {
                throw DukeException.INVALID_INDEX_EXCEPTION;
            }
        }
        return toBeRet;
    }

    /**
     * Finds tasks that contains the string input as part of the task description
     * @param input word to be found in task descriptions
     * @return ArrayList of Tasks that contain the required word
     * @throws DukeException Throws an exception if query is invalid due to missing or wrong information.
     */
    public ArrayList<Task> find(String input) throws DukeException{
        assert input.length() >= 4;
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw DukeException.INVALID_QUERY_EXCEPTION;
        } else {
            ArrayList<Task> ret = new ArrayList<>();
            for (Task k : list) {
                if (k.getDescription().contains(info[1])) {
                    ret.add(k);
                }
            }
            return ret;
        }
    }

    /**
     * Sorts the current list by type of tasks, with To-do items listed first, followed by Deadlines then Events.
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> sortByType() {
        ArrayList<Task> todos = new ArrayList<>();
        ArrayList<Task> deadlines = new ArrayList<>();
        ArrayList<Task> events = new ArrayList<>();

        for (Task k: list) {
            switch (k.getType()) {
                case "Todo":
                    todos.add(k);
                    break;
                case "Event":
                    events.add(k);
                    break;
                case "Deadline":
                    deadlines.add(k);
                    break;
            }
        }
        todos.addAll(deadlines);
        todos.addAll(events);
        list = todos;
        return getList();
    }

    /**
     * Sorts the current list by date due, with undated To-do items listed first.
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> sortByDate() {
        ArrayList<Task> ret = new ArrayList<>(list);
        Collections.sort(ret);
        list = ret;
        return getList();
    }

    /**
     * Returns the number of tasks on the list.
     * @return integer representing the number of tasks on the list
     */
    public int getListSize() {
        return this.list.size();
    }

    public ArrayList<Task> clear() {
        list = new ArrayList<>();
        return getList();
    }
    /**
     * Returns the current list of Tasks.
     * @return an ArrayList of Tasks
     */
    public ArrayList<Task> getList() {
        return list;
    }

}

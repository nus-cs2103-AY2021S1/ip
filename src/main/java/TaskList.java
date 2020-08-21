import java.util.ArrayList;
import java.util.List;

public class TaskList {
    static final String INDENT = "    ";
    static final String UNKNOWN_TASK_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that meows :-(";
    static final String EMPTY_BY_ERROR = "☹ OOPS!!! The deadline cannot be empty.";
    static final String EMPTY_AT_ERROR = "☹ OOPS!!! The event time cannot be empty.";
    static final String NO_SUCH_TASK = "☹ OOPS!!! There is no such task.";
    static final String EMPTY_TASK_INDEX = "☹ OOPS!!! The task index cannot be empty.";
    static final String EMPTY_DESCRIPTION_ERROR(String task) {
        return String.format("☹ OOPS!!! The description of %s cannot be empty.", task);
    }

    protected List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.list = taskList;
    }

    public Task addTask(String t) throws DukeEmptyDescriptionException,
            DukeEmptyByException, DukeEmptyAtException,
            DukeUnknownInputException{
        Task toBeAdded;
        String des;
        String[] tokens;
        if (t.startsWith("todo")) {
            try {
                des = t.substring(5);
                toBeAdded = new Todo(des);
                list.add(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException(EMPTY_DESCRIPTION_ERROR("todo"));
            }
        } else if (t.startsWith("deadline")) {
            tokens = t.split(" /by ");
            try {
                des = tokens[0].substring(9);
                toBeAdded = new Deadline(des, tokens[1]);
                list.add(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException(EMPTY_DESCRIPTION_ERROR("deadline"));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyByException(EMPTY_BY_ERROR);
            }
        } else if (t.startsWith("event")) {
            try {
                tokens = t.split(" /at ");
                des = tokens[0].substring(6);
                toBeAdded = new Event(des, tokens[1]);
                list.add(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException(EMPTY_DESCRIPTION_ERROR("event"));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyAtException(EMPTY_AT_ERROR);
            }
        } else {
            throw new DukeUnknownInputException(UNKNOWN_TASK_ERROR);
        }
        return toBeAdded;
    }

    public Task markDone(String md) throws DukeInvalidDoneIndexException, DukeEmptyDoneIndexException {
        int index;
        try {
            String[] tokens = md.split(" ");
            index = Integer.parseInt(tokens[1]);
            Task task = list.get(index - 1);
            task.markAsDone();
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyDoneIndexException(EMPTY_TASK_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidDoneIndexException(NO_SUCH_TASK);
        }
    }

    public Task deleteTask(String dt) throws DukeInvalidDeleteIndexException, DukeEmptyDeleteIndexException {
        int index;
        try {
            String[] tokens = dt.split(" ");
            index = Integer.parseInt(tokens[1]);
            Task removedTask = list.remove(index - 1);
            return removedTask;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyDeleteIndexException(EMPTY_TASK_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidDeleteIndexException(NO_SUCH_TASK);
        }
    }

    public static String getListAsStringFromList(List<Task>  list, String alternative) {
        String s = "";
        for (int i = 0; i < list.size(); i ++) {
            s += (i + 1) + "." + list.get(i);
            if (i != list.size() - 1) {
                s += '\n' + INDENT;
            }
        }
        if (s.equals("")) {
            return alternative;
        } else {
            return s;
        }
    }

    public String getListAsString() {
        return getListAsStringFromList(this.list, "There is nothing in the list!");
    }

    public int getSize() {
        return list.size();
    }


}

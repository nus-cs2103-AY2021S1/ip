package duke.task;

import duke.Duke;
import duke.DukeException;
import duke.datetime.DateTimeFormat;
import duke.datetime.DateTimeUtility;

import java.time.DateTimeException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor to initialise taskList with tasks loaded from an existing log file.
     *
     * @param tasksStr
     * @throws DukeException
     */
    public TaskList(ArrayList<String> tasksStr) throws DukeException {
        assert !tasksStr.isEmpty() : "tasksStr cannot be empty";

        this.tasks = new ArrayList<>();
        for (String taskStr : tasksStr) {
            this.tasks.add(TaskList.parseTaskFromString(taskStr));
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public String getTaskByIdx(int idx) {
        return tasks.get(idx).toString();
    }

    /**
     * Returns a string describing all the tasks in the taskList with a valid
     * Date or DateTime that is before the Date or DateTime specified.
     *
     * Performs input validation on the input Date or DateTime string and throws a
     * DukeException if it is of an invalid format.
     *
     * @param by
     * @return
     * @throws DukeException
     */
    public String filterTasksByDate(String by) throws DukeException {
        assert !by.isEmpty() : "by cannot be empty";

        if (DateTimeUtility.checkDateTimeType(by) != DateTimeFormat.String) {
            ArrayList<Task> filtered = new ArrayList<>();

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) instanceof TimedTask) {
                    String taskBy = ((TimedTask)tasks.get(i)).getByString();
                    try {
                        if (DateTimeUtility.compare(by, taskBy) >= 0) {
                            filtered.add(tasks.get(i));
                        }
                    } catch (DateTimeException e) {}
                }
            }

            return tasks2String(filtered);

        } else {
            throw new DukeException("U NID 2 GIV CORRECT DATE FOMAT!");
        }
    }

    public String filterTasksByDescription(String description) {
        assert !description.isEmpty() : "description cannot be empty";
        ArrayList<Task> filtered = new ArrayList<>();
        String descriptionLower = description.toLowerCase();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(descriptionLower)) {
                filtered.add(tasks.get(i));
            }
        }

        return tasks2String(filtered);
    }

    /**
     * Adds a TODO task to the existing taskList. The task is marked as undone by default.
     * Returns a string representation of the added task.
     *
     * @param name
     * @return
     */
    public String addTodo(String name) {
        assert !name.isEmpty() : "name cannot be empty";

        Todo newTask = new Todo(name);
        tasks.add(newTask);
        return newTask.toString();
    }

    /**
     * Adds a Deadline task to the existing taskList. The task is marked as undone by default.
     * Returns a string representation of the added task.
     *
     * @param name
     * @return
     */
    public String addDeadline(String name, String by) {
        assert !name.isEmpty() : "name cannot be empty";
        assert !by.isEmpty() : "by cannot be empty";

        Deadline newTask = new Deadline(name, by);
        tasks.add(newTask);
        return newTask.toString();
    }

    /**
     * Adds an Event task to the existing taskList. The task is marked as undone by default.
     * Returns a string representation of the added task.
     *
     * @param name
     * @return
     */
    public String addEvent(String name, String by) {
        assert !name.isEmpty() : "name cannot be empty";
        assert !by.isEmpty() : "by cannot be empty";

        Event newTask = new Event(name, by);
        tasks.add(newTask);
        return newTask.toString();
    }

    /**
     * Marks an existing task in the taskList as done, accessed by index. Performs input
     * validation on the task index and throws a DukeException if the input is invalid.
     *
     * @param idx
     * @throws DukeException
     */
    public void markTaskAsDone(int idx) throws DukeException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DukeException("U DOAN HAS TASK WIF DIS LABEL");
        } else {
            tasks.get(idx).setDone(true);
        }
    }

    /**
     * Updates an existing task in the taskList with a new description, accessed by index. Performs input
     * validation on the task index and throws a DukeException if the input is invalid.
     *
     * @param idx
     * @param newDescription
     * @throws DukeException
     */
    public String updateTaskDescription(int idx, String newDescription) throws DukeException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DukeException("U DOAN HAS TASK WIF DIS LABEL");
        } else {
            tasks.get(idx).setDescription(newDescription);
            return tasks.get(idx).toString();
        }
    }

    /**
     * Updates an existing task in the tasklist with a new date, accessed by index. Performs input
     * validation on the task index and date and throws a DukeException if the input index is invalid,
     * or if the task does not take in a date.
     *
     * @param idx
     * @param newDate
     * @throws DukeException
     */

    public String updateTaskDate(int idx, String newDate) throws DukeException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DukeException("U DOAN HAS TASK WIF DIS LABEL");
        } else if (!(tasks.get(idx) instanceof TimedTask)) {
            throw new DukeException("DIS TASK GOTS NO DATE FER UPDATE LULZ");
        } else {
            ((TimedTask)tasks.get(idx)).setByString(newDate);
            return tasks.get(idx).toString();
        }
    }


    /**
     * Removes an existing task from the taskList by index and returns its string representation.
     * Performs input validation on the specified index and throws a DukeException when index
     * is invalid.
     *
     * @param idx
     * @return
     * @throws DukeException
     */
    public String popTask(int idx) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("U CANT DELET ANYTHIN COZ U HAS NO TASKZ NAO LOLOL");
        }
        if (idx < 0 || idx >= tasks.size()) {
            throw new DukeException("U DOAN HAS TASK WIF DIS LABEL");
        } else {
            return tasks.remove(idx).toString();
        }
    }

    /**
     * Parses an input string and returns a Task of the correct type, name, status and
     * deadline (if available), otherwise throws a DukeException if input string is
     * of the wrong format.
     *
     * @param str
     * @return
     * @throws DukeException
     */
    public static Task parseTaskFromString(String str) throws DukeException {
        assert !str.isEmpty() : "str cannot be empty";

        Task task;
        String[] temp = str.split("]");
        String taskType = String.valueOf(temp[0].charAt(1));
        boolean isDone = (String.valueOf(temp[1].charAt(1)).equals(Task.ICON_TICK));

        if (taskType.equals(Todo.TASK_ICON)) {
            task = new Todo(temp[2].trim());
        } else if (taskType.equals(Deadline.TASK_ICON)) {
            String taskName = temp[2].split("\\(by:")[0].trim();
            String by = temp[2].split(" \\(by:")[1].trim();
            by = by.substring(0, by.length()-1);
            task = new Deadline(taskName, by);
        } else if (taskType.equals(Event.TASK_ICON)) {
            String taskName = temp[2].split(" \\(at:")[0].trim();
            String by = temp[2].split(" \\(at:")[1].trim();
            by = by.substring(0, by.length()-1);
            task = new Event(taskName, by);
        } else {
            throw new DukeException("TASK ICON NOT RECOGNISED");
        }

        task.setDone(isDone);
        return task;
    }

    private static String tasks2String(ArrayList<Task> tasks) {
        String ret = "";
        for (int i = 0; i < tasks.size(); i++) {
            ret += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return ret;
    }

    @Override
    public String toString() {
        return TaskList.tasks2String(this.tasks);

    }
}

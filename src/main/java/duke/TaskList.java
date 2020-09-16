package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A list of Task object and methods that deal with modification to the list of tasks.
 */

public class TaskList {

    private static ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * updating the taskList from the database if file is found.
     * @param listOfTask to be created.
     */
    TaskList(List<String> listOfTask) {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < listOfTask.size(); i++) {
            addTaskFromFile(listOfTask.get(i), tasks);
        }
    }

    public static Task prioritise(String index) {
        int i = Integer.parseInt(index);
        Task target = tasks.get(i - 1);
        target.setPriority(Task.Priority.HIGH);
        return target;
    }

    /**
     * method to mark a task in the list as done.
     * @param remain index of the task in list.
     * @return task specified.
     */

    public static Task doneTask(String remain) throws DukeException {
        assert Integer.parseInt(remain) > 0;
        try {
            int index = Integer.parseInt(remain);
            Task task = tasks.get(index - 1);
            task.markAsDone();
            return task;
        } catch (AssertionError e) {
            throw new DukeException("command not found");
        }
    }

    /**
     * method to add a todo item into the list.
     * @param command description of a todo.
     * @return a new todo task.
     */

    public static Task createTodo(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException("todo");
        } else {
            tasks.add(new Todo(command));
            return new Todo(command);
        }
    }

    /**
     * method to add an Event into the list.
     * @param command description of an event.
     * @return a new event task.
     */

    public static Task createEvent(String command) throws DukeException {
        try {
            String description = command.split(" /at ", 2)[0];
            String at = command.split(" /at ", 2)[1].replace(" ", "");
            tasks.add(new Event(description, at));
            return new Event(description, at);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("event");
        } catch (DateTimeParseException e2) {
            throw new DukeException("time");
        }
    }

    /**
     * method to add a deadline into the list.
     * @param command description of an event.
     * @return a new deadline task.
     */
    public static Task createDeadline(String command) {
        try {
            String description = command.split("/by ", 2)[0];
            String by = command.split("/by ", 2)[1].replace(" ", "");
            tasks.add(new Deadline(description, by));
            return new Deadline(description, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.dukeException(new DukeException("deadline"));
        } catch (DateTimeParseException e2) {
            Ui.dukeException(new DukeException("time"));
        }
        return null;
    }

    /**
     * Method to recall the list of tasks.
     * @return list of task.
     */
    public List<Task> getTaskList() {
        Collections.sort(tasks);
        return tasks;
    }

    /**
     * Void method to add a task in string format from file.
     * @param task to be added to arraylist of tasks.
     * @param store the arraylist of tasks stored.
     */
    public static void addTaskFromFile(String task, ArrayList<Task> store) {
        String taskFormat = task.split(" #", 2)[0];
        String priority = task.split(" #", 2)[1];
        String typeOfTask = taskFormat.split(" ", 2)[0];
        String remain = taskFormat.split(" ", 2)[1];
        String isDone = remain.split(" ", 2)[0];
        String title = remain.split(" ", 2)[1];

        Task newTask;
        if (typeOfTask.isEmpty()) {
            return;
        }
        if (typeOfTask.equals("todo")) {
            newTask = new Todo(title, Boolean.valueOf(isDone));
            newTask.setPriority(Task.Priority.valueOf(priority));
            store.add(newTask);
        } else {
            String description = title.split("/", 2)[0];

            if (typeOfTask.equals("deadline")) {
                String by = title.split("/by ", 2)[1];
                newTask = new Deadline(description, by, Boolean.valueOf(isDone));
                newTask.setPriority(Task.Priority.valueOf(priority));
                store.add(newTask);
            }
            if (typeOfTask.equals("event")) {
                String at = title.split("/at ", 2)[1];
                newTask = new Event(description, at, Boolean.valueOf(isDone));
                newTask.setPriority(Task.Priority.valueOf(priority));
                store.add(newTask);
            }
        }
    }

    /**
     * Void method to search for a task with a given keyword.
     * @param name of the keyword.
     * @return list of tasks with specified keyword in string format.
     */
    public static List<Task> findTask(String name) {
        List<Task> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).findTask(name)) {
                result.add(tasks.get(i));
            }
        }
        return result;
    }

    /**
     * method to delete a specific task at the index of the list.
     * @param command the specified index.
     */

    public static Task deleteTask(String command) {
        assert Integer.parseInt(command) > 0;
        int index = Integer.parseInt(command);
        Task k = tasks.get(index - 1);
        tasks.remove(k);
        return k;
    }

    /**
     * Method to get the size of the task list.
     * @return size of list.
     */
    public int getSize() {
        return tasks.size();
    }
}

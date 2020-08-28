package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
 
/**
 * Contains task list, and has operations like list and delete.
 */
public class TaskList {
    
    /** List of tasks. */
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor used to create list of tasks.
     */
    public TaskList() {
    }

    /**
     * Constructor used to create list of tasks.
     *
     * @param taskStrings List of tasks in string format.
     * @throws DukeException  When date is in wrong format.
     */
    public TaskList(ArrayList<ArrayList<String>> taskStrings) throws DukeException {
        //TODO: Create Enum for this, or just store as Deadline, Event, Todo

        for (ArrayList<String> taskString : taskStrings) {

            int isDoneInt = Integer.parseInt(taskString.get(1));
            boolean isDone = isDoneInt > 0;
            switch (taskString.get(0)) {
            case "D":
                handleDeadline(taskString.get(2), taskString.get(3), isDone);
                break;
            case "E":
                handleEvent(taskString.get(2), taskString.get(3), isDone);
                break;
            case "T":
                handleTodo(taskString.get(2), isDone);
                break;
            }
        }
    }

    /**
     * Getter to retrieve list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public String addTask(CommandName commandName, String description, String date) throws DukeException {
        Task task;

        switch (commandName) {
        case TODO:
            task = handleTodo(description);
            break;
        case DEADLINE:
            task = handleDeadline(description, date);
            break;
        case EVENT:
            task = handleEvent(description, date);
            break;
        default:
            task = new Task("");
        }

        int len = taskList.size();
        return "     Got it. I've added this task:\n       "
                + task.toString() + "\n     Now you have " + len
                + " tasks in the list.";
    }

    private Task handleTodo(String todoTask) {
        Task todo = new Todo(todoTask);
        taskList.add(todo);
        return todo;
    }

    private void handleTodo(String todoTask, boolean isDone) {
        Task todo = new Todo(todoTask, isDone);
        taskList.add(todo);
    }

    private Task handleDeadline(String deadlineTask, String deadlineBy) throws DukeException {
        try {
            LocalDate deadlineByLocalDate = LocalDate.parse(deadlineBy);
            Task deadline = new Deadline(deadlineTask, deadlineByLocalDate);
            taskList.add(deadline);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new DukeException("     OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private void handleDeadline(String deadlineTask, String deadlineBy, boolean isDone) throws DukeException {
        try {
            LocalDate deadlineByLocalDate = LocalDate.parse(deadlineBy);
            Task deadline = new Deadline(deadlineTask, deadlineByLocalDate, isDone);
            taskList.add(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("     OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private Task handleEvent(String eventTask, String eventAt) throws DukeException {
        try {
            LocalDate eventAtLocalDate = LocalDate.parse(eventAt);
            Task event = new Event(eventTask, eventAtLocalDate);
            taskList.add(event);
            return event;
        } catch (DateTimeParseException e) {
            throw new DukeException("     OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private void handleEvent(String eventTask, String eventAt, boolean isDone) throws DukeException {
        try {
            LocalDate eventAtLocalDate = LocalDate.parse(eventAt);
            Task event = new Event(eventTask, eventAtLocalDate, isDone);
            taskList.add(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("     OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    public String listTasks() {
        StringBuilder output = new StringBuilder("     Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : taskList) {
            output.append("     ").append(index).append(".").append(task.toString()).append("\n");
            index++;
        }
        return output.toString();
    }

    public String doTask(int index) {
        Task task = taskList.get(index - 1);
        task.setDone(true);
        return "     Nice! I've marked this task as done: \n"
                + "       " + task.toString();
    }

    public String deleteTask(int index) {
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        int len = taskList.size();
        return "     Noted. I've removed this task:\n       "
                + task.toString() + "\n     Now you have " + len
                + " tasks in the list.";
    }

    public String findTasks(String search) {
        StringBuilder output = new StringBuilder("     Here are the matching tasks in your list:\n");
        int index = 1;
        for (Task task : taskList) {
            if (task.toString().contains(search)) {
                output.append("     ").append(index).append(".").append(task.toString()).append("\n");
                index++;
            }
        }
        return output.toString();
    }
}

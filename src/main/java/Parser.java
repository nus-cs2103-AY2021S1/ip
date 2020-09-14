import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that takes in user's input and performs tasks using it.
 */
public class Parser {

    /**
     * Makes a todo Task.
     * @param last the description of the task.
     * @param taskList the taskList containing current tasks.
     * @param storage the storage to store tasks in the hard disk
     * @param ui to interact with the user.
     * @return a String containing the message related to a Todo task.
     */
    public static String parseToDo(String last, TaskList taskList, Storage storage, Ui ui) {
        try {
            Todo todo = Todo.makeToDo(last, false);
            taskList.addTask(todo);
            storage.saveTasks(taskList.getTasks());
            return ui.addTask() + todo.toString() + "\n" + ui.showNumberOfTasks(taskList.getTasks());
        } catch (DukeException e) {
            return "Todo cannot be empty";
        }
    }

    /**
     * Makes a deadline Task.
     * @param last the description of the task.
     * @param taskList the taskList containing current tasks.
     * @param storage the storage to store tasks in the hard disk
     * @param ui to interact with the user.
     * @param formatter the datetimeformatter to format the date.
     * @return a String containing the message related to a deadline task.
     * @throws DukeException to indicate that it cannot save tasks.
     */
    public static String parseDeadline(String last, TaskList taskList, Storage storage, Ui ui, DateTimeFormatter formatter) throws DukeException {
        String job = last.split("/by")[0].trim();
        String time = last.split("/by")[1].trim();
        LocalDate date = LocalDate.parse(time);
        Deadline work = new Deadline(job + " (by: " + formatter.format(date) + ")", false, date);
        taskList.addTask(work);
        storage.saveTasks(taskList.getTasks());
        return ui.addTask() + work.toString() + "\n" + ui.showNumberOfTasks(taskList.getTasks());
    }

    /**
     * Makes a event Task.
     * @param last the description of the task.
     * @param taskList the taskList containing current tasks.
     * @param storage the storage to store tasks in the hard disk
     * @param ui to interact with the user.
     * @param formatter the datetimeformatter to format the date.
     * @return a String containing the message related to an event task.
     * @throws DukeException to indicate that it cannot save tasks.
     */
    public static String parseEvent(String last, TaskList taskList, Storage storage, Ui ui, DateTimeFormatter formatter) throws DukeException {
        String job = last.split("/at")[0].trim();
        String time = last.split("/at")[1].trim();
        LocalDate date = LocalDate.parse(time);
        Event work = new Event(job + " (at: " + formatter.format(date) + ")", false, date);
        taskList.addTask(work);
        storage.saveTasks(taskList.getTasks());
        return ui.addTask() + work.toString() + "\n" + ui.showNumberOfTasks(taskList.getTasks());
    }

    /**
     * Marks a task as done and updates the records.
     * @param last the description of the task.
     * @param taskList the taskList containing current tasks.
     * @param storage the storage to store tasks in the hard disk
     * @param ui to interact with the user.
     * @return a String containing the message related to the task that is done.
     * @throws DukeException to indicate that it cannot save tasks.
     */
    public static String parseDone(String last, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        int id = Integer.parseInt(last) - 1;
        String changed = taskList.getTask(id).getDescription();
        String type = taskList.getTask(id).getType();
        taskList.getTask(id).markAsDone();
        storage.saveTasks(taskList.getTasks());
        return ui.doneTask() + "[" + type + "][" + "\u2713" + "]" + changed + "\n";
    }

    /**
     * Marks a task as done and updates the records.
     * @param last the description of the task.
     * @param taskList the taskList containing current tasks.
     * @param storage the storage to store tasks in the hard disk
     * @param ui to interact with the user.
     * @return a String containing the message related to the task that is deleted.
     * @throws DukeException to indicate that it cannot save tasks.
     */
    public static String parseDelete(String last, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        int index = Integer.parseInt(last) - 1;
        String deleted = taskList.getTask(index).getDescription();
        String deletedType = taskList.getTask(index).getType();
        String status = taskList.getTask(index).getStatusIcon();
        Task removedTask = taskList.removeTask(index);
        storage.saveTasks(taskList.getTasks());

        return ui.removeTask() + "[" + deletedType + "][" + status + "] "
                + deleted + "\n" + ui.showNumberOfTasks(taskList.getTasks());
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import exception.DukeErrorException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import ui.Ui;

/**
 * Represents a {@code Tasklist} object to store tasks in memory
 */
public class TaskList {

    /**
     * Read input from String and add new Deadline object to the list
     * @param ui Ui object to print user display
     * @param list The task list
     * @param str The string input
     * @param isNew Boolean value that indicates the task is new or not
     * @param isDone Boolean value that indicates the state of the task
     */
    public String addDeadline(Ui ui, ArrayList<Task> list, String str, boolean isNew, boolean isDone)
            throws InvalidDeadlineException {
        String[] deadline;
        if (isNew) {
            deadline = str.split("/by");
        } else {
            deadline = str.split("on");
        }
        if (deadline.length > 2) {
            throw new InvalidDeadlineException("☹ Task deadline must be written after `/by`.");
        }
        if (deadline[0].equals("")) {
            throw new InvalidDeadlineException("☹ Task description must be specified.");
        }
        if (deadline.length == 1) {
            throw new InvalidDeadlineException("☹ Task deadline must be specified.");
        }
        String description = deadline[0].trim();
        try {
            Deadline curr = new Deadline(description, isDone, LocalDate.parse(deadline[1].trim()));
            list.add(curr);
            if (isNew) {
                return ui.describeTask(list, curr);
            }
        } catch (DateTimeParseException e) {
            return "Invalid Date format! Must be (yyyy-mm-dd).";
        }
        return "";
    }

    /**
     * Read input from String and add new Event object to the list
     * @param ui Ui object to print user display
     * @param list The task list
     * @param str The string input
     * @param isNew Boolean value that indicates the task is new or not
     * @param isDone Boolean value that indicates the state of the task
     */
    public String addEvent(Ui ui, ArrayList<Task> list, String str, boolean isNew, boolean isDone)
            throws InvalidEventException {
        String[] event;
        if (isNew) {
            event = str.split("/at");
        } else {
            event = str.split("on");
        }
        if (event.length > 2) {
            throw new InvalidEventException("☹ Event time must be written after `/at`.");
        }
        if (event[0].equals("")) {
            throw new InvalidEventException("☹ Event description must be specified.");
        }
        if (event.length == 1) {
            throw new InvalidEventException("☹ Event time must be specified.");
        }
        String description = event[0].trim();
        try {
            Event curr = new Event(description, isDone, LocalDate.parse(event[1].trim()));
            list.add(curr);
            if (isNew) {
                return ui.describeTask(list, curr);
            }
        } catch (DateTimeParseException e) {
            return "Invalid Date format! Must be (yyyy-mm-dd).";
        }
        return "";
    }

    /**
     * Read input from String and add new Todo object to the list
     * @param ui Ui object to print user display
     * @param list The task list
     * @param str The string input
     * @param isNew Boolean value that indicates the task is new or not
     * @param isDone Boolean value that indicates the state of the task
     */
    public String addTodo(Ui ui, ArrayList<Task> list, String str, boolean isNew, boolean isDone)
            throws InvalidTodoException {
        String description = str.trim();
        if (description.equals("")) {
            throw new InvalidTodoException("☹ Todo description must be specified.");
        }
        Todo curr = new Todo(description, isDone);
        list.add(curr);
        if (isNew) {
            return ui.describeTask(list, curr);
        }
        return "";
    }

    /**
     * Deletes a task from the list
     * @param list The task list
     * @param index The index of the target object
     */
    public String deleteTask(ArrayList<Task> list, int index) throws DukeErrorException {
        if (index >= list.size() || index < 0) {
            throw new DukeErrorException("Operation: delete " + (index + 1) + " fails ☹.");
        }
        Task deleted = list.remove(index);
        return Ui.printDeleted(deleted, list);
    }

    /**
     * Make the target task to be completed
     * @param list The task list
     * @param index The index of the target object
     */
    public String makeDone(ArrayList<Task> list, int index) throws DukeErrorException {
        if (index >= list.size() || index < 0) {
            throw new DukeErrorException("Operation: done " + (index + 1) + " fails ☹.");
        }
        list.set(index, list.get(index).completeTask());
        return Ui.printDone(list, index);
    }

    /**
     * Finds Task objects that contains the keyword
     * @param list The task list
     * @param query The query keyword
     * @reutn A list of task that fulfills the query keyword
     */
    public ArrayList<Task> findTask(ArrayList<Task> list, String query) {
        ArrayList<Task> suitableTasks = new ArrayList<>();
        list.forEach(x -> {
            if (x.getDescription().contains(query)) {
                suitableTasks.add(x);
            }
        });
        return suitableTasks;
    }
}

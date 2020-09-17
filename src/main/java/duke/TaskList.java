package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import exception.DukeErrorException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import exception.UnknownCommandException;
import ui.Ui;


/**
 * Represents a {@code Tasklist} object to store tasks in memory.
 */
public class TaskList {
    // List of tasks
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Reads input from String and add new Deadline object to the list.
     * @param ui Ui object to print user display.
     * @param str The string input.
     * @param isNew Boolean value that indicates the task is new or not.
     * @param isDone Boolean value that indicates the state of the task.
     */
    public String addDeadline(Ui ui, String str, boolean isNew, boolean isDone)
            throws InvalidDeadlineException {
        String[] deadline;
        if (isNew) {
            deadline = str.split("/by");
        } else {
            deadline = str.split("on");
        }
        assert deadline != null : "Deadline should not be empty";
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
            this.tasks.add(curr);
            if (isNew) {
                return ui.describeTask(this.tasks, curr);
            }
        } catch (DateTimeParseException e) {
            return "Invalid Date format! Must be (yyyy-mm-dd).";
        }
        return "";
    }

    /**
     * Reads input from String and add new Event object to the list.
     * @param ui Ui object to print user display.
     * @param str The string input.
     * @param isNew Boolean value that indicates the task is new or not.
     * @param isDone Boolean value that indicates the state of the task.
     */
    public String addEvent(Ui ui, String str, boolean isNew, boolean isDone)
            throws InvalidEventException {
        String[] event;
        if (isNew) {
            event = str.split("/at");
        } else {
            event = str.split("on");
        }
        assert event != null : "Event should not be empty";
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
            this.tasks.add(curr);
            if (isNew) {
                return ui.describeTask(this.tasks, curr);
            }
        } catch (DateTimeParseException e) {
            return "Invalid Date format! Must be (yyyy-mm-dd).";
        }
        return "";
    }

    /**
     * Reads input from String and add new Todo object to the list.
     * @param ui Ui object to print user display.
     * @param str The string input.
     * @param isNew Boolean value that indicates the task is new or not.
     * @param isDone Boolean value that indicates the state of the task.
     */
    public String addTodo(Ui ui, String str, boolean isNew, boolean isDone)
            throws InvalidTodoException {
        String description = str.trim();
        assert description != null : "Todo description must not be empty";
        if (description.equals("")) {
            throw new InvalidTodoException("☹ Todo description must be specified.");
        }
        Todo curr = new Todo(description, isDone);
        assert this.tasks != null : "The task list should not be null";
        this.tasks.add(curr);
        if (isNew) {
            return ui.describeTask(this.tasks, curr);
        }
        return "";
    }

    /**
     * Deletes a task from the list.
     * @param index The index of the target object.
     */
    public String deleteTask(int index) throws DukeErrorException {
        if (index >= this.tasks.size() || index < 0) {
            throw new DukeErrorException("Operation: delete " + (index + 1) + " fails ☹.");
        }
        assert this.tasks != null : "The task list should not be null";
        Task deleted = this.tasks.remove(index);
        return Ui.printDeleted(deleted, this.tasks);
    }

    /**
     * Makes the target task to be completed.
     * @param index The index of the target object.
     */
    public String makeDone(int index) throws DukeErrorException {
        if (index >= this.tasks.size() || index < 0) {
            throw new DukeErrorException("Operation: done " + (index + 1) + " fails ☹.");
        }
        assert this.tasks != null : "The task list should not be null";
        this.tasks.set(index, this.tasks.get(index).completeTask());
        return Ui.printDone(this.tasks, index);
    }

    /**
     * Updates a task
     * @param ui Ui object to print user display
     * @param str The string input containing the new description
     */
    public String updateTask(Ui ui, String str)
            throws ArrayIndexOutOfBoundsException, UnknownCommandException, DukeErrorException {
        String[] command = str.trim().split(" ", 2);
        if (command[0].length() > 1) {
            throw new DukeErrorException("Follow this format: update TASKID TASKTYPE NEWDESCRIPTION /by NEWDATE");
        }
        int index = Integer.parseInt(command[0]) - 1;
        if (index > this.tasks.size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        String[] description = command[1].split(" ", 2);
        String taskType = description[0];
        String taskDetails = description[1];
        System.out.println(taskDetails);
        if (taskType.equals("todo")) {
            tasks.get(index).setDescription(taskDetails);
        } else if (taskType.equals("deadline")) {
            description = taskDetails.split("/by");
            String deadlineTitle = description[0].trim();
            String deadlineTime = description[1].trim();
            tasks.get(index).setDescription(deadlineTitle);
            tasks.get(index).setTime(LocalDate.parse(deadlineTime));
        } else if (taskType.equals("event")) {
            description = taskDetails.split("/at");
            String eventTitle = description[0].trim();
            String eventTime = description[1].trim();
            tasks.get(index).setDescription(eventTitle);
            tasks.get(index).setTime(LocalDate.parse(eventTime));
        } else {
            throw new UnknownCommandException();
        }
        return ui.printList(this.tasks);
    }

    /**
     * Finds Task objects that contains the keyword.
     * @param query The query keyword.
     * @reutn A list of task that fulfills the query keyword.
     */
    public ArrayList<Task> findTask(String query) {
        assert this.tasks != null : "The task list should not be null";
        Predicate<Task> matchQuery = x -> x.getDescription().contains(query);
        List<Task> suitableTasks = new ArrayList<>(this.tasks)
                .stream()
                .filter(matchQuery)
                .collect(Collectors.toList());
        return new ArrayList<Task>(suitableTasks);
    }
}

package taskbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import taskbot.exceptions.InvalidDateTimeException;
import taskbot.exceptions.InvalidIndexException;
import taskbot.exceptions.TaskAlreadyCompleteException;
import taskbot.exceptions.WrongFormatException;
import taskbot.storage.Storage;


/**
 * Encapsulates the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    /**
     * Creates a task list.
     *
     * @param storage The database to retrieve the task list.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.getTasksList();
    }

    /**
     * Creates a task list given the task list.
     * This constructor is to be used for
     * testing purposes only.
     *
     * @param tasks The task list to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.storage = null;
        this.tasks = tasks;
    }

    /**
     * Lists all the tasks.
     * @return a String representing the list of tasks, or no tasks.
     */
    public String listTasks() {
        //Checks if list is empty
        if (tasks.size() == 0) {
            return "You currently have no tasks pending.";
        }

        StringBuilder sb = new StringBuilder("These are the following task(s) to complete:\n");
        // Size of the tasks
        int size = tasks.size();
        // Builds the list of tasks
        for (int i = 0; i < size - 1; i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        sb.append(size).append(". ").append(tasks.get(size - 1));

        // Returns the list of tasks
        return sb.toString();
    }

    /**
     * Informs the user of the number of tasks in the current list.
     *
     * @return A string describing how many tasks there are in the list.
     */
    public String listTaskSize() {
        return "You now have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Adds a todo Task to the list
     *
     * @param task The task to be added
     * @return a String informing the task has been added.
     */
    public String addTodoTask(String task) {
        // Makes a new todo task
        Todo newTask = new Todo(task);
        //Adds the new task to the list
        tasks.add(newTask);
        // Update the storage
        if (storage != null) {
            storage.setTasksList(tasks);
        }
        // Informs the user that the task has been added
        return "I have added a Todo:\n" + newTask
                + "\n" + listTaskSize();
    }

    /**
     * Adds an event Task to the list.
     *
     * @param input The task to be added followed by time.
     * @return a String informing the task has been added.
     * @throws InvalidDateTimeException if the given date time argument has a wrong format.
     * @throws WrongFormatException if the given input does not match the required format for an event.
     */
    public String addEventTask(String input) throws InvalidDateTimeException, WrongFormatException {
        // Splits the input according to whitespace
        String[] parsedString = input.split("/at");

        if (parsedString.length != 2) {
            throw new WrongFormatException(
                    "Wrong format for event. Please ensure that you provide a description and time, delimited by /at");
        }
        LocalDateTime dateTime = parseDateTime(parsedString[1].stripLeading());
        // Makes a new Event task
        Event newTask = new Event(parsedString[0], dateTime);

        // Adds the new task to the list
        tasks.add(newTask);

        // Update the storage
        if (storage != null) {
            storage.setTasksList(tasks);
        }

        // Informs the user that the task has been added
        return "I have added an Event:\n" + newTask
                + "\n" + listTaskSize();
    }

    /**
     * Adds a deadline Task to the list.
     *
     * @param input The task to be added followed by time.
     * @return a String informing the task has been added.
     * @throws InvalidDateTimeException if the given date time argument has a wrong format.
     * @throws WrongFormatException if the given input does not match the required format for a deadline.
     */
    public String addDeadlineTask(String input) throws InvalidDateTimeException, WrongFormatException {
        // Splits the input according to whitespace
        String[] parsedString = input.split("/by");
        if (parsedString.length != 2) {
            throw new WrongFormatException(
                    "Wrong format for deadline. "
                    + "Please ensure that you provide a description and time, delimited by /by");
        }

        LocalDateTime dateTime = parseDateTime(parsedString[1].stripLeading());
        // Makes a new Deadline task
        Deadline newTask = new Deadline(parsedString[0], dateTime);

        // Adds the new task to the list
        tasks.add(newTask);

        // Update the storage
        if (storage != null) {
            storage.setTasksList(tasks);
        }

        // Informs the user that the task has been added
        return "I have added a Deadline:\n" + newTask
                + "\n" + listTaskSize();
    }

    /**
     * Checks if the given dateTime is valid.
     *
     * @param str Localized date and time.
     */
    private LocalDateTime parseDateTime(String str) throws InvalidDateTimeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid format of date and time.\n"
                    + "Please follow the format: YYYY-mm-dd hhmm");
        }
    }

    /**
     * Completes a task in the given index.
     *
     * @param taskIndex Index of the task in the list.
     * @return a String informing the task has been completed.
     * @throws InvalidIndexException when the given index is not within the size of the list.
     * @throws TaskAlreadyCompleteException when the task to complete is already completed.
     */
    public String completeTask(int taskIndex) throws InvalidIndexException, TaskAlreadyCompleteException {
        try {
            if (tasks.get(taskIndex).getStatusIcon().equals("\u2713")) {
                throw new TaskAlreadyCompleteException("The specified task has already been completed.");
            }
            tasks.get(taskIndex).completeTask();
            // Update the storage
            if (storage != null) {
                storage.setTasksList(tasks);
            }
            String message = "Understood. The following task is now marked as done:\n";
            message += "    " + tasks.get(taskIndex);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(
                    "You have specified an index not within the ranges of the list. Please try again.");
        }
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param taskIndex Index of the task in the list.
     * @return a String informing the task has been deleted.
     * @throws InvalidIndexException when the given index is not within the size of the list.
     */
    public String deleteTask(int taskIndex) throws InvalidIndexException {
        try {
            if (tasks.size() == 0) {
                return "You currently have no tasks pending.";
            }
            String message = "Understood. The following task has been deleted.\n";
            message += "    " + tasks.get(taskIndex) + "\n";
            tasks.remove(taskIndex);
            // Update the storage
            if (storage != null) {
                storage.setTasksList(tasks);
            }
            message += listTaskSize();
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(
                    "You have specified an index not within the ranges of the list. Please try again.");
        }
    }

    /**
     * Lists the upcoming tasks in the given time frame.
     * Tasks without time limits are included as well.
     *
     * @param days Number of days ahead to check for tasks.
     * @return a String containing tasks occuring in the upcoming days.
     */
    public String getUpcoming(int days) {
        //If tasks is empty
        if (tasks.size() == 0) {
            return "You currently have no tasks pending.";
        }

        StringBuilder sb = new StringBuilder("These are the following task(s) to complete:\n");
        // Size of the tasks
        int size = tasks.size();
        // Threshold for time of tasks
        LocalDateTime threshold = LocalDate.now().plusDays(days + 1).atTime(LocalTime.MIN);
        // Builds the list of tasks
        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);
            if (task instanceof TimedTask) {
                if (((TimedTask) task).getTime().isAfter(threshold)) {
                    continue;
                }
            }
            sb.append(i + 1).append(". ").append(task).append("\n");
        }
        // Returns the list of tasks that fall within the time frame
        return sb.toString();
    }

    /**
     * Finds tasks containing the given keyword.
     * This method ignores casing.
     *
     * @param keywords Keywords within a task's description
     */
    public String findTasks(String[] keywords) {
        //If tasks is empty
        if (tasks.size() == 0) {
            return "You currently have no tasks pending.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        //Set to contain tasks
        Set<String> uniqueTasks = new HashSet<String>();
        //Flag for when a match is found
        boolean found = false;
        //Builds the list of tasks
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            for (String keyword : keywords) {
                if (task.getTask().toLowerCase().contains(keyword)) {
                    if (!found) {
                        found = true;
                    }
                    uniqueTasks.add(task.toString());
                }
            }
        }

        if (!found) {
            //Printed if no matches found
            return "Sorry, that keyword did not return any results. Please try another.";
        } else {
            int counter = 1;
            for (String task : uniqueTasks) {
                sb.append(counter).append(". ").append(task).append("\n");
                counter++;
            }
            //Returns the lists of tasks found matching the keyword
            return sb.toString();
        }
    }
}

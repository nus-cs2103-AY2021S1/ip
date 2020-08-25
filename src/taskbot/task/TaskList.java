package taskbot.task;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import taskbot.storage.Storage;

import taskbot.exceptions.InvalidDateTimeException;
import taskbot.exceptions.InvalidIndexException;
import taskbot.exceptions.TaskAlreadyCompleteException;
import taskbot.exceptions.WrongFormatException;

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
     * Lists all the tasks
     */
    public void listTasks() {
        //If tasks list is empty
        if (tasks.size() == 0) {
            System.out.println("You currently have no tasks pending.");
            return;
        }

        StringBuilder sb = new StringBuilder("These are the following task(s) to complete:\n");
        //size of the tasks
        int size = tasks.size();
        //Builds the list of tasks
        for (int i = 0; i < size - 1; i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        sb.append(size).append(". ").append(tasks.get(size - 1));

        //Prints the string
        System.out.print(sb.toString());
    }

    /**
     * Informs the user of the number of tasks in the current list
     */
    public String listTaskSize() {
        return "You now have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Adds a todo Task to the list
     * @param task The task to be added
     */
    public void addTodoTask(String task) {
        //Makes a new todo task
        Todo newTask = new Todo(task);
        //Adds the new task to the list
        tasks.add(newTask);
        //Update the storage
        if (storage != null) {
            storage.setTasksList(tasks);
        }
        //Informs the user that the task has been added
        System.out.println("I have added a Todo:\n" + newTask +
                "\n" + listTaskSize());
    }

    /**
     * Adds an event Task to the list
     * @param input The task to be added followed by time
     */
    public void addEventTask(String input) throws InvalidDateTimeException, WrongFormatException {
        //splits the input according to whitespace
        String[] parsedString = input.split("/at");

        if (parsedString.length != 2) {
            throw new WrongFormatException("Wrong format for event. Please ensure that you provide a description and time, delimited by /at");
        }
        LocalDateTime dateTime = parseDateTime(parsedString[1].stripLeading());
        //Makes a new Event task
        Event newTask = new Event(parsedString[0], dateTime);

        //Adds the new task to the list
        tasks.add(newTask);

        //Update the storage
        if (storage != null) {
            storage.setTasksList(tasks);
        }

        //Informs the user that the task has been added
        System.out.println("I have added an Event:\n" + newTask +
                "\n" + listTaskSize());
    }

    /**
     * Adds a deadline Task to the list
     * @param input The task to be added followed by time
     */
    public void addDeadlineTask(String input) throws InvalidDateTimeException, WrongFormatException {
        //splits the input according to whitespace
        String[] parsedString = input.split("/by");
        if (parsedString.length != 2) {
            throw new WrongFormatException("Wrong format for deadline. Please ensure that you provide a description and time, delimited by /by");
        }

        LocalDateTime dateTime = parseDateTime(parsedString[1].stripLeading());
        //Makes a new Deadline task
        Deadline newTask = new Deadline(parsedString[0], dateTime);

        //Adds the new task to the list
        tasks.add(newTask);

        //Update the storage
        if (storage != null) {
            storage.setTasksList(tasks);
        }

        //Informs the user that the task has been added
        System.out.println("I have added a Deadline:\n" + newTask +
                "\n" + listTaskSize());
    }

    /**
     * Checks if the given dateTime is valid.
     * @param str Localized date and time.
     */
    private LocalDateTime parseDateTime(String str) throws InvalidDateTimeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid format of date and time.\n" +
                    "Please follow the format: YYYY-mm-dd hhmm");
        }
    }

    /**
     * Completes a task in the given index
     * @param taskIndex Index of the task in the list
     */
    public void completeTask(int taskIndex) throws InvalidIndexException, TaskAlreadyCompleteException {
        try {
            if (tasks.get(taskIndex).getStatusIcon().equals("\u2713")) {
                throw new TaskAlreadyCompleteException("The specified task has already been completed.");
            }
            tasks.get(taskIndex).completeTask();
            //Update the storage
            if (storage != null) {
                storage.setTasksList(tasks);
            }
            String message = "Understood. The following task is now marked as done:\n";
            message += "    " + tasks.get(taskIndex);
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("You have specified an index not within the ranges of the list. Please try again.");
        }
    }

    /**
     * Deletes the task at the specified index
     * @param taskIndex Index of the task in the list
     */
    public void deleteTask(int taskIndex) throws InvalidIndexException {
        try {
            if (tasks.size() == 0) {
                System.out.println("You currently have no tasks pending.");
                return;
            }
            String message = "Understood. The following task has been deleted.\n";
            message += "    " + tasks.get(taskIndex) + "\n";
            tasks.remove(taskIndex);
            //Update the storage
            if (storage != null) {
                storage.setTasksList(tasks);
            }
            message += listTaskSize();
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("You have specified an index not within the ranges of the list. Please try again.");
        }
    }

    /**
     * Lists the upcoming tasks.
     * @param days Number of days ahead to check for tasks.
     */
    public void getUpcoming(int days) {
        //If tasks is empty
        if (tasks.size() == 0) {
            System.out.println("You currently have no tasks pending.");
            return;
        }

        StringBuilder sb = new StringBuilder("These are the following task(s) to complete:\n");
        //size of the tasks
        int size = tasks.size();
        //Threshold for time of tasks
        LocalDateTime threshold = LocalDate.now().plusDays(days + 1).atTime(LocalTime.MIN);
        //Builds the list of tasks
        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);
            if (task instanceof TimedTask) {
                if (((TimedTask) task).getTime().isAfter(threshold)) {
                    continue;
                }
            }
            sb.append(i + 1).append(". ").append(task).append("\n");
        }
        //Prints the string
        System.out.print(sb.toString());
    }

}

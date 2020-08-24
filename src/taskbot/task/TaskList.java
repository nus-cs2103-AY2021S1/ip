package taskbot.task;

import taskbot.exceptions.InvalidIndexException;
import taskbot.exceptions.TaskAlreadyCompleteException;
import taskbot.exceptions.WrongFormatException;
import taskbot.storage.Storage;

import java.util.ArrayList;

import static taskbot.helper.Helper.borderString;
import static taskbot.helper.Helper.handleException;

/**
 * Contains the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    /**
     * Creates a task list
     * @param storage The database containing the list
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.getTasksList();
    }

    /**
     * Lists all the tasks
     */
    public void listTasks() {
        //If tasks is empty
        if (tasks.size() == 0) {
            borderString("You currently have no tasks pending.");
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
        borderString(sb.toString());
    }

    /**
     * Adds a todo Task to the list
     * @param task The task to be added
     */
    public void addTodoTask(String task) {
        //Makes a new taskbot.task.Todo task
        Todo newTask = new Todo(task);
        //Adds the new task to the list
        tasks.add(newTask);
        //Update the storage
        storage.setTasksList(tasks);
        //Informs the user that the task has been added
        borderString("I have added a Todo:\n" + newTask +
                "\n" + listTaskSize());
    }

    /**
     * Adds an event Task to the list
     * @param input The task to be added followed by time
     */
    public void addEventTask(String input) {
        try {
            //splits the input according to whitespace
            String[] parsedString = input.split("/at");

            if (parsedString.length != 2) {
                throw new WrongFormatException("Wrong format for event. Please ensure that you provide a description and time, delimited by /at");
            }
            //Makes a new Event task
            Event newTask = new Event(parsedString[0], parsedString[1].stripLeading());
            //Adds the new task to the list
            tasks.add(newTask);
            //Update the storage
            storage.setTasksList(tasks);
            //Informs the user that the task has been added
            borderString("I have added an Event:\n" + newTask +
                    "\n" + listTaskSize());
        } catch (WrongFormatException e) {
            handleException(e);
        }
    }

    /**
     * Adds a deadline Task to the list
     * @param input The task to be added followed by time
     */
    public void addDeadlineTask(String input) {
        try {
            //splits the input according to whitespace
            String[] parsedString = input.split("/by");
            if (parsedString.length != 2) {
                throw new WrongFormatException("Wrong format for deadline. Please ensure that you provide a description and time, delimited by /by");
            }
            //Makes a new Deadline task
            Deadline newTask = new Deadline(parsedString[0], parsedString[1].stripLeading());
            //Adds the new task to the list
            tasks.add(newTask);
            //Update the storage
            storage.setTasksList(tasks);
            //Informs the user that the task has been added
            borderString("I have added a Deadline:\n" + newTask +
                    "\n" + listTaskSize());
        } catch (WrongFormatException e) {
            handleException(e);
        }
    }

    /**
     * Informs the user of the number of tasks in the current list
     */
    public String listTaskSize() {
        return "You now have " + tasks.size() + " task(s) in the list";
    }

    /**
     * Completes a task in the given index
     * @param taskIndex Index of the task in the list
     */
    public void completeTask(int taskIndex) {
        try {
            if (tasks.get(taskIndex).getStatusIcon().equals("\u2713")) {
                throw new TaskAlreadyCompleteException("The specified task has already been completed.");
            }
            tasks.get(taskIndex).completeTask();
            //Update the storage
            storage.setTasksList(tasks);
            String message = "Understood. The following task is now marked as done:\n";
            message += "    " + tasks.get(taskIndex);
            borderString(message);
        } catch (IndexOutOfBoundsException e) {
            InvalidIndexException iie = new InvalidIndexException("You have specified an index not within the ranges of the list. Please try again.");
            handleException(iie);
        } catch (TaskAlreadyCompleteException e) {
            handleException(e);
        }
    }

    /**
     * Deletes the task at the specified index
     * @param taskIndex Index of the task in the list
     */
    public void deleteTask(int taskIndex) {
        try {
            if (tasks.size() == 0) {
                borderString("You currently have no tasks pending.");
                return;
            }
            String message = "Understood. The following task has been deleted.\n";
            message += "    " + tasks.get(taskIndex) + "\n";
            tasks.remove(taskIndex);
            //Update the storage
            storage.setTasksList(tasks);
            message += listTaskSize();
            borderString(message);
        } catch (IndexOutOfBoundsException e) {
            InvalidIndexException iie = new InvalidIndexException("You have specified an index not within the ranges of the list. Please try again.");
            handleException(iie);
        }
    }
}

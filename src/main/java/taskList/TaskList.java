package tasklist;

import java.util.ArrayList;

import task.Task;
import task.Deadline;
import task.Todo;
import task.Event;

import duke.DukeException;

import parser.Parser;

/**
 * TaskList contains the list of tasks and the functions that change the list of
 * tasks depending on the commands from the user of the Java Duke Program.
 *
 * @author (Sruthi)
 */
public class TaskList {
    private final ArrayList<Task> todoList;
    private int numberOfTasks;

    /**
     * It creates a new array list for the todo list and sets the counter to 0.
     */
    public TaskList() {
        todoList = new ArrayList<>();
        numberOfTasks = 0;
    }

    /**
     * It creates a String to be printed to the user to show all the tasks in the list
     * and returns it.
     *
     * @return String to be printed to the user
     */
    public String showList() {
        int count = 1;
        String output = "  The tasks in your Todo List: ";
        for (Task item : todoList) {
            output += "\n    " + count + ". " + item.getItem();
            count += 1;
        }
        return output;
    }

    /**
     * It finds the task in the task list based on the number given and sets it to
     * completed. It then returns the String to be printed for the user to see.
     * It throws a DukeException error if the user gives an invalid input.
     *
     * @param input input given by the user
     * @return String to be printed to the user
     * @throws DukeException
     */
    public String completeItem(String input) throws DukeException {
        String indexString = input.split(" ")[1];
        try {
            String output = "";
            Task item = todoList.get(Integer.valueOf(indexString) - 1);
            item.completeTask();
            output += "     \\\\(^o^)/ *.*.* \\\\(^o^)/"+ "\n  Yay! This task has been completed:" + "\n  " + item.getItem();
            return output;
        } catch (Exception e) {
            throw new DukeException("Oops! Invalid task number. Please try again >.<");
        }
    }

    /**
     * It finds the task in the task list based on the number given and deletes it from
     * the list. It then returns the String to be printed for the user to see.
     * It throws a DukeException error if the user gives an invalid input.
     *
     * @param input input given by the user
     * @return String to be printed to the user
     * @throws DukeException
     */
    public String deleteItem(String input) throws DukeException {
        String indexString = input.split(" ")[1];
        try {
            int index = Integer.valueOf(indexString) - 1;
            Task item = todoList.get(index);
            String output = "";
            output += "  Noted. This task has now been removed from the list:" + "\n    " + item.getItem();
            todoList.remove(index);
            numberOfTasks -= 1;
            output += "\n  There are now " + numberOfTasks + " todo items in the list";
            return output;
        } catch (Exception e) {
            throw new DukeException("Oops! Invalid task number. Please try again >.<");
        }
    }

    /**
     * It adds a task to the task list based on the command given by calling the appropriate
     * add task functionand then returns the String to be printed to the user. It throws a
     * DukeException when an error occurred while adding a task to the list.
     *
     * @param instruction the command given by the user
     * @param item description of the task
     * @return String to be printed to the user
     * @throws DukeException
     */
    public String addItem(String instruction, String item) throws DukeException {
        if (item.equals("") || item.equals(" ")) {
            throw new DukeException("Oops! The description cannot be empty >.<");
        }
        String output = "";
        if (instruction.equals("todo")) {
            output += addTodoItem(item, false);
        } else if (instruction.equals("deadline")) {
            output += addDeadline(item, false);
        } else {
            output += addEvent(item, false);
        }
        output += "\n  New todo item added to the list!";
        output += "\n  There are now " + (numberOfTasks + 1) + " todo items in the list";
        return output;
    }

    /**
     * It creates a new todo item based on the prams given and returns the String
     * to be printed to the user.
     *
     * @param item description of the todo item
     * @param isCompleted whether the todo is completed
     * @return String to be printed to the user
     */
    public String addTodoItem(String item, boolean isCompleted) {
        Todo newTask = new Todo(item, isCompleted);
        todoList.add(newTask);
        numberOfTasks += 1;
        return "  " + newTask.getItem();
    }


    /**
     * It creates a new Deadline item based on the params given and returns the String
     * to be printed. It throws a DukeException when an error occurs while adding the
     * task to the list.
     *
     * @param item the description of the Deadline
     * @param isCompleted whether the Deadline is completed
     * @return String to be printed to the user
     * @throws DukeException
     */
    public String addDeadline(String item, boolean isCompleted) throws DukeException {
        Deadline newTask = Parser.parseDeadline(item, isCompleted);
        todoList.add(newTask);
        numberOfTasks += 1;
        return "  " + newTask.getItem();
    }

    /**
     * It creates a new Event item based on the params given and returns the String
     * to be printed. It throws a DukeException when an error occurs while adding the
     * task to the list.
     *
     * @param item description of the Event
     * @param isCompleted whether the Event is completed
     * @return String to be printed to the user
     * @throws DukeException
     */
    public String addEvent(String item, boolean isCompleted) throws DukeException {
        Event newTask = Parser.parseEvent(item, isCompleted);
        todoList.add(newTask);
        numberOfTasks += 1;
        return "  " + newTask.getItem();
    }

    /**
     * It converts all the tasks in the list to String for ease of printing
     * to the User and returns this String.
     *
     * @return String to be print to the user
     */
    public String formatTodoListToString() {
        String tasks = "";
        for (Task task : todoList) {
            tasks += task.getInput() + "\n";
        }
        return tasks;
    }
}

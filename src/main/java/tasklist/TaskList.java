package tasklist;

import duke.DukeException;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

/**
 * TaskList contains the list of tasks and the functions that change the list of
 * tasks depending on the commands from the user of the Java Duke Program.
 *
 * @author (Sruthi)
 */
public class TaskList {
    private final ArrayList<Task> todoList;
    private int numberOfTasks;
    private String completedTask = "     \\\\(^o^)/ *.*.* \\\\(^o^)/"
            + "\n  Yay! This task has been completed:"
            + "\n  ";
    private Task deletedItem;

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
        assert(input.contains(" "));
        String indexString = input.split(" ")[1];
        assert(Integer.valueOf(indexString) > 0);
        try {
            String output = "";
            Task item = todoList.get(Integer.valueOf(indexString) - 1);
            item.completeTask();
            output += completedTask + item.getItem();
            return output;
        } catch (Exception e) {
            throw new DukeException("Oops! Invalid task number. Please try again >.<");
        }
    }

    /**
     * It marks a completed task as incomplete
     *
     * @param input
     * @return String to be outputted to the user
     * @throws DukeException
     */
    public String unCompleteItem(String input) throws DukeException {
        String indexString = input.split(" ")[1];
        try {
            String output = "";
            Task item = todoList.get(Integer.valueOf(indexString) - 1);
            item.unCompleteTask();
            output += "This task is back to being incomplete:\n" + item.getItem();
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
        assert(input.contains(" "));
        String indexString = input.split(" ")[1];
        try {
            Task item = deleteItemFromTodolist(indexString);
            String output = "";
            output += "  Noted. This task has now been removed from the list:" + "\n    " + item.getItem();
            output += "\n  There are now " + numberOfTasks + " todo items in the list";
            return output;
        } catch (Exception e) {
            throw new DukeException("Oops! Invalid task number. Please try again >.<");
        }
    }

    /**
     * Deletes the task of the given index from the todolist.
     *
     * @param indexString
     * @return Task that is deleted
     */
    public Task deleteItemFromTodolist(String indexString) {
        int index = Integer.valueOf(indexString) - 1;
        Task item = todoList.get(index);
        deletedItem = item;
        todoList.remove(index);
        numberOfTasks -= 1;
        return item;
    }

    /**
     * Adds the deleted item back to the todolist at its previous index
     *
     * @param input
     * @return String to be outputted to the user
     * @throws DukeException
     */
    public String unDeleteItem(String input) throws DukeException {
        try {
            String indexString = input.split(" ")[1];
            int index = Integer.valueOf(indexString) - 1;
            todoList.add(index, deletedItem);
            numberOfTasks += 1;
            String output = "";
            output += "  Noted. This task has now been added back to the list:"
                    + "\n    " + deletedItem.getItem();
            output += "\n  There are now " + numberOfTasks + " todo items in the list";
            return output;
        } catch (Exception e) {
            throw new DukeException("Oops! Invalid task number. Please try again >.<");
        }
    }

    /**
     * Deletes the last item added to the todolist
     *
     * @return String to be outputted to the user
     */
    public String deleteLastAddedItem() {
        int index = numberOfTasks - 1;
        Task item = todoList.get(index);
        deletedItem = item;
        todoList.remove(index);
        numberOfTasks -= 1;
        String output = "";
        output += "  Noted. This task has now been removed from the list:" + "\n    " + item.getItem();
        output += "\n  There are now " + numberOfTasks + " todo items in the list";
        return output;
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
        String output = addItemToTodolist(item, instruction);
        output += "\n  New todo item added to the list!";
        output += "\n  There are now " + numberOfTasks + " todo items in the list";
        return output;
    }

    /**
     * Adds task to the todo list.
     *
     * @param item
     * @param instruction
     * @return String of the task to be outputted to the user
     * @throws DukeException
     */
    public String addItemToTodolist(String item, String instruction) throws DukeException  {
        String output = "";
        if (instruction.equals("todo")) {
            output += addTodoItem(item, false);
        } else if (instruction.equals("deadline")) {
            output += addDeadline(item, false);
        } else {
            output += addEvent(item, false);
        }
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
     * Finds the tasks that match the search keywords inputted by the user
     * and returns the String to be printed to the user.
     *
     * @param input input given by the user
     * @return String returns all the tasks that match the search query
     */
    public String findItem(String input) {
        String query = input.substring(4);
        String result = findItemsFromTodolist(query);
        if (result.length() == 0) {
            result = "  There is no matching tasks in your list.";
        } else {
            result = "  Here are the matching tasks in your list: " + result;
        }
        return result;
    }

    /**
     * Based on the query given, finds the tasks in the list and returns their Strings
     *
     * @param query
     * @return String of all the tasks found in the list
     */
    public String findItemsFromTodolist(String query) {
        String result = "";
        int count = 0;
        for (Task task : todoList) {
            if (task.getTask().contains(query)) {
                count += 1;
                result += "\n    " + count + ". " + task.getItem();
            }
        }
        return result;
    }

    /**
     * It converts all the tasks in the list to String for ease of printing
     * to the User and returns this String.
     *
     * @return String to be print to the user
     * @return
     */
    public String formatTodoListToString() {
        String tasks = "";
        for (Task task : todoList) {
            tasks += task.getInput() + "\n";
        }
        return tasks;
    }
}

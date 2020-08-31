package duke.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains and handles the task list.
 */
public class TaskList {
    private List<Task> list;
    private static final String STARLINE = "**************************************************************************";

    /**
     * Constructs an instance of TaskList with an empty task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs an instance of TaskList populated with saved tasks read from BufferedReader.
     * @param bufferedReader Reader that reads from a saved file with previously saved tasks, if such a file exists.
     */
    public TaskList(BufferedReader bufferedReader) {
        try {
            this.list = new ArrayList<>();

            String line = bufferedReader.readLine();
            boolean isFileCorrupted = false;
            while (line != null) {
                try {
                    boolean isDone = Boolean.parseBoolean(bufferedReader.readLine());
                    this.add(line, isDone,false);
                } catch (IllegalArgumentException e) {
                    if (!isFileCorrupted) {
                        System.out.println(STARLINE + "WARNING: Your stored data appears to be in a corrupted format. "
                                + "Some tasks may be lost.");
                        isFileCorrupted = true;
                    }
                } finally {
                    line = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error populating task list with saved tasks: " + e);
        } 
    }

    /**
     * Returns the task list containing the user's task.
     * @return List containing the user's Task.
     */
    public List<Task> getTaskList() {
        return this.list;
    }

    /**
     * Prints a list of the user's tasks.
     */
    public void list() {
        System.out.println(STARLINE + "Here are the tasks in your list:");
        for (int i=0; i < this.list.size(); i++) {
            printTask(i);
        }
        System.out.println(STARLINE);
    }

    /**
     * Prints a specific task.
     * @param listIndex Index of the task as shown in the printed list (starting from 1).
     */
    public void printTask(int listIndex) {
        String task = String.format("%d.%s", listIndex + 1, this.list.get(listIndex));
        System.out.println(task);
    }
    
    /**
     * Prints the task added and number of tasks in the list.
     * @param task The task added.
     */
    public void echo(String task) {
        System.out.println("added: " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
    
    private void throwEmptyFieldException(String taskType, String ... fields) {
        StringBuilder emptyFields = new StringBuilder();
        boolean isFirstField = true;
        for (String field: fields) {
            if (isFirstField) {
                emptyFields.append(field);
                isFirstField = false;
            } else {
                emptyFields.append("/").append(field);
            }
        }
        
        throw new IllegalArgumentException("OOPS! The " + emptyFields + " of " + taskType + " cannot be empty.");
    }
    
    private void throwInvalidTaskSyntaxException(String taskType) {
        throw new IllegalArgumentException(String.format("OOPS! Invalid syntax. To add a %s, use:\n%s", taskType,
                Task.getFormat(taskType)));
    }

    /**
     * Handles user input command related to adding new task to the list. 
     * Throws errors to be caught and handled by parser if user input commands are in an invalid format.
     * @param input User input command for adding the task (unprocessed).
     * @param isDone Whether task is done or not.
     * @param shouldEcho Whether or not to echo the task added.
     */
    public void add(String input, boolean isDone, boolean shouldEcho) {
        String[] splitInput = input.split(" ", 2);
        String taskType = splitInput[0];
        Task newTask;
        
        switch(taskType) { 
        case "todo":
            if (input.matches("todo (\\S+\\s?)+")) {
                newTask = new Todo(splitInput[1], isDone);
                break;
            } else if (input.matches("todo\\s?")) {
                throwEmptyFieldException("todo", "description");
            } else {
                throwInvalidTaskSyntaxException("todo");
            }
        case "deadline":
            if (input.matches("deadline (\\S+\\s?)+ /by (\\S+\\s?)+")) {
                String[] splitDeadline = splitInput[1].split(" /by ");
                String deadlineDesc = splitDeadline[0];
                String by = splitDeadline[1];
                newTask = new Deadline(deadlineDesc, by, isDone);
                break;
            } else if (input.matches("deadline\\s?") || !input.contains(" by ")){
                throwEmptyFieldException("deadline", "description", "date");
            } else {
                throwInvalidTaskSyntaxException("deadline");
            }
        case "event":
            if (input.matches("event (\\S+\\s?)+ /at (\\S+\\s?)+")) {
                String[] splitEvent = splitInput[1].split(" /at ");
                String eventDesc = splitEvent[0];
                String at = splitEvent[1];
                newTask = new Event(eventDesc, at, isDone);
                break;
            } else if (input.matches("event\\s?") || !input.contains(" at ")) {
                throwEmptyFieldException("event", "description", "location");
            } else {
                throwInvalidTaskSyntaxException("event");
            }
        default:
            throw new IllegalArgumentException("OOPS! There is no task of type " + taskType + "!");
        }
        this.list.add(newTask);
        if (shouldEcho) echo(newTask.toString());
    }

    /**
     * Handles the marking of tasks in the list as done. Feedbacks to user if list index of task is invalid.
     * @param listIndexString List index of task to mark as done (starting from 1).
     */
    public void markTaskAsDone(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = this.list.get(listIndexNumber - 1);
            task.markDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } catch (IndexOutOfBoundsException ex) { // if list index is not in the list
            System.out.println("OOPS! This task index does not exist! Type 'list' to check out your tasks.");
        } catch (NumberFormatException ex) { // if list index string is not an integer
            System.out.println("OOPS! The keyword 'done' is used to check off tasks as follows:"
                    + "   done <task index>");
        }
    }

    /**
     * Handles the deleting of tasks from task list. Feedbacks to user if list index of task is invalid.
     * @param listIndexString List index of task to delete (starting from 1).
     */
    public void deleteTask(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = this.list.get(listIndexNumber - 1);
            this.list.remove(listIndexNumber - 1);
            System.out.println("The following task has been deleted:\n" + task);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OOPS! This task index does not exist! Type 'list' to check out your tasks.");
        } catch (NumberFormatException ex) { // if list index string is not an integer
            System.out.println("OOPS! The keyword 'delete' is used to delete tasks as follows:"
                    + "   delete <task index>");
        }
    }
}

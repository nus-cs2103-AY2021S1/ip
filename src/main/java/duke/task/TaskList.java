package duke.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains and handles the task list.
 */
public class TaskList {
    private static final String STAR_LINE = "**************************************************************************";
    private List<Task> list;

    /**
     * Constructs an instance of TaskList with an empty task list.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructs an instance of TaskList populated with saved tasks read from BufferedReader.
     * @param bufferedReader Reader that reads from a saved file with previously saved tasks, if such a file exists.
     */
    public TaskList(BufferedReader bufferedReader) {
        try {
            list = new ArrayList<>();

            String line = bufferedReader.readLine();
            boolean isFileCorrupted = false;
            while (line != null) {
                try {
                    boolean isDone = Boolean.parseBoolean(bufferedReader.readLine());
                    add(line, isDone, false);
                } catch (IllegalArgumentException e) {
                    if (!isFileCorrupted) {
                        System.out.println(STAR_LINE + "WARNING: Your stored data appears to be in a corrupted format. "
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
        return list;
    }

    private void printList(List<Task> list, String listObjectDescription) {
        System.out.println(STAR_LINE + String.format("\nHere are the %s in your list:", listObjectDescription));
        for (int i = 0; i < list.size(); i++) {
            printTask(i + 1, list.get(i));
        }
        System.out.println(STAR_LINE);
    }

    /**
     * Prints a list of the user's tasks.
     */
    public void list() {
        printList(list, "tasks");
    }

    /**
     * Prints a specific task.
     * @param listIndex Index of the task as shown in the printed list (starting from 1).
     */
    public void printTask(int listIndex, Task task) {
        String taskString = String.format("%d.%s", listIndex, task);
        System.out.println(taskString);
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

    private void addTaskToList(Task task, boolean shouldEcho) {
        list.add(task);
        if (shouldEcho) {
            echo(task.toString());
        }
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

        switch(taskType) {
        case "todo":
            if (input.matches("todo (\\S+\\s?)+")) {
                Task newTask = new Todo(splitInput[1], isDone);
                addTaskToList(newTask, shouldEcho);
                break;
            } else if (input.matches("todo\\s?")) {
                throwEmptyFieldException("todo", "description");
            } else {
                throwInvalidTaskSyntaxException("todo");
            }
            break;
        case "deadline":
            if (input.matches("deadline (\\S+\\s?)+ /by (\\S+\\s?)+")) {
                String[] splitDeadline = splitInput[1].split(" /by ");
                String deadlineDesc = splitDeadline[0];
                String by = splitDeadline[1];
                Task newTask = new Deadline(deadlineDesc, by, isDone);
                addTaskToList(newTask, shouldEcho);
                break;
            } else if (input.matches("deadline\\s?") || !input.contains(" by ")) {
                throwEmptyFieldException("deadline", "description", "date");
            } else {
                throwInvalidTaskSyntaxException("deadline");
            }
            break;
        case "event":
            if (input.matches("event (\\S+\\s?)+ /at (\\S+\\s?)+")) {
                String[] splitEvent = splitInput[1].split(" /at ");
                String eventDesc = splitEvent[0];
                String at = splitEvent[1];
                Task newTask = new Event(eventDesc, at, isDone);
                addTaskToList(newTask, shouldEcho);
                break;
            } else if (input.matches("event\\s?") || !input.contains(" at ")) {
                throwEmptyFieldException("event", "description", "location");
            } else {
                throwInvalidTaskSyntaxException("event");
            }
            break;
        default:
            throw new IllegalArgumentException("OOPS! There is no task of type " + taskType + "!");
        }
    }

    /**
     * Handles the marking of tasks in the list as done. Feedbacks to user if list index of task is invalid.
     * @param listIndexString List index of task to mark as done (starting from 1).
     */
    public void markTaskAsDone(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = list.get(listIndexNumber - 1);
            task.setDoneAsTrue();
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
            Task task = list.get(listIndexNumber - 1);
            list.remove(listIndexNumber - 1);
            System.out.println("The following task has been deleted:\n" + task);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OOPS! This task index does not exist! Type 'list' to check out your tasks.");
        } catch (NumberFormatException ex) { // if list index string is not an integer
            System.out.println("OOPS! The keyword 'delete' is used to delete tasks as follows:"
                    + "   delete <task index>");
        }
    }

    /**
     * Returns a list of Task with descriptions containing the keyword.
     * @param keyword The keyword used to filter Task descriptions.
     * @return A list of Task with descriptions containing the keyword.
     */
    public List<Task> find(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        printList(matchingTasks, "matching tasks");
        return matchingTasks;
    }
}

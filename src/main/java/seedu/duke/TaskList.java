package seedu.duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that stores all the tasks in an arraylist.
 */
public class TaskList {
    private static final String INVALID_RANGE = "Index out of range! Try again.\n";
    private static final String COMPLETED_TASK = "This task has already been completed!";
    private static final String MARK_DONE_TASK = "Nice! I have marked this task as done:\n";
    private static final String DELETE_TASK = "Noted. I have removed this task:\n";
    private static final String ADD_TASK = "Got it. I have added this task:\n";
    private static final String NOT_FOUND = "There are no tasks related to this keyword!\n";
    private static final String FOUND_KEYWORD = "Here are the matching tasks in your list: \n";
    private static final String DESCRIPTION = "Here are the tasks in your list:\n";
    private static final String MUST_BE_NUMBER = "Index must be a number!\n";
    private static final String INVALID_DATE = "Invalid Date format!\n";
    private static final String MISSING_DIVIDER = "Missing Divider!\n";
    private static final String LINE_BREAK = "\n";
    private static final String SORT_TASK_TYPE = "SORTED BY TASK TYPE\n";
    private static final String MISSING_INFO = "Missing Information!\n";

    private static final String TODO_SYMBOL = "T";
    private static final String EVENT_SYMBOL = "E";
    private static final String DEADLINE_SYMBOL = "D";

    private ArrayList<Task> taskLists;

    /**
     * Initializes an instance of Tasklist.
     *
     * @param listOfTasks Arraylist of Tasks to be completed.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        taskLists = listOfTasks;
    }

    /**
     * Outputs the size of tasklist.
     *
     * @return String describing size of tasklist.
     */

    private String sizeOfTaskList() {
        return "Now you have " + taskLists.size() + " tasks in the list" + "\n";
    }

    /**
     * Checks if a input for a new task is valid.
     *
     * @param input Name of the task.
     * @param task Task type.
     * @throws DukeException if empty input is given.
     */
    public void checkForItem(String input, String task) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("The field after " + task + " cannot be empty!");
        }
    }

    /**
     * Returns list of Tasks on GUI.
     *
     * @return String of Tasklist.
     */
    public String showTaskListForGui() {
        return toString();
    }

    /**
     * Checks if given index of the task is in valid position.
     *
     * @param index Index of the task.
     * @param size Size of Tasklist.
     * @return boolean True if valid index, else false.
     */
    private boolean checkValidIndex(int index, int size) {
        if (index < 1 || index > size) {
            return false;
        }
        return true;
    }

    /**
     * Checks if given Deadline or Event task has a divider "/".
     *
     * @param input String of user input.
     * @return boolean True if valid index, else false.
     */
    private boolean checkForDivider(String input) {
        if (!input.contains("/")) {
            return false;
        }
        return true;
    }

    /**
     * Marks the task as done in a list.
     *
     * @param userInput Task to be completed.
     * @return String output after completing Task.
     */
    public String completeTaskForGui(String userInput) {
        try {
            checkForItem(userInput.substring(4), Keyword.DONE.label); //Length of "done"
            String[] splitUserInput = userInput.split(" ");
            int index = Integer.parseInt(splitUserInput[1]);
            int size = taskLists.size();
            boolean isValidIndex = checkValidIndex(index, size);
            if (!isValidIndex) {
                return INVALID_RANGE;
            }
            if (taskLists.get(index - 1).getIsDone()) {
                return COMPLETED_TASK;
            }
            taskLists.get(index - 1).markAsDone();
            Storage.completeTaskOnFile(index - 1, taskLists.size());
            String info = MARK_DONE_TASK;
            info += taskLists.get(index - 1).toString();
            return info;
        } catch (NumberFormatException err) {
            return MUST_BE_NUMBER;
        } catch (DukeException err) {
            return err.getMessage();
        }
    }

    /**
     * Task to be deleted from tasklist.
     *
     * @param userInput String of the task to be deleted.
     * @return String output after deleting task.
     */
    public String deleteTaskForGui(String userInput) {
        try {
            checkForItem(userInput.substring(6), Keyword.DELETE.label); //Length of "done"
            String[] splitUserInput = userInput.split(" ");
            int index = Integer.parseInt(splitUserInput[1]);
            int size = taskLists.size();
            boolean isValidIndex = checkValidIndex(index, size);
            if (!isValidIndex) {
                return INVALID_RANGE;
            }
            String info = DELETE_TASK;
            info += taskLists.get(index - 1).toString();
            info += LINE_BREAK;
            taskLists.remove(index - 1);
            Storage.deleteTaskOnFile(index - 1, size);
            info += sizeOfTaskList();
            return info;
        } catch (NumberFormatException err) {
            return MUST_BE_NUMBER;
        } catch (DukeException err) {
            return err.getMessage();
        }
    }

    /**
     * Todo to be added to tasklist.
     *
     * @param userInput String of the todo.
     * @return String output after creating Todo object.
     */
    public String addToDoForGui(String userInput) {
        try {
            checkForItem(userInput.substring(4), Keyword.TODO.label.strip()); //Length of "todo"
            String task = userInput.substring(5);
            String info = ADD_TASK;
            Todo newToDo = new Todo(task);
            Storage.addTask(newToDo.getStorageString(TODO_SYMBOL));
            info += newToDo.toString();
            info += LINE_BREAK;
            taskLists.add(newToDo);
            info += sizeOfTaskList();
            return info;
        } catch (DukeException | IOException err) {
            return err.getMessage();
        }
    }

    /**
     * Deadline to be added to tasklist.
     *
     * @param userInput String of the deadline.
     * @return String output after creating Deadline object.
     */
    public String addDeadlineForGui(String userInput) {
        try {
            checkForItem(userInput.substring(8), Keyword.DEADLINE.label.strip()); //Length of Event
            boolean isDividerFound = checkForDivider(userInput.substring(8));
            if (!isDividerFound) {
                return MISSING_DIVIDER;
            }
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(9, dateIndex);
            if (task.strip().equals("")) {
                return MISSING_INFO;
            }
            String time = userInput.substring(dateIndex + 1);
            String info = ADD_TASK;
            Deadline newDeadline = new Deadline(task, time);
            String formatDate = newDeadline.getFormattedDate();
            info += newDeadline.toString();
            info += LINE_BREAK;
            taskLists.add(newDeadline);
            Storage.addTask(newDeadline.getStorageString(DEADLINE_SYMBOL, formatDate));
            info += sizeOfTaskList();
            return info;
        } catch (DukeException | IOException err) {
            return err.getMessage();
        } catch (DateTimeParseException err) {
            return INVALID_DATE;
        }
    }

    /**
     * Event to be added to the tasklist.
     *
     * @param userInput String of the event.
     * @return String output after creating Event object.
     */
    public String addEventForGui(String userInput) {
        try {
            checkForItem(userInput.substring(5), Keyword.EVENT.label.strip()); //Length of Event
            boolean isDividerFound = checkForDivider(userInput.substring(5));
            if (!isDividerFound) {
                return MISSING_DIVIDER;
            }
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(6, dateIndex);
            if (task.strip().equals("")) {
                return MISSING_INFO;
            }
            String time = userInput.substring(dateIndex + 1);
            String info = ADD_TASK;
            Event newEvent = new Event(task, time);
            String formatDate = newEvent.getFormattedDate();
            info += newEvent.toString();
            info += LINE_BREAK;
            Storage.addTask(newEvent.getStorageString(EVENT_SYMBOL, formatDate));
            taskLists.add(newEvent);
            info += sizeOfTaskList();
            return info;
        } catch (DukeException | IOException err) {
            return err.getMessage();
        } catch (DateTimeParseException err) {
            return INVALID_DATE;
        }
    }

    /**
     * Finds tasks that contain the keyword in the string input.
     *
     * @param input Keyword that is used to find related tasks.
     * @return String output after finding keyword in TaskList.
     */
    public String findForGui(String input) {
        try {
            checkForItem(input.substring(4), Keyword.FIND.label); //Length for "find"
            String keyword = input.substring(5);
            ArrayList<Task> keywordInTasks = new ArrayList<>();
            for (int i = 0; i < taskLists.size(); i++) {
                Task current = taskLists.get(i);
                if (current.toString().contains(keyword)) {
                    keywordInTasks.add(current);
                }
            }
            if (keywordInTasks.size() == 0) {
                return NOT_FOUND;
            }
            String info = FOUND_KEYWORD;
            for (int i = 0; i < keywordInTasks.size(); i++) {
                info += keywordInTasks.get(i).toString();
                info += LINE_BREAK;
            }
            return info;
        } catch (DukeException err) {
            return err.getMessage();
        }
    }

    /**
     * Returns a string of sorted list of tasks by alphabetical order.
     *
     * @return string of tasks in chronological order.
     */
    public String sortByTasksForGui() {
        ArrayList<String> taskListInString = new ArrayList<>();
        for (Task task : taskLists) {
            taskListInString.add(task.toString());
        }
        Collections.sort(taskListInString);
        String toPrint = "";
        toPrint += SORT_TASK_TYPE;
        for (String sortedTask : taskListInString) {
            toPrint += sortedTask;
            toPrint += LINE_BREAK;
        }
        return toPrint;
    }

    /**
     * Iterates through the arraylist of tasks and print it out.
     *
     * @return String describing each tasks in the list.
     */
    @Override
    public String toString() {
        String toPrint = "";
        toPrint += DESCRIPTION;
        for (int i = 0; i < taskLists.size(); i++) {
            toPrint += ("  " + (i + 1) + ". " + taskLists.get(i).toString() + " \n");
        }
        return toPrint;
    }
}

package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that stores all the tasks in an arraylist.
 */
public class TaskList {
    private ArrayList<Task> taskLists;
    private final String INVALID_RANGE = "Index out of range! Try again.\n";
    private final String COMPLETED_TASK = "This task has already been completed!";
    private final String MARK_DONE_TASK = "    Nice! I have marked this task as done:\n";
    private final String MISSING_INDEX = "No index found! Try again.\n";
    private final String DELETE_TASK = "Noted. I have removed this task:\n";
    private final String ADD_TASK = "Got it. I have added this task:\n";
    private final String NOT_FOUND = "There are no tasks related to this keyword!\n";
    private final String FOUND_KEYWORD = "Here are the matching tasks in your list: \n";
    private final String DESCRIPTION = "    Here are the tasks in your list:\n";

    private final String TODO = "todo";
    private final String EVENT = "event";
    private final String DEADLINE = "deadline";
    private final String FIND = "find";

    private static final String TODO_SYMBOL = "T";
    private static final String EVENT_SYMBOL = "E";
    private static final String DEADLINE_SYMBOL  = "D";

    /**
     * Initializes an instance of Tasklist.
     *
     * @param listOfTasks Arraylist of Tasks to be completed.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        taskLists = listOfTasks;
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
            throw new DukeException("The description of a " + task + " cannot be empty!");
        }
    }

    /**
     * Marks the task as done in a list.
     *
     * @param userInput Task to be completed.
     */
    public void completeTask(String userInput) {
        try {
            String[] splitUserInput = userInput.split(" ");
            int index = Integer.parseInt(splitUserInput[1]);
            if (index < 1 || index > taskLists.size()) {
                Ui.print(INVALID_RANGE);
            } else {
                if (taskLists.get(index - 1).getIsDone()) {
                    Ui.print(COMPLETED_TASK);
                } else {
                    taskLists.get(index - 1).markAsDone();
                    Storage.completeTaskOnFile(index - 1, taskLists.size());
                    String info = MARK_DONE_TASK;
                    info += taskLists.get(index - 1).toString() + "\n";
                    Ui.print(info);
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            Ui.print(INVALID_RANGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.print(MISSING_INDEX);
        }
    }

    /**
     * Marks the task as done in a list.
     *
     * @param userInput Task to be completed.
     * @return String output after completing Task.
     */
    public String completeTaskToString(String userInput) {
        try {
            if  (userInput.length() < 5) {
                return (MISSING_INDEX);
            }
            String[] splitUserInput = userInput.split(" ");
            int index = Integer.parseInt(splitUserInput[1]);
            if (index < 1 || index > taskLists.size()) {
                return (INVALID_RANGE);
            } else {
                if (taskLists.get(index - 1).getIsDone()) {
                    return (COMPLETED_TASK);
                } else {
                    taskLists.get(index - 1).markAsDone();
                    Storage.completeTaskOnFile(index - 1, taskLists.size());
                    String info = MARK_DONE_TASK;
                    info += taskLists.get(index - 1).toString() + "\n";
                    return info;
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            return INVALID_RANGE;
        } catch (ArrayIndexOutOfBoundsException e) {
            return MISSING_INDEX;
        }
    }

    public String sizeOfTaskList() {
        return "Now you have " + taskLists.size() + " tasks in the list" + "\n";
    }
    /**
     * Task to be deleted from tasklist.
     *
     * @param userInput String of the task to be deleted.
     */
    public void deleteTask(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        int index = Integer.parseInt(splitUserInput[1]);
        if (index < 1 || index > taskLists.size()) {
            Ui.print(INVALID_RANGE);
        } else {
            String info = DELETE_TASK;
            info += "  " + taskLists.get(index - 1).toString() + "\n";
            taskLists.remove(index - 1);
            Storage.deleteTaskOnFile(index - 1, taskLists.size());
            info += sizeOfTaskList();
            Ui.print(info);
        }
    }
    /**
     * Task to be deleted from tasklist.
     *
     * @param userInput String of the task to be deleted.
     * @return String output after deleting task.
     */
    public String deleteTaskToString(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        int index = Integer.parseInt(splitUserInput[1]);
        if (index < 1 || index > taskLists.size()) {
            return (INVALID_RANGE);
        } else {
            String info = DELETE_TASK;
            info += "  " + taskLists.get(index - 1).toString() + "\n";
            taskLists.remove(index - 1);
            Storage.deleteTaskOnFile(index - 1, taskLists.size());
            info += sizeOfTaskList();
            return info;
        }
    }

    /**
     * Todo to be added to tasklist.
     *
     * @param userInput String of the todo.
     */
    public void addToDo(String userInput) {
        try {
            checkForItem(userInput.substring(4), TODO);
            String task = userInput.substring(5);
            String info = ADD_TASK;
            Todo tempTodo = new Todo(task);
            Storage.addTask(tempTodo.getStorageString(TODO_SYMBOL));
            info += "  " + tempTodo.toString() + "\n";
            taskLists.add(tempTodo);
            info += sizeOfTaskList();
            Ui.print(info);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Todo to be added to tasklist.
     *
     * @param userInput String of the todo.
     * @return String output after creating Todo object.
     */
    public String addToDoToString(String userInput) {
        try {
            checkForItem(userInput.substring(4), TODO);
            String task = userInput.substring(5);
            String info = ADD_TASK;
            Todo tempTodo = new Todo(task);
            Storage.addTask(tempTodo.getStorageString(TODO_SYMBOL));
            info += "  " + tempTodo.toString() + "\n";
            taskLists.add(tempTodo);
            info += sizeOfTaskList();
            return info;
        } catch (DukeException err) {
            return err.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Deadline to be added to tasklist.
     *
     * @param userInput String of the deadline.
     */
    public void addDeadline(String userInput) {
        try {
            checkForItem(userInput.substring(8), DEADLINE);
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(9, dateIndex);
            String time = userInput.substring(dateIndex + 1);
            String info = ADD_TASK;
            Deadline tempDeadline = new Deadline(task, time);
            String formatDate = tempDeadline.getFormattedDate();
            info += "  " + tempDeadline.toString() + "\n";
            taskLists.add(tempDeadline);
            Storage.addTask(tempDeadline.getStorageString(DEADLINE_SYMBOL, formatDate));
            info += sizeOfTaskList();
            Ui.print(info);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deadline to be added to tasklist.
     *
     * @param userInput String of the deadline.
     * @return String output after creating Deadline object.
     */
    public String addDeadlineToString(String userInput) {
        try {
            checkForItem(userInput.substring(8), DEADLINE);
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(9, dateIndex);
            String time = userInput.substring(dateIndex + 1);
            String info = ADD_TASK;
            Deadline tempDeadline = new Deadline(task, time);
            String formatDate = tempDeadline.getFormattedDate();
            info += "  " + tempDeadline.toString() + "\n";
            taskLists.add(tempDeadline);
            Storage.addTask(tempDeadline.getStorageString(DEADLINE_SYMBOL, formatDate));
            info += sizeOfTaskList();
            return info;
        } catch (DukeException err) {
            return err.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Event to be added to the tasklist.
     *
     * @param userInput String of the event.
     */
    public void addEvent(String userInput) {
        try {
            checkForItem(userInput.substring(5), EVENT);
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(6, dateIndex);
            String time = userInput.substring(dateIndex + 1);
            String info = ADD_TASK;
            Event tempEvent = new Event(task, time);
            String formatDate = tempEvent.getFormattedDate();
            info += "  " + tempEvent.toString() + "\n";
            Storage.addTask(tempEvent.getStorageString(EVENT_SYMBOL, formatDate));
            taskLists.add(tempEvent);
            info += sizeOfTaskList();
            Ui.print(info);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event to be added to the tasklist.
     *
     * @param userInput String of the event.
     * @return String output after creating Event object.
     */
    public String addEventToString(String userInput) {
        try {
            checkForItem(userInput.substring(5), EVENT);
            int dateIndex = userInput.indexOf("/");
            String task = userInput.substring(6, dateIndex);
            String time = userInput.substring(dateIndex + 1);
            String info = ADD_TASK;
            Event tempEvent = new Event(task, time);
            String formatDate = tempEvent.getFormattedDate();
            info += "  " + tempEvent.toString() + "\n";
            Storage.addTask(tempEvent.getStorageString(EVENT_SYMBOL, formatDate));
            taskLists.add(tempEvent);
            info += sizeOfTaskList();
            return info;
        } catch (DukeException err) {
            return err.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Finds tasks that contain the keyword in the string input.
     *
     * @param input Keyword that is used to find related tasks.
     */
    public void find(String input) {
        try {
            checkForItem(input.substring(5), FIND);
            String keyword = input.substring(5);
            ArrayList<Task> keywordInTasks = new ArrayList<>();
            for (int i = 0; i < taskLists.size(); i++) {
                Task current = taskLists.get(i);
                if (current.toString().contains(keyword)) {
                    keywordInTasks.add(current);
                }
            }
            if (keywordInTasks.size() == 0) {
                Ui.print(NOT_FOUND);
            } else {
                String info = FOUND_KEYWORD;
                for (int i = 0; i < keywordInTasks.size(); i++) {
                    info += keywordInTasks.get(i).toString() + "\n";
                }
                Ui.print(info);
            }
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Finds tasks that contain the keyword in the string input.
     *
     * @param input Keyword that is used to find related tasks.
     * @return String output after finding keyword in TaskList.
     */
    public String findToString(String input) {
        try {
            checkForItem(input.substring(5), FIND);
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
            } else {
                String info = FOUND_KEYWORD;
                for (int i = 0; i < keywordInTasks.size(); i++) {
                    info += keywordInTasks.get(i).toString() + "\n";
                }
                return info;
            }
        } catch (DukeException err) {
            return err.getMessage();
        }
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

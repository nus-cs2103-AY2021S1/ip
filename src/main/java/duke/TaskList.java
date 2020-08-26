package duke;

import java.util.ArrayList;

/**
 * Represents a list of stored tasks.
 */
public class TaskList {
    private ArrayList<Task> storedTasks;

    /**
     * Creates an instance of a TaskList.
     * @param storedTasks ArrayList of stored tasks loaded from file.
     */
    public TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }

    /**
     * Checks whether an empty input is given and throws a DukeException.
     * @param input String user input.
     * @throws DukeException If input is empty.
     */
    public void validateScannerInput(String input) throws DukeException {
        if (input.trim().length() == 0) {
            throw new DukeException("Come again, Your Majesty?");
        }
    }

    /**
     * Checks whether an empty task description is given and throws a DukeException.
     * @param task String array of user input.
     * @throws DukeException If task description is empty.
     */
    public void validateAdd(String[] task) throws DukeException {
        if ((task.length == 1 || task[1].trim().length() == 0) &&
                (task[0].equalsIgnoreCase("todo") ||
                        task[0].equalsIgnoreCase("event") ||
                        task[0].equalsIgnoreCase("deadline"))
        ) {
            throw new DukeException("Do give me more details about this " + task[0] + ", Your Majesty.");
        }
    }

    /**
     * Checks whether correct slash command /by is given for a deadline task and /on for an event task. 
     * DukeException is thrown if slash commands are incorrect.
     * @param task String array of user input.
     * @throws DukeException If slash commands are incorrect.
     */
    public void validateSlashCommands(String[] task) throws DukeException {
        if(task[0].equalsIgnoreCase("deadline") && task[1].split("/by ", 2).length == 1){
            throw new DukeException("Use /by for deadlines, Your Majesty.");
        } else if (task[0].equalsIgnoreCase("event") &&  task[1].split("/on ", 2).length == 1){
            throw new DukeException("Use /on for events, Your Majesty.");
        }
    }

    /**
     * Checks whether a given index corresponds to an existing task and throws a DukeException.
     * @param taskNumber String array of user input.
     * @throws DukeException If index given does not correspond to an existing task.
     */
    public void validateIndex(int taskNumber) throws DukeException{
        if(taskNumber > storedTasks.size() || taskNumber <= 0) {
            throw new DukeException("Your Majesty, there's no such agenda in my detailed records.");
        }
    }

    /**
     * Checks whether a valid number is given in user input.
     * @param command String array of user input.
     * @throws DukeException If no valid number is given in the user input.
     */
    public void validateConquerDelete(String[] command) throws DukeException {
        try {
            Integer.parseInt(sanitiseInput(command[1]));
        } catch (Exception err) {
            throw new DukeException("Please enter valid numbers after your command, Your Majesty.");
        }
    }

    /**
     * Removes leading white spaces in String input.
     * @param input String user input.
     * @return String that has been trimmed of leading white spaces.
     */
    public String sanitiseInput(String input) {
        return input.stripLeading();
    }

    /**
     * Prints all stored tasks.
     */
    public void printAllTasks() {
        Ui.printAllTasksUi(storedTasks);
    }

    /**
     * Creates and adds an instance of a Task object to list of stored tasks.
     * @param task String user input.
     * @return Task object that has been created.
     */
    public Task addTask(String task) {
        Task toBeReturned = null;
        try {
            validateScannerInput(task);
            String splitTask[] = task.split(" ", 2);
            Task newTask = null;
            validateAdd(splitTask);
            validateSlashCommands(splitTask);

            switch(splitTask[0].toLowerCase()) {
            case "todo":
                newTask = new ToDo(splitTask[1]);
                break;
            case "deadline":
                newTask = new Deadline(splitTask[1]);
                break;
            case "event":
                newTask = new Event(splitTask[1]);
                break;
            default:
                throw new DukeException("I'm afraid I do not understand that command, Your Majesty.");
            }
            if (newTask != null) {
                storedTasks.add(newTask);
                Ui.addedMessage(newTask, storedTasks.size());
            }
            toBeReturned = newTask;
        } catch (DukeException err) {
            Ui.dukeErrorMessage(err);
        }
        return toBeReturned;
    }

    /**
     * Marks a specific task as done.
     * @param command String array of user input.
     * @return Index of task that is to be marked as done.
     */
    public int conquerTask(String[] command) {
        int indexToBeReturned = -1;
        try {
            validateConquerDelete(command);
            int taskNumber = Integer.parseInt(sanitiseInput(command[1]));
            validateIndex(taskNumber);

            storedTasks.get(taskNumber - 1).markAsDone();
            indexToBeReturned = taskNumber;
            Ui.conqueredMessage(storedTasks.get(taskNumber - 1));
        } catch (DukeException err) {
            Ui.dukeErrorMessage(err);
        }
        return indexToBeReturned;
    }

    /**
     * Deletes a specific task.
     * @param command String array of user input.
     * @return Index of task that is to be deleted.
     */
    public int deleteTask(String[] command) {
        int indexToBeReturned = -1;
        try {
            validateConquerDelete(command);
            int taskNumber = Integer.parseInt(sanitiseInput(command[1]));
            validateIndex(taskNumber);

            Task deletedTask = storedTasks.get(taskNumber - 1);
            storedTasks.remove(deletedTask);
            indexToBeReturned = taskNumber;
            Ui.deletedMessage(deletedTask, storedTasks.size());
        } catch (DukeException err) {
            Ui.dukeErrorMessage(err);
        }
        return indexToBeReturned;
    }

    /**
     * Returns current list of stored tasks.
     * @return Current list of stored tasks.
     */
    public ArrayList<Task> getStoredTasks() {
        return this.storedTasks;
    }
}

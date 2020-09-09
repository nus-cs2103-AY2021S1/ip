package duke;

import java.util.ArrayList;

/**
 * Represents a list of stored tasks.
 */
public class TaskList {
    private ArrayList<Task> storedTasks;
    private Task addedTask;
    private int conqueredTaskIndex;
    private int deletedTaskIndex;

    /**
     * Creates an instance of a TaskList.
     *
     * @param storedTasks ArrayList of stored tasks loaded from file.
     */
    public TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
        this.conqueredTaskIndex = -1;
        this.deletedTaskIndex = -1;
    }
    
    /**
     * Checks whether an empty task description is given and throws a DukeException.
     *
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
     *
     * @param task String array of user input.
     * @throws DukeException If slash commands are incorrect.
     */
    public void validateSlashCommands(String[] task) throws DukeException {
        if (task[0].equalsIgnoreCase("deadline") && task[1].split("/by ", 2).length == 1) {
            throw new DukeException("Use /by for deadlines, Your Majesty.");
        } else if (task[0].equalsIgnoreCase("event") && task[1].split("/on ", 2).length == 1) {
            throw new DukeException("Use /on for events, Your Majesty.");
        }
    }

    /**
     * Checks whether a given index corresponds to an existing task and throws a DukeException.
     *
     * @param taskNumber String array of user input.
     * @throws DukeException If index given does not correspond to an existing task.
     */
    public void validateIndex(int taskNumber) throws DukeException {
        if (taskNumber > storedTasks.size() || taskNumber <= 0) {
            throw new DukeException("Your Majesty, there's no such agenda in my detailed records.");
        }
    }

    /**
     * Checks whether a valid number is given in user input.
     *
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
     *
     * @param input String user input.
     * @return String that has been trimmed of leading white spaces.
     */
    public String sanitiseInput(String input) {
        return input.stripLeading();
    }

    /**
     * Prints all stored tasks.
     */
    public String printAllTasks() {
        return Ui.printAllTasksUi(storedTasks);
    }

    public String deleteTaskAndGetMessage(String[] input) {
        Task deletedTask;
        try {
            validateConquerDelete(input);
            int taskNumber = Integer.parseInt(sanitiseInput(input[1]));
            validateIndex(taskNumber);
            this.deletedTaskIndex = taskNumber;
            deletedTask = storedTasks.get(taskNumber - 1);
            storedTasks.remove(deletedTask);
        } catch (DukeException err) {
            this.conqueredTaskIndex = -1;
            return Ui.dukeErrorMessage(err);
        }
        return Ui.deletedMessage(deletedTask, storedTasks.size());
    }
    
    public int getDeletedTaskIndex() {
        return this.deletedTaskIndex;
    }
    
    public String conquerTaskAndGetMessage(String[] input) {
        Task conqueredTask;
        try {
            validateConquerDelete(input);
            int taskNumber = Integer.parseInt(sanitiseInput(input[1]));
            validateIndex(taskNumber);
            this.conqueredTaskIndex = taskNumber;
            storedTasks.get(taskNumber - 1).markAsDone();
            conqueredTask = storedTasks.get(taskNumber - 1);
        } catch (DukeException err) {
            return Ui.dukeErrorMessage(err);
        }
        
        return Ui.conqueredMessage(conqueredTask);
    }
    
    public int getConqueredTaskIndex() {
        return this.conqueredTaskIndex;
    }

    /**
     * Returns current list of stored tasks.
     *
     * @return Current list of stored tasks.
     */
    public ArrayList<Task> getStoredTasks() {
        return this.storedTasks;
    }

    /**
     * Prints list of relevant tasks based on inputted key word.
     *
     * @param input String array of user input.
     */
    public String returnSearchedTask(String[] input) {
        ArrayList<Task> relevantTasks = new ArrayList<>();
        String searchWord = input[1];
        for (Task t : storedTasks) {
            if (t.getDescription().contains(searchWord)) {
                relevantTasks.add(t);
            }
        }
        return Ui.printRelevantTasksUi(relevantTasks);
    }
    
    public String addTodoAndGetMessage(String[] input) {
       try {
           validateAdd(input);
           Task addedToDo = new ToDo(input[1]);
           storedTasks.add(addedToDo);
           this.addedTask = addedToDo;
       } catch (DukeException err) {
           return Ui.dukeErrorMessage(err);
       }
       return Ui.addedMessage(this.addedTask, this.storedTasks.size());
    }
    
    
    public String addDeadlineAndGetMessage(String[] input) {
        try {
            validateAdd(input);
            validateSlashCommands(input);
            Task addedDeadline = new Deadline(input[1]);
            storedTasks.add(addedDeadline);
            this.addedTask = addedDeadline;
        } catch (DukeException err) {
            return Ui.dukeErrorMessage(err);
        }
        return Ui.addedMessage(this.addedTask, this.storedTasks.size());
    }

    public String addEventAndGetMessage(String[] input) {
        try {
            validateAdd(input);
            validateSlashCommands(input);
            Task addedEvent = new Event(input[1]);
            storedTasks.add(addedEvent);
            this.addedTask = addedEvent;
        } catch (DukeException err) {
            return Ui.dukeErrorMessage(err);
        }
        return Ui.addedMessage(this.addedTask, this.storedTasks.size());
    }

    public Task getAddedTask() {
        return this.addedTask;
    }
}
package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of stored tasks.
 */
public class TaskList {
    private ArrayList<Task> storedTasks;
    private Task addedTask;
    private int conqueredTaskIndex;
    private int deletedTaskIndex;
    private final int UNINITIALISED_INDEX = -1;

    /**
     * Creates an instance of a TaskList.
     *
     * @param storedTasks ArrayList of stored tasks loaded from file.
     */
    public TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
        this.conqueredTaskIndex = UNINITIALISED_INDEX;
        this.deletedTaskIndex = UNINITIALISED_INDEX;
    }

    /**
     * Checks whether an empty task description is given and throws a DukeException.
     *
     * @param task String array of user input.
     * @throws DukeException If task description is empty.
     */
    public void validateAdd(String[] task) throws DukeException {
        boolean isMissingDetails = task.length == 1;
        boolean hasEmptySpaceAsDetails = task.length > 1 && task[1].trim().length() == 0;

        if (isMissingDetails || hasEmptySpaceAsDetails) {
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
        boolean isDeadline = task[0].equalsIgnoreCase("deadline");
        boolean isEvent = task[0].equalsIgnoreCase("event");
        boolean containsKeywordBy = task[1].contains("/by");
        boolean containsKeywordOn =  task[1].contains("/on");

        if (isDeadline && !containsKeywordBy) {
            throw new DukeException("Use /by for deadlines, Your Majesty.");
        } else if (isEvent && !containsKeywordOn) {
            throw new DukeException("Use /on for events, Your Majesty.");
        }
        
        boolean hasEmptyDeadlineDescription = task[1].split(" ", 2)[0].equals("/by");
        boolean hasEmptyEventDescription = task[1].split(" ", 2)[0].equals("/on");
        
        if (isDeadline && containsKeywordBy && hasEmptyDeadlineDescription) {
            throw new DukeException("Do give me more details about this deadline, Your Majesty.");
        } 
        if (isEvent && containsKeywordOn && hasEmptyEventDescription) {
            throw new DukeException("Do give me more details about this event, Your Majesty.");
        }
    }

    /**
     * Checks whether a given index corresponds to an existing task and throws a DukeException.
     *
     * @param taskNumber String array of user input.
     * @throws DukeException If index given does not correspond to an existing task.
     */
    public void validateIndex(int taskNumber) throws DukeException {
        boolean isExceedingTotalNumber = taskNumber > storedTasks.size();
        boolean isNegativeNumber = taskNumber <= 0;
        if (isExceedingTotalNumber || isNegativeNumber) {
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
        return Ui.printListOfTasksUi(storedTasks);
    }

    /**
     * Deletes a specified task and returns Duke's response as a String.
     *
     * @param input User's input.
     * @return Duke's response as a String.
     */
    public String deleteTaskAndGetMessage(String[] input) {
        Task deletedTask;
        try {
            validateConquerDelete(input);
            int taskNumber = Integer.parseInt(sanitiseInput(input[1]));
            validateIndex(taskNumber);
            this.deletedTaskIndex = taskNumber;
            deletedTask = storedTasks.remove(taskNumber - 1);
        } catch (DukeException err) {
            this.deletedTaskIndex = UNINITIALISED_INDEX;
            return Ui.dukeErrorMessage(err);
        }
        return Ui.deletedMessage(deletedTask, storedTasks.size());
    }

    /**
     * Returns the index of the most recently deleted task.
     *
     * @return Index of the most recently deleted task.
     */
    public int getDeletedTaskIndex() {
        return this.deletedTaskIndex;
    }

    /**
     * Marks a specified task as done and returns Duke's response as a String.
     *
     * @param input User's input.
     * @return Duke's response as a String.
     */
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
            this.conqueredTaskIndex = UNINITIALISED_INDEX;
            return Ui.dukeErrorMessage(err);
        }

        return Ui.conqueredMessage(conqueredTask);
    }

    /**
     * Returns index of the most recently conquered task.
     *
     * @return Index of the most recently conquered task.
     */
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
        try {
            String searchWord = input[1];
            for (Task t : storedTasks) {
                boolean containsSearchWord = t.getDescription().contains(searchWord);
                if (containsSearchWord) {
                    relevantTasks.add(t);
                }
            }
        } catch (Exception err) {
            return Ui.dukeErrorMessage(new DukeException("Please enter a search term, Your Majesty."));
        }

        return Ui.printListOfTasksUi(relevantTasks);
    }

    /**
     * Adds a specified ToDo to the list and returns Duke's response as a String.
     *
     * @param input User's input.
     * @return Duke's response as a String.
     */
    public String addTodoAndGetMessage(String[] input) {
        try {
            validateAdd(input);
            handleAddTask(new ToDo(input[1]));
        } catch (DukeException err) {
            return Ui.dukeErrorMessage(err);
        }
        return Ui.addedMessage(this.addedTask, this.storedTasks.size());
    }

    /**
     * Adds a specified task to the list.
     *
     * @param t Specified task to be added.
     */
    public void handleAddTask(Task t) {
        storedTasks.add(t);
        this.addedTask = t;
    }

    /**
     * Adds a specified Deadline to the list and returns Duke's response as a String.
     *
     * @param input User's input.
     * @return Duke's response as a String.
     */
    public String addDeadlineAndGetMessage(String[] input) {
        try {
            validateAdd(input);
            validateSlashCommands(input);
            handleAddTask(new Deadline(input[1]));
        } catch (DukeException err) {
            return Ui.dukeErrorMessage(err);
        }
        return Ui.addedMessage(this.addedTask, this.storedTasks.size());
    }

    /**
     * Adds a specified Event to the list and returns Duke's response as a String.
     *
     * @param input User's input.
     * @return Duke's response as a String.
     */
    public String addEventAndGetMessage(String[] input) {
        try {
            validateAdd(input);
            validateSlashCommands(input);
            handleAddTask(new Event(input[1]));
        } catch (DukeException err) {
            return Ui.dukeErrorMessage(err);
        }
        return Ui.addedMessage(this.addedTask, this.storedTasks.size());
    }

    /**
     * Returns the most recently added task.
     *
     * @return Most recently added task.
     */
    public Task getAddedTask() {
        return this.addedTask;
    }

    public String getTasksForADate(String[] input) {
        ArrayList<Task> relevantTasks = new ArrayList<>();
        try {
            LocalDate date = LocalDate.parse(input[1]);
            for (Task t : storedTasks) {
                if (t.getDate() != null && t.getDate().equals(date)) {
                    relevantTasks.add(t);
                }
            }
        } catch (Exception err) {
            return Ui.dukeErrorMessage(new DukeException("Please input a valid date in YYYY-MM-DD format, Your Majesty."));
        }
        return Ui.printListOfTasksUi(relevantTasks);
    }
}
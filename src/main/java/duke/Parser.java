package duke;

import java.util.ArrayList;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private static Ui ui = new Ui();
    private static final String EMPTY_COMMAND = "";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String NEW_TODO_COMMAND = "todo";
    private static final String NEW_DEADLINE_COMMAND = "deadline";
    private static final String NEW_EVENT_COMMAND = "event";

    private static final int TODO_DESCRIPTION_START_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    private static final int EVENT_DESCRIPTION_START_INDEX = 6;
    private static final int DATE_START_INDEX = 4;

    /**
     * Executes a done command.
     *
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @param isRunningOnGui A boolean that returns true if the application is running on GUI.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String executeDoneCommand(
            String[] userInputWords, TaskList tasks, boolean isRunningOnGui) throws DukeException {
        int index = Integer.parseInt(userInputWords[1]);
        if (index > tasks.size() || index < 0) {
            throw new DukeException("That task number does not exist.");
        }
        tasks.get(index - 1).setDone();
        Task t = tasks.get(index - 1);
        ArrayList<Task> tasksCopy = tasks.clone();
        Storage.store(tasksCopy);
        return ui.done(t, isRunningOnGui);
    }

    /**
     * Executes a delete command.
     *
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @param isRunningOnGui A boolean that returns true if the application is running on GUI.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String executeDeleteCommand(
            String[] userInputWords, TaskList tasks, boolean isRunningOnGui) throws DukeException {
        int index = Integer.parseInt(userInputWords[1]);
        if (index > tasks.size() || index < 0) {
            throw new DukeException("That task number does not exist.");
        }
        Task deletedTask = tasks.remove(index - 1);
        ArrayList<Task> tasksCopy = tasks.clone();
        Storage.store(tasksCopy);
        return ui.delete(deletedTask, tasks.size(), isRunningOnGui);
    }

    /**
     * Executes a find command.
     *
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @param isRunningOnGui A boolean that returns true if the application is running on GUI.
     * @return Returns a response to the user.
     */
    public static String executeFindCommand(
            String[] userInputWords, TaskList tasks, boolean isRunningOnGui) {
        String keyWord = userInputWords[1];
        ArrayList<Task> foundTasks = tasks.find(keyWord);
        return ui.find(foundTasks, isRunningOnGui);
    }

    /**
     * Command to create a ToDo.
     *
     * @param input Line of user input.
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @param isRunningOnGui A boolean that returns true if the application is running on GUI.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String createToDoCommand(
            String input, String[] userInputWords, TaskList tasks, boolean isRunningOnGui) throws DukeException {
        if (userInputWords.length <= 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        ToDo newToDo = new ToDo(input.substring(TODO_DESCRIPTION_START_INDEX));
        tasks.add(newToDo);
        ArrayList<Task> tasksCopy = tasks.clone();
        Storage.store(tasksCopy);
        return ui.add(newToDo, tasks.size(), isRunningOnGui);
    }

    /**
     * Command to create a deadline.
     *
     * @param input Line of user input.
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @param isRunningOnGui A boolean that returns true if the application is running on GUI.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String createDeadlineCommand(
            String input, String[] userInputWords, TaskList tasks, boolean isRunningOnGui) throws DukeException {
        if (userInputWords.length <= 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        int index = input.indexOf("/");
        if (index == -1) {
            throw new DukeException("Please include the date of the deadline!");
        }
        String deadlineDescription = input.substring(DEADLINE_DESCRIPTION_START_INDEX, index - 1);
        String deadlineDate = input.substring(index + DATE_START_INDEX);
        try {
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
            tasks.add(newDeadline);
            ArrayList<Task> tasksCopy = tasks.clone();
            Storage.store(tasksCopy);
            return ui.add(newDeadline, tasks.size(), isRunningOnGui);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid YYYY-MM-DD date format!");
        }
    }

    /**
     * Command to create an event.
     *
     * @param input Line of user input.
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @param isRunningOnGui A boolean that returns true if the application is running on GUI.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String createEventCommand(
            String input, String[] userInputWords, TaskList tasks, boolean isRunningOnGui) throws DukeException {
        if (userInputWords.length <= 1) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        int ind = input.indexOf("/");
        if (ind == -1) {
            throw new DukeException("Please include the date of the event!");
        }
        String eventDescription = input.substring(EVENT_DESCRIPTION_START_INDEX, ind - 1);
        String eventDate = input.substring(ind + DATE_START_INDEX);
        try {
            Event newEvent = new Event(eventDescription, eventDate);
            tasks.add(newEvent);
            ArrayList<Task> tasksCopy = tasks.clone();
            Storage.store(tasksCopy);
            return ui.add(newEvent, tasks.size(), isRunningOnGui);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid YYYY-MM-DD date format!");
        }
    }

    /**
     * Processes each line of user input in accordance to the user's TaskList.
     * Triggers the appropriate response to the user via the Ui class methods.
     *
     * @param inputted Line of user input.
     * @param tasks The user's current task list.
     * @param isRunningOnGui A boolean that returns true if the application is running on GUI.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String parse(String inputted, TaskList tasks, boolean isRunningOnGui) throws DukeException {
        String input = inputted.trim();
        if (input.equals(EMPTY_COMMAND)) {
            throw new DukeException("Please input a proper command!");
        }
        String[] userInputWords = input.split(" ");
        if (input.equals(BYE_COMMAND)) {
            return ui.goodbye(isRunningOnGui);
        } else if (input.equals(LIST_COMMAND)) {
            return ui.list(tasks, isRunningOnGui);
        } else if (userInputWords.length == 2 && userInputWords[0].equals(DONE_COMMAND)) {
            return executeDoneCommand(userInputWords, tasks, isRunningOnGui);
        } else if (userInputWords.length == 2 && userInputWords[0].equals(DELETE_COMMAND)) {
            return executeDeleteCommand(userInputWords, tasks, isRunningOnGui);
        } else if (userInputWords.length == 2 && userInputWords[0].equals(FIND_COMMAND)) {
            return executeFindCommand(userInputWords, tasks, isRunningOnGui);
        } else {
            switch (userInputWords[0]) {
            case NEW_TODO_COMMAND:
                return createToDoCommand(input, userInputWords, tasks, isRunningOnGui);
            case NEW_DEADLINE_COMMAND:
                return createDeadlineCommand(input, userInputWords, tasks, isRunningOnGui);
            case NEW_EVENT_COMMAND:
                return createEventCommand(input, userInputWords, tasks, isRunningOnGui);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}

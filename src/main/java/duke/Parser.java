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
    private static final String SHOW_TAGS_COMMAND = "show tags";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String FIND_TAG_COMMAND = "findtag";
    private static final String REMOVE_TAG_COMMAND = "removetag";
    private static final String TAG_COMMAND = "tag";
    private static final String NEW_TODO_COMMAND = "todo";
    private static final String NEW_DEADLINE_COMMAND = "deadline";
    private static final String NEW_EVENT_COMMAND = "event";

    private static final int TODO_DESCRIPTION_START_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    private static final int EVENT_DESCRIPTION_START_INDEX = 6;
    private static final int DATE_START_INDEX = 4;
    private static final int INVALID_INDEX = -1;

    /**
     * Executes a done command.
     *
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String executeDoneCommand(
            String[] userInputWords, TaskList tasks) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(userInputWords[1]);
        } catch (Exception e) {
            throw new DukeException("Please input a valid task number.");
        }
        tasks.get(index - 1).setDone();
        Task t = tasks.get(index - 1);
        ArrayList<Task> tasksCopy = tasks.clone();
        Storage.store(tasksCopy);
        return ui.done(t);
    }

    /**
     * Executes a delete command.
     *
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String executeDeleteCommand(
            String[] userInputWords, TaskList tasks) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(userInputWords[1]);
        } catch (Exception e) {
            throw new DukeException("Please input a valid task number.");
        }
        if (index > tasks.size() || index < 0) {
            throw new DukeException("That task number does not exist.");
        }
        Task deletedTask = tasks.remove(index - 1);
        ArrayList<Task> tasksCopy = tasks.clone();
        Storage.store(tasksCopy);
        return ui.delete(deletedTask, tasks.size());
    }

    /**
     * Executes a tag command.
     *
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect. tag 1 HAPPY
     */
    public static String executeTagCommand(
            String[] userInputWords, TaskList tasks) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(userInputWords[1]);
        } catch (Exception e) {
            throw new DukeException("Please input a valid task number.");
        }
        if (index > tasks.size() || index < 0) {
            throw new DukeException("That task number does not exist.");
        }
        Tag tag = Tag.stringToTag(userInputWords[2]);
        tasks.get(index - 1).addTag(tag);
        Task t = tasks.get(index - 1);
        ArrayList<Task> tasksCopy = tasks.clone();
        Storage.store(tasksCopy);
        return ui.tag(t, tag);
    }

    /**
     * Executes a remove tag command.
     *
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect. tag 1 HAPPY
     */
    public static String executeRemoveTagCommand(
            String[] userInputWords, TaskList tasks) throws DukeException {
        int index = Integer.parseInt(userInputWords[1]);
        String tagToRemove = userInputWords[2];
        if (index > tasks.size() || index < 0) {
            throw new DukeException("That task number does not exist.");
        }
        Task t = tasks.get(index - 1);
        boolean isTagRemoved = t.removeTag(tagToRemove) == 1;
        ArrayList<Task> tasksCopy = tasks.clone();
        Storage.store(tasksCopy);
        return ui.removeTag(t, isTagRemoved, tagToRemove);
    }

    /**
     * Executes a find command.
     *
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @return Returns a response to the user.
     */
    public static String executeFindCommand(
            String[] userInputWords, TaskList tasks) {
        String keyWord = userInputWords[1];
        ArrayList<Task> foundTasks = tasks.find(keyWord);
        return ui.find(foundTasks);
    }

    /**
     * Executes a find tag command.
     *
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @return Returns a response to the user.
     */
    public static String executeFindTagCommand(
            String[] userInputWords, TaskList tasks) {
        String keyTag = userInputWords[1];
        ArrayList<Task> foundTasks = tasks.findTag(keyTag);
        return ui.findTag(foundTasks);
    }

    /**
     * Command to create a ToDo.
     *
     * @param input Line of user input.
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String createToDoCommand(
            String input, String[] userInputWords, TaskList tasks) throws DukeException {
        if (userInputWords.length <= 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        ToDo newToDo = new ToDo(input.substring(TODO_DESCRIPTION_START_INDEX));
        tasks.add(newToDo);
        ArrayList<Task> tasksCopy = tasks.clone();
        Storage.store(tasksCopy);
        return ui.add(newToDo, tasks.size());
    }

    /**
     * Command to create a deadline.
     *
     * @param input Line of user input.
     * @param userInputWords Array of words from user input.
     * @param tasks The user's current task list.
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String createDeadlineCommand(
            String input, String[] userInputWords, TaskList tasks) throws DukeException {
        if (userInputWords.length <= 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        int index = input.indexOf("/");
        if (index == INVALID_INDEX) {
            throw new DukeException("Please include the date of the deadline!");
        }
        String deadlineDescription = input.substring(DEADLINE_DESCRIPTION_START_INDEX, index - 1);
        String deadlineDate = input.substring(index + DATE_START_INDEX);
        try {
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
            tasks.add(newDeadline);
            ArrayList<Task> tasksCopy = tasks.clone();
            Storage.store(tasksCopy);
            return ui.add(newDeadline, tasks.size());
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
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String createEventCommand(
            String input, String[] userInputWords, TaskList tasks) throws DukeException {
        if (userInputWords.length <= 1) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        int index = input.indexOf("/");
        if (index == INVALID_INDEX) {
            throw new DukeException("Please include the date of the event!");
        }
        String eventDescription = input.substring(EVENT_DESCRIPTION_START_INDEX, index - 1);
        String eventDate = input.substring(index + DATE_START_INDEX);
        try {
            Event newEvent = new Event(eventDescription, eventDate);
            tasks.add(newEvent);
            ArrayList<Task> tasksCopy = tasks.clone();
            Storage.store(tasksCopy);
            return ui.add(newEvent, tasks.size());
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
     * @return Returns a response to the user.
     * @throws DukeException  If user input is incorrect.
     */
    public static String parse(String inputted, TaskList tasks) throws DukeException {
        String input = inputted.trim();
        assert input != null;
        if (input.equals(EMPTY_COMMAND)) {
            throw new DukeException("Please input a proper command!");
        }
        //userInputWords length reflects the number of words in the input command.
        String[] userInputWords = input.split(" ");
        if (input.equals(BYE_COMMAND)) {
            return ui.goodbye();
        } else if (input.equals(LIST_COMMAND)) {
            return ui.list(tasks);
        } else if (userInputWords.length == 2 && userInputWords[0].equals(DONE_COMMAND)) {
            return executeDoneCommand(userInputWords, tasks);
        } else if (userInputWords.length == 2 && userInputWords[0].equals(DELETE_COMMAND)) {
            return executeDeleteCommand(userInputWords, tasks);
        } else if (userInputWords.length == 2 && userInputWords[0].equals(FIND_COMMAND)) {
            return executeFindCommand(userInputWords, tasks);
        } else if (userInputWords.length == 2 && userInputWords[0].equals(FIND_TAG_COMMAND)) {
            return executeFindTagCommand(userInputWords, tasks);
        } else if (userInputWords.length == 3 && userInputWords[0].equals(TAG_COMMAND)) {
            return executeTagCommand(userInputWords, tasks);
        } else if (userInputWords.length == 3 && userInputWords[0].equals(REMOVE_TAG_COMMAND)) {
            return executeRemoveTagCommand(userInputWords, tasks);
        } else if (userInputWords.length == 2 && input.equals(SHOW_TAGS_COMMAND)) {
            return ui.showTags(tasks);
        } else {
            switch (userInputWords[0]) {
            case NEW_TODO_COMMAND:
                return createToDoCommand(input, userInputWords, tasks);
            case NEW_DEADLINE_COMMAND:
                return createDeadlineCommand(input, userInputWords, tasks);
            case NEW_EVENT_COMMAND:
                return createEventCommand(input, userInputWords, tasks);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}

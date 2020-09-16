import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The command class handles any command related situations.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class Command {

    private Ui ui;
    private Parser parser;
    private boolean isTerminated;

    Command() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.isTerminated = false;
    }

    /**
     * Getter method for isTerminated attribute.
     *
     * @return If Duke should be terminated.
     */
    public boolean isTerminated() {
        return this.isTerminated;
    }

    /**
     * Executes the respective command based on accepted keywords.
     *
     * @param userInput The input given by the user.
     * @param tasks The current list of tasks.
     * @param storage The current storage object.
     * @return The relevant response.
     * @throws DukeException If any Duke-type exceptions are thrown.
     */
    public String execute(String userInput, TaskList tasks, Storage storage) throws DukeException {
        // determining user input type via the first word
        String keyWord = getKeyWord(userInput).toUpperCase();
        Keyword commandType = Keyword.valueOf(keyWord);
        switch (commandType) {
        case WELCOME:
            return welcomeCommand();
        case BYE:
            this.isTerminated = true;
            return byeCommand();
        case HELP:
            return helpCommand();
        case LIST:
            return listCommand(tasks);
        case DONE:
            return doneCommand(userInput, tasks, storage);
        case DELETE:
            return deleteCommand(tasks, userInput, ui, storage);
        case TODO:
        case EVENT:
        case DEADLINE:
            return addTaskCommand(userInput, commandType, tasks, storage);
        case FIND:
            return findCommand(tasks, userInput);
        case SORTDES:
            return sortByDesCommand(tasks);
        default:
            return ui.printErrorMessage(new DukeException("Unknown execution error."));
        }
    }

    /**
     * Splits the user input by white spaces.
     *
     * @param userInput The input from user.
     * @return The split string in array form.
     */
    public String[] splitUserInputByWhiteSpace(String userInput) {
        return userInput.split(" ");
    }

    /**
     * Shows the welcome command response.
     *
     * @return The expected welcome response.
     */
    public String welcomeCommand() {
        return ui.printWelcome();
    }

    /**
     * Calls for the required ui methods when list keyword is used.
     *
     * @param tasks The current list of tasks.
     * @return The list of tasks(if applicable).
     */
    public String listCommand(TaskList tasks) {
        if (tasks.getNoOfTasks() == 0) {
            return ui.printNoPastTasks();
        } else {
            return ui.printPastTasks(tasks);
        }
    }

    /**
     * Shows the bye command response.
     *
     * @return The expected bye response.
     */
    public String byeCommand() {
        return ui.printGoodbye();
    }

    /**
     * Shows the help command response.
     *
     * @return The list of help commands.
     */
    public String helpCommand() {
        return ui.printHelp();
    }

    /**
     * Calls for the required methods to handle a task deleting situation.
     *
     * @param tasks The current list of tasks.
     * @param userInput The user input.
     * @param ui The UI object used.
     * @param storage The storage used.
     * @return The deleted task message.
     * @throws DeleteFailureException If the deleting process fails.
     */
    public String deleteCommand(TaskList tasks, String userInput, Ui ui, Storage storage)
            throws DeleteFailureException {
        String[] splitInput = splitUserInputByWhiteSpace(userInput);
        String toReturn = tasks.deleteTask(tasks, splitInput, ui);
        storage.updateTaskFile(tasks);
        return toReturn;
    }

    /**
     * Calls for the required methods to handle a done situation.
     *
     * @param userInput The user input.
     * @param tasks The current list of tasks.
     * @param storage The storage used.
     * @return The done task message.
     */
    public String doneCommand(String userInput, TaskList tasks, Storage storage) {
        // checks the formatting of user input
        String[] splitInput = splitUserInputByWhiteSpace(userInput);
        if (splitInput.length > 2) {
            return ui.printErrorMessage(new InvalidFormatException("Please use the correct format:"
                    + " done <task number>"));
        }
        String toReturn = tasks.doneTask(splitInput, tasks, ui);
        storage.updateTaskFile(tasks);
        return toReturn;
    }

    /**
     * Calls for the required methods to handle a task adding situation.
     *
     * @param userInput The user input.
     * @param keyWord The type of task.
     * @param tasks The current list of tasks.
     * @param storage The storage used.
     * @return The added task message.
     * @throws DukeException If any Duke-type exceptions are encountered.
     */
    public String addTaskCommand(String userInput, Keyword keyWord, TaskList tasks, Storage storage)
            throws DukeException {
        String[] splitInput = splitUserInputByWhiteSpace(userInput);
        String[] data = splitIntoDesDateTime(splitInput);
        String toReturn = tasks.addTask(data, keyWord, tasks, ui);
        storage.updateTaskFile(tasks);
        return toReturn;
    }

    /**
     * Obtains the keyword in the user input to determine command needed.
     *
     * @param input The given keyword from user input.
     * @return The keyword if it is valid.
     * @throws InvalidKeyWordException If the keyword not valid.
     */
    public String getKeyWord(String input) throws InvalidKeyWordException {
        String[] splitInput = splitUserInputByWhiteSpace(input);
        if (parser.isValidKeyWord(splitInput[0])) {
            return splitInput[0];
        } else {
            throw new InvalidKeyWordException("\u2639 OOPS!!! I'm sorry, but I don't know what "
                    + "that means :-(");
        }
    }

    /**
     * Processes the user input (for task commands only).
     *
     * @param inputSplitByWhiteSpace Contains strings for task type, description, date/time
     *     if applicable.
     * @return An array with index 0 as description, 1 as date and 2 as time (if applicable)
     * @throws InvalidFormatException If the input format is wrong.
     * @throws EmptyTaskException If input task is not tagged with description.
     */

    public static String[] splitIntoDesDateTime(String[] inputSplitByWhiteSpace)
            throws InvalidFormatException, EmptyTaskException {
        String taskType = inputSplitByWhiteSpace[0];
        Keyword taskTypeEnum = Keyword.valueOf(taskType.toUpperCase());
        if (inputSplitByWhiteSpace.length <= 1) {
            throw new EmptyTaskException("\u2639 OOPS!!! The description of a " + taskType + " cannot "
                    + "be empty.");
        } else {
            switch (taskTypeEnum) {
            case TODO:
                return handleToDoSplit(inputSplitByWhiteSpace);
            case DEADLINE:
                return handleDeadlineSplit(inputSplitByWhiteSpace);
            case EVENT:
                return handleEventSplit(inputSplitByWhiteSpace);
            default:
                return new String[]{};
            }
        }
    }

    /**
     * Splits an event-type input into readable data for Duke.
     *
     * @param inputSplitByWhiteSpace Contains strings for task type, description, date/time
     *     if applicable.
     * @return An array with index 0 as description, 1 as date and 2 as time (if applicable)
     * @throws InvalidFormatException If the input format is wrong.
     */
    public static String[] handleEventSplit(String[] inputSplitByWhiteSpace)
            throws InvalidFormatException {
        Parser parser = new Parser();
        String des = "";
        String date = "";
        String time = "";
        boolean toBreak = false;
        int pivotIndex = 0;

        for (int i = 1; i < inputSplitByWhiteSpace.length; i++) {
            if (inputSplitByWhiteSpace[i].equals("/at")) {
                // sets the breaking point of input
                toBreak = true;
                pivotIndex = i;
                break;
            } else {
                // before breaking point
                des += inputSplitByWhiteSpace[i] + " ";
            }
        }

        String[] dateTimeArr = Arrays.copyOfRange(inputSplitByWhiteSpace,
                pivotIndex + 1, inputSplitByWhiteSpace.length);

        // after breaking point, there should only be a maximum of 2 items remaining
        // first item is date, second item is time
        if (dateTimeArr.length > 2) {
            throw new InvalidFormatException("Please enter a valid format");
        } else {
            if (dateTimeArr.length == 1) {
                // only has date but no time
                date = dateTimeArr[0];
            } else if (dateTimeArr.length == 2) {
                // has date and time
                date = dateTimeArr[0];
                time = dateTimeArr[1];
            }
        }
        // index 0 is description, index 1 is date, index 2 is time
        if (toBreak && parser.isValidDate(date) && parser.isValidTime(time)) {
            return new String[]{des, date, time};
        } else {
            // exception is thrown when the format is off, since there is no breaking point, or
            // if the input date or time is wrong
            throw new InvalidFormatException("Please use the correct format and include the "
                    + "keyword: /at");
        }
    }

    /**
     * Splits an deadline-type input into readable data for Duke.
     *
     * @param inputSplitByWhiteSpace Contains strings for task type, description, date/time
     *     if applicable.
     * @return An array with index 0 as description, 1 as date and 2 as time (if applicable)
     * @throws InvalidFormatException If the input format is wrong.
     */
    public static String[] handleDeadlineSplit(String[] inputSplitByWhiteSpace)
            throws InvalidFormatException {
        Parser parser = new Parser();
        String des = "";
        String date = "";
        String time = "";
        boolean toBreak = false;
        int pivotIndex = 0;

        for (int i = 1; i < inputSplitByWhiteSpace.length; i++) {
            if (inputSplitByWhiteSpace[i].equals("/by")) {
                // sets the breaking point of input
                toBreak = true;
                pivotIndex = i;
                break;
            } else {
                // before breaking point
                des += inputSplitByWhiteSpace[i] + " ";
            }
        }

        String[] dateTimeArr = Arrays.copyOfRange(inputSplitByWhiteSpace,
                pivotIndex + 1, inputSplitByWhiteSpace.length);

        // after breaking point, there should only be a maximum of 2 items remaining
        // first item is date, second item is time
        if (dateTimeArr.length > 2) {
            throw new InvalidFormatException("Please enter a valid format");
        } else {
            if (dateTimeArr.length == 1) {
                // only has date but no time
                date = dateTimeArr[0];
            } else if (dateTimeArr.length == 2) {
                // has date and time
                date = dateTimeArr[0];
                time = dateTimeArr[1];
            }
        }
        // index 0 is description, index 1 is date, index 2 is time
        if (toBreak && parser.isValidDate(date) && parser.isValidTime(time)) {
            return new String[]{des, date, time};
        } else {
            // exception is thrown when the format is off, since there is no breaking point, or
            // if the input date or time is wrong
            throw new InvalidFormatException("Please use the correct format and include the "
                    + "keyword: /by");
        }
    }

    /**
     * Splits an to-do-type input into readable data for Duke.
     *
     * @param inputSplitByWhiteSpace Contains strings for task type, description, date/time
     *     if applicable.
     * @return An array with index 0 as description.
     */
    public static String[] handleToDoSplit(String[] inputSplitByWhiteSpace) {
        String des = "";
        for (int i = 1; i < inputSplitByWhiteSpace.length; i++) {
            des += inputSplitByWhiteSpace[i];
            if (i != inputSplitByWhiteSpace.length - 1) {
                // adds a white space in between each word
                des += " ";
            }
        }
        // index 0 is description
        return new String[]{des};
    }

    /**
     * Calls for the required methods to find a task.
     *
     * @param tasks The current list of tasks.
     * @param userInput The user input.
     * @return The found tasks.
     */
    public String findCommand(TaskList tasks, String userInput) {
        String[] splitInput = splitUserInputByWhiteSpace(userInput);
        String desToFind = "";
        for (int i = 1; i < splitInput.length; i++) {
            desToFind += splitInput[i];
            if (i != splitInput.length - 1) {
                desToFind += " ";
            }
        }
        return ui.printFoundTasks(tasks, desToFind);
    }

    /**
     * Calls for the required methods to sort tasks in alphabetical order
     *
     * @param tasks The current list of tasks.
     * @return String of tasks sorted.
     */
    public String sortByDesCommand(TaskList tasks) {
        Comparator<Task> taskComparator = (thisTask, otherTask) -> {
            String thisDes = thisTask.description;
            String otherDes = otherTask.description;
            int limit = Math.min(thisDes.length(), otherDes.length());
            for (int i = 0; i < limit; i++) {
                if (thisDes.charAt(i) < otherDes.charAt(i)) {
                    return -1;
                } else if (thisDes.charAt(i) > otherDes.charAt(i)) {
                    return 1;
                }
            }
            return thisDes.length() > otherDes.length() ? -1
                    : thisDes.length() < otherDes.length() ? 1 : 0;
        };

        PriorityQueue<Task> sortedTasks = new PriorityQueue<>(tasks.getNoOfTasks(), taskComparator);
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            sortedTasks.add(tasks.getTask(i));
        }

        return ui.printSortByDes(sortedTasks, tasks.getNoOfTasks());
    }

}


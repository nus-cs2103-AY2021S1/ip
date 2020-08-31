import java.util.Arrays;

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
        //determining user input type via the first word
        String[] splitInput = splitUserInput(userInput);
        String keyWord = getKeyWord(userInput);
        if (keyWord.equals("welcome")) {
            return welcomeCommand();
        } else if (keyWord.equals("bye")) {
            this.isTerminated = true;
            return byeCommand();
        } else if (keyWord.equals("help")) {
            return helpCommand();
        } else if (keyWord.equals("list")) {
            return listCommand(tasks);
        } else if (keyWord.equals("done")) {
            return doneCommand(splitInput, tasks, storage);
        } else if (keyWord.equals("delete")) {
            return deleteCommand(tasks, splitInput, ui, storage);
        } else if (keyWord.equals("todo") || keyWord.equals("deadline")
                || keyWord.equals("event")) {
            return addTaskCommand(splitInput, keyWord, tasks, storage);
        } else if (keyWord.equals("find")) {
            return findCommand(tasks, splitInput);
        } else {
            return ui.showErrorMessage(new DukeException("Unknown execution error.").getMessage());
        }
    }

    public String[] splitUserInput(String userInput) {
        return userInput.split(" ");
    }

    public String welcomeCommand() {
        return ui.showWelcome();
    }

    /**
     * Calls for the required ui methods when list keyword is used.
     *
     * @param tasks The current list of tasks.
     * @return The list of tasks(if applicable).
     */
    public String listCommand(TaskList tasks) {
        if (tasks.noOfTasks() == 0) {
            return ui.showNoPastTasks();
        } else {
            return ui.showPastTasks(tasks);
        }
    }

    public String byeCommand() {
        return ui.showGoodbye();
    }

    public String helpCommand() {
        return ui.showHelp();
    }

    /**
     * Calls for the required methods to handle a task deleting situation.
     *
     * @param tasks The current list of tasks.
     * @param splitInput A valid string array is of length 2, index 1 being the task number to
     *     denote as done.
     * @param ui The UI object used.
     * @param storage The storage used.
     * @return The deleted task message.
     * @throws DeleteFailureException If the deleting process fails.
     */
    public String deleteCommand(TaskList tasks, String[] splitInput, Ui ui, Storage storage)
            throws DeleteFailureException {
        String toReturn = tasks.deleteTask(tasks, splitInput, ui);
        storage.updateTaskFile(tasks);
        return toReturn;
    }

    /**
     * Calls for the required methods to handle a done situation.
     *
     * @param splitInput A valid string array is of length 2, index 1 being the task number
     *     to denote as done.
     * @param tasks The current list of tasks.
     * @param storage The storage used.
     * @return The done task message.
     * @throws InvalidFormatException If the input format is wrong.
     */
    public String doneCommand(String[] splitInput, TaskList tasks, Storage storage)
            throws InvalidFormatException {
        //checks the formatting of user input
        if (splitInput.length > 2) {
            return ui.showErrorMessage(new InvalidFormatException("Please use the correct format:"
                    + " done <task number>").getMessage());
        }
        String toReturn = tasks.doneTask(splitInput, tasks, ui);
        storage.updateTaskFile(tasks);
        return toReturn;
    }

    /**
     * Calls for the required methods to handle a task adding situation.
     *
     * @param splitInput A valid string array is of length 2, index 1 being the task number to
     *     denote as done.
     * @param keyWord The type of task.
     * @param tasks The current list of tasks.
     * @param storage The storage used.
     * @return The added task message.
     * @throws DukeException If any Duke-type exceptions are encountered.
     */
    public String addTaskCommand(String[] splitInput, String keyWord, TaskList tasks, Storage storage)
            throws DukeException {
        String[] data = processUserTaskInput(splitInput);
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
        String[] splitInput = splitUserInput(input);
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
     * @param array Contains strings for task type, description, date/time if applicable.
     * @throws InvalidFormatException If the input format is wrong.
     * @throws EmptyTaskException If input task is not tagged with description.
     */
    public static String[] processUserTaskInput(String[] array) throws InvalidFormatException,
            EmptyTaskException {
        Parser parser = new Parser();
        if (array.length <= 1) {
            if (array[0].equals("event")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a event cannot "
                        + "be empty.");
            } else if (array[0].equals("deadline")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a deadline cannot "
                        + "be empty.");
            } else if (array[0].equals("todo")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a todo cannot "
                        + "be empty.");
            } else {
                throw new InvalidFormatException("Invalid format error!");
            }
        } else if (array[0].equals("event")) {
            String des = "";
            String date = "";
            String time = "";

            boolean toBreak = false;
            int pivotIndex = 0;
            for (int i = 1; i < array.length; i++) {
                if (array[i].equals("/at")) {
                    //sets the breaking point of input
                    toBreak = true;
                    pivotIndex = i;
                    break;
                } else {
                    //before breaking point
                    des += array[i] + " ";
                }
            }
            String[] dateTimeArr = Arrays.copyOfRange(array, pivotIndex + 1, array.length);
            //after breaking point, there should only be a maximum of 2 items remaining
            //first item is date, second item is time
            if (dateTimeArr.length > 2) {
                throw new InvalidFormatException("Please enter a valid format");
            } else {
                if (dateTimeArr.length == 1) {
                    //only has date but no time
                    date = dateTimeArr[0];
                } else if (dateTimeArr.length == 2) {
                    //has date and time
                    date = dateTimeArr[0];
                    time = dateTimeArr[1];
                }
            }
            //index 0 is description, index 1 is date, index 2 is time
            if (toBreak && parser.isValidDate(date) && parser.isValidTime(time)) {
                return new String[]{des, date, time};
            } else {
                //exception is thrown when the format is off, since there is no breaking point, or
                //if the input date or time is wrong
                throw new InvalidFormatException("Please use the correct format and include the "
                        + "keyword: /at");
            }
        } else if (array[0].equals("deadline")) {
            String des = "";
            String date = "";
            String time = "";

            boolean toBreak = false;
            int pivotIndex = 0;
            for (int i = 1; i < array.length; i++) {
                if (array[i].equals("/by")) {
                    //sets the breaking point of input
                    toBreak = true;
                    pivotIndex = i;
                    break;
                } else {
                    //before breaking point
                    des += array[i] + " ";
                }
            }
            String[] dateTimeArr = Arrays.copyOfRange(array, pivotIndex + 1, array.length);
            //after breaking point, there should only be a maximum of 2 items remaining
            //first item is date, second item is time
            if (dateTimeArr.length > 2) {
                throw new InvalidFormatException("Please enter a valid format");
            } else {
                if (dateTimeArr.length == 1) {
                    //only has date but no time
                    date = dateTimeArr[0];
                } else if (dateTimeArr.length == 2) {
                    //has date and time
                    date = dateTimeArr[0];
                    time = dateTimeArr[1];
                }
            }
            //index 0 is description, index 1 is date, index 2 is time
            if (toBreak && parser.isValidDate(date) && parser.isValidTime(time)) {
                return new String[]{des, date, time};
            } else {
                //exception is thrown when the format is off, since there is no breaking point, or
                //if the input date or time is wrong
                throw new InvalidFormatException("Please use the correct format and include the "
                        + "keyword: /by");
            }
        } else if (array[0].equals("todo")) {
            String des = "";
            for (int i = 1; i < array.length; i++) {
                des += array[i];
                if (i != array.length - 1) {
                    //adds a white space in between each word
                    des += " ";
                }
            }
            //index 0 is description
            return new String[]{des};
        }
        return new String[]{};
    }

    /**
     * Calls for the required methods to find a task.
     *
     * @param tasks The current list of tasks.
     * @param splitInput A valid string array is of length 2, index 1 being the description
     *     to find.
     * @return The found tasks.
     */
    public String findCommand(TaskList tasks, String[] splitInput) {
        String desToFind = "";
        for (int i = 1; i < splitInput.length; i++) {
            desToFind += splitInput[i];
            if (i != splitInput.length - 1) {
                desToFind += " ";
            }
        }
        return ui.showFoundTasks(tasks, desToFind);
    }
}

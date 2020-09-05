package seedu.duke;

/**
 * Parser processes each command from Ui and executes program accordingly.
 */
public class Parser {
    private static final String LINES = "\n-----------------------------------------------\n";
    private static final String INVALID_INPUT = "I don't know what that means! Try again.";
    private TaskList taskLists;

    /**
     * Initializes an instance of a Parser.
     *
     * @param tasks List of Tasks stored in a TaskList class.
     */
    public Parser(TaskList tasks) {
        taskLists = tasks;
    }

    /**
     * Checks for invalid inputs.
     *
     * @param input String that is input by user.
     * @throws DukeException if input does not contain any of the keywords in the method.
     */
    public void checkForInvalidInput(String input) throws DukeException {
        if (isInvalidInput(input)) {
            throw new DukeException(INVALID_INPUT + LINES);
        }
    }

    /**
     * Checks for invalid string input.
     *
     * @param input command input by user.
     * @return boolean value depending on valid or invalid input.
     */
    public boolean isInvalidInput(String input) {
        return (!input.contains(Keyword.TODO.label) && !input.contains(Keyword.DEADLINE.label)
                && !input.contains(Keyword.EVENT.label) && !input.contains(Keyword.DONE.label)
                && !input.contains(Keyword.BYE.label) && !input.contains(Keyword.LIST.label)
                && !input.contains(Keyword.DELETE.label) && !input.contains(Keyword.FIND.label));
    }

    /**
     * Processes input by user from CLI and execute accordingly.
     *
     * @param input String input by user.
     */
    public void readCliInput(String input) {
        try {
            this.checkForInvalidInput(input);
            if (input.equals(Keyword.LIST.label)) {
                showTaskList();
            } else if (input.contains(Keyword.DONE.label)) {
                taskLists.completeTaskForCli(input);
            } else if (input.contains(Keyword.DELETE.label)) {
                taskLists.deleteTaskForCli(input);
            } else if (input.contains(Keyword.TODO.label)) {
                taskLists.addToDoForCli(input);
            } else if (input.contains(Keyword.DEADLINE.label)) {
                taskLists.addDeadlineForCli(input);
            } else if (input.contains(Keyword.EVENT.label)) {
                taskLists.addEventForCli(input);
            } else if (input.contains(Keyword.FIND.label)) {
                taskLists.findForCli(input);
            } else {
                System.out.println(INVALID_INPUT);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Takes in input by user from GUI and return Duke output.
     *
     * @param input String input by user.
     * @return String output processed by Duke.
     */
    public String readGuiInput(String input) {
        try {
            this.checkForInvalidInput(input);
            if (input.equals(Keyword.LIST.label)) {
                return showTaskListForGui();
            } else if (input.contains(Keyword.DONE.label)) {
                return taskLists.completeTaskForGui(input);
            } else if (input.contains(Keyword.DELETE.label)) {
                return taskLists.deleteTaskForGui(input);
            } else if (input.contains(Keyword.TODO.label)) {
                return taskLists.addToDoForGui(input);
            } else if (input.contains(Keyword.DEADLINE.label)) {
                return taskLists.addDeadlineForGui(input);
            } else if (input.contains(Keyword.EVENT.label)) {
                return taskLists.addEventForGui(input);
            } else if (input.contains(Keyword.FIND.label)) {
                return taskLists.findForGui(input);
            } else {
                return INVALID_INPUT;
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Print out list of Tasks.
     */
    public void showTaskList() {
        Ui.print(taskLists.toString());
    }

    public String showTaskListForGui() {
        return taskLists.toString();
    }
}

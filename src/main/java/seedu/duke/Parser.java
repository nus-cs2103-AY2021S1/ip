package seedu.duke;

/**
 * Parser processes each command from Ui and executes program accordingly.
 */
public class Parser {
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
     * Processes input by user from CLI and execute accordingly.
     *
     * @param input String input by user.
     */
    public void readCliInput(String input) {
        if (input.equals(Keyword.LIST.label)) {
            taskLists.showTaskListForCli();
        } else if (input.contains(Keyword.DONE.label + " ")) {
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
        } else if (input.equals(Keyword.SORT.label)) {
            taskLists.sortByTasksForCli();
        } else {
            invalidInput(INVALID_INPUT);
        }
    }

    /**
     * Informs user of invalid input.
     *
     * @param textToPrint String input by user.
     */
    private void invalidInput(String textToPrint) {
        Ui.printForCli(INVALID_INPUT);
    }

    /**
     * Takes in input by user from GUI and return Duke output.
     *
     * @param input String input by user.
     * @return String output processed by Duke.
     */
    public String readGuiInput(String input) {
        if (input.strip().equals(Keyword.LIST.label)) {
            return taskLists.showTaskListForGui();
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
        } else if (input.strip().equals(Keyword.SORT.label)) {
            return taskLists.sortByTasksForGui();
        } else {
            return INVALID_INPUT;
        }
    }
}

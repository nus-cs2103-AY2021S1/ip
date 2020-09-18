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

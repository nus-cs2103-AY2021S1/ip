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
     * Parses the input string into an array of strings split by a whitespace.
     *
     * @param input User input.
     * @return String array.
     */
    public String[] parseInput(String input) {
        return input.split(" ");
    }

    /**
     * Takes in input by user from GUI and return Duke output.
     *
     * @param input String input by user.
     * @return String output processed by Duke.
     */
    public String readGuiInput(String input) {
        String[] parsed = parseInput(input);
        String command = parsed[0];
        if (command.equals(Keyword.LIST.label)) {
            return taskLists.showTaskListForGui();
        } else if (command.equals(Keyword.DONE.label)) {
            return taskLists.completeTaskForGui(input);
        } else if (command.equals(Keyword.DELETE.label)) {
            return taskLists.deleteTaskForGui(input);
        } else if (command.equals(Keyword.TODO.label)) {
            return taskLists.addToDoForGui(input);
        } else if (command.equals(Keyword.DEADLINE.label)) {
            return taskLists.addDeadlineForGui(input);
        } else if (command.equals(Keyword.EVENT.label)) {
            return taskLists.addEventForGui(input);
        } else if (command.equals(Keyword.FIND.label)) {
            return taskLists.findForGui(input);
        } else if (command.equals(Keyword.SORT.label)) {
            return taskLists.sortByTasksForGui();
        } else {
            return INVALID_INPUT;
        }
    }
}

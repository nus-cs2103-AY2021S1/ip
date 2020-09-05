package seedu.duke;

/**
 * Parser processes each command from Ui and executes program accordingly.
 */
public class Parser {
    private TaskList taskLists;
    private final String INVALID_INPUT = "I don't know what that means! Try again.";
    private final String LINES = "\n-----------------------------------------------\n";

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
        if (!input.contains("todo") && !input.contains("deadline") && !input.contains("event")
                && !input.contains("done") && !input.contains("bye") && !input.contains("list")
                && !input.contains("delete") && !input.contains("find")) {
            Ui.printLines();
            throw new DukeException(INVALID_INPUT + LINES);
        }
    }

    /**
     * Processes input by user from CLI and execute accordingly.
     *
     * @param input String input by user.
     */
    public void readCliInput(String input) {
        try {
            this.checkForInvalidInput(input);
            if (input.equals("list")) {
                showTaskList();
            } else if (input.contains("done")) {
                taskLists.completeTask(input);
            } else if (input.contains("delete")) {
                taskLists.deleteTask(input);
            } else if (input.contains("todo")) {
                taskLists.addToDo(input);
            } else if (input.contains("deadline")) {
                taskLists.addDeadline(input);
            } else if (input.contains("event")) {
                taskLists.addEvent(input);
            } else if (input.contains("find")) {
                taskLists.find(input);
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
            if (input.equals("list")) {
                return this.showTaskListToString();
            } else if (input.contains("done")) {
                return taskLists.completeTaskToString(input);
            } else if (input.contains("delete")) {
                return taskLists.deleteTaskToString(input);
            } else if (input.contains("todo")) {
                return taskLists.addToDoToString(input);
            } else if (input.contains("deadline")) {
                return taskLists.addDeadlineToString(input);
            } else if (input.contains("event")) {
                return taskLists.addEventToString(input);
            } else if (input.contains("find")) {
                return taskLists.findToString(input);
            } else {
                return "Not valid input!";
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

    public String showTaskListToString() {
        return taskLists.toString();
    }
}

package duke;

public class Duke {

    private TaskList taskList;
    private Parser parser;

    /**
     * Function to get Duke's response to user inputs.
     *
     * @param input User input.
     * @return A string representing Duke's response.
     */
    public String getResponse(String input) {
        return parser.allocate(input, taskList);
    }

    /**
     * Constructor for Duke class
     */
    public Duke() {
        taskList = new TaskList();
    }
}

package duke;

public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a new instance of Duke.
     * Duke handles the operations of the programme.
     */
    public Duke() {
        taskList = new TaskList();
        ui = new Ui();
        parser = new Parser(taskList, ui);
        Storage.loadTasksFrom("data/duke.txt", taskList);
    }

    public void loadTasks() {
        Storage.loadTasksFrom("data/duke.txt", taskList);
    }

    public void saveTasks() {
        Storage.saveTasksTo("data/duke.txt", taskList);
    }

    /**
     * Parses the input and performs the necessary operations based on its contents.
     * @param input The command entered by the user.
     * @return String output to be displayed to the user.
     */
    public String parseInput(String input) {
        String output = parser.parse(input);
        // System.out.println(output);
        return output;
    }
}

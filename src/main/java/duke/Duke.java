package duke;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private boolean isExit = false;

    /**
     * Initialize a Duke instance.
     */
    public Duke() {
        TaskList taskList = new TaskList();
        loadFile(taskList);
        Ui ui = new Ui();
        this.isExit = false;
        Parser.setTaskList(taskList);
    }
    /**
     * Generates a response to user input.
     *
     * @param input User input.
     * @return Response string.
     */
    public String getResponse(String input) {
        try {
            String response = Parser.parseCommand(input);
            if (Parser.isExit()) {
                this.isExit = true;
            }
            return response;
        } catch (DukeException e) {
            return Ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Returns whether the program should exit.
     *
     * @return A boolean.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Loads the saved tasks.
     *
     * @param taskList Task list.
     */
    public void loadFile(TaskList taskList) {
        try {
            Storage.loadFromFile(taskList);
        } catch (DukeException e) {
            System.out.println(Ui.getErrorMessage(e.getMessage()));
        }
    }

    /**
     * Run the duke program.
     */
    public void run() {
        TaskList taskList = new TaskList();
        loadFile(taskList);
        Ui ui = new Ui();
        System.out.print(Ui.greet());
        Parser.setTaskList(taskList);
        while (!this.isExit) {
            try {
                String command = ui.readInput();
                if (Parser.stopProgram(command)) {
                    this.isExit = true;
                    Storage.writeToFile(taskList);
                    System.out.print(ui.exit());
                } else {
                    System.out.print(Parser.parseCommand(command));
                }
            } catch (DukeException e) {
                System.out.println(Ui.getErrorMessage(e.getMessage()));
            }
        }
    }

    /**
     * Entry point for the Duke program.
     * @param args Args.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}

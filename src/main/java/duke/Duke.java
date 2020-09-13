package duke;
import duke.task.TaskList;

public class Duke {
    private static final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    private boolean isExit = false;

    Duke() {
        // initializing plus greeting
        TaskList taskList = new TaskList();
        Storage.loadFromFile(taskList);
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
     * Sets exit states to true.
     */
    public void setExit() {
        this.isExit = true;
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
     * Run the duke program.
     */
    public void run() {
        TaskList taskList = new TaskList();
        Storage.loadFromFile(taskList);
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

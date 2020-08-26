/**
 * Duke, more commonly known as Duck, is a Personal Assistant Chat Bot that
 * helps a person to keep track of various tasks.
 * Contains static attribute stored_task which stores task input from user.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initializes Duke containing the storage, ui and task list.
     *
     * @param filePath Filepath of where storage files are stored.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Runs Duke chat bot.
     */
    public void run() {
        boolean isInProgram = true;

        ui.greet();
        while (isInProgram) {
            try {
                String input = ui.getNextInput();
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);
                isInProgram = command.isInProgram();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * Creates a duke object named duck and runs it.
     **/
    public static void main(String[] args) {
        Duke duck = new Duke("data/duke.txt");
        duck.run();
    }
}

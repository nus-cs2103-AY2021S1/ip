package duke;

/**
 * Encapsulates the main entry point of the duke program.
 */
public class Duke {
    private TaskList list;
    private Storage storage;
    private Ui ui;

    /**
     * Instantiates a Duke object.
     * @param filePath path to locate/save the save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        list = storage.loadFile();
    }

    /**
     * Instantiates a Duke object, with default filePath ".//data//duke.data".
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(".//data//duke.data");
        list = storage.loadFile();
    }

    // private void run() {
    //     Ui.printGreeting();
    //
    //     String input = ui.getInput();
    //     Command command = Parser.parse(input);
    //     while (command.getTaskType() != TaskType.BYE) {
    //         String result = command.execute(list);
    //         Ui.print(result);
    //
    //         input = ui.getInput();
    //         command = Parser.parse(input);
    //     }
    //
    //     storage.saveFile(list);
    //     Ui.printGoodbye();
    // }

    public String getResponse(String input) {
        Command command = Parser.parse(input);
        return command.execute(list);
    }

    // /**
    //  * Entry point for the Duke program
    //  * @param args command line arguments
    //  */
    // public static void main(String[] args) {
    //     new Duke(".//data//duke.data").run();
    // }
}

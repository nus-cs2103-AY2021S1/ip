/**
 * Encapsulates the main Duke program.
 */
public class Duke {

    /**
     * Member variables that make up the main Duke program.
     */
    private DukeSaver saver;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    /**
     * Starts Duke.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Constructor for the Duke object.
     *
     * @param savePath Directory path to the save file.
     */
    public Duke(String savePath) {
        saver = new DukeSaver(savePath);
        taskList = new TaskList();
        ui = new UI();
        parser = new Parser(ui, taskList, saver);
    }

    /**
     * Holds the main driver code.
     */
    private void run() {
        saver.loadData(taskList);
        ui.greet();
        while (true) {
            String response = ui.prompt();
            try {
                parser.handleResponse(response);
            } catch (DukeException ex) {
                ui.print(ex.toString());
            }
        }
    }
}

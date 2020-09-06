/**
 * Encapsulates the main Duke program.
 */
public class Duke {

    /**
     * Member variables that make up the main Duke program.
     */
    private String savePath = "data/duke.txt";
    private DukeSaver saver;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructor for the Duke object.
     */
    public Duke() {
        saver = new DukeSaver(savePath);
        taskList = new TaskList();
        parser = new Parser(taskList, saver);
        saver.loadData(taskList);
    }

    public String getResponse(String input) {
        try {
            return parser.handleResponse(input);
        } catch (DukeException ex) {
            return ex.toString();
        }
    }
}

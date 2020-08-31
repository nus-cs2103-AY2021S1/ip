/**
 * Main driver class for Duke.
 */
public class Duke {
    protected Storage storage;
    protected Ui ui;
    protected TaskList arrayOfTasks;

    /**
     * Instantiates Duke object.
     * @param path File path giving the location of the input file.
     */
    public Duke(String path) {
        this.storage = new Storage(path);
        this.ui = new Ui();
    }

    /**
     * Loads input from duke.txt
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            this.arrayOfTasks = new TaskList(storage.load());
        } catch (DukeException error) {
            System.out.println("Error loading from specified file path");
            this.arrayOfTasks = new TaskList();
        }
    }

    /**
     * Gets UI object.
     *
     * @return UI object
     */
    public Ui getUI() {
        return ui;
    }

    /**
     * Gets Response object.
     * @return Response object
     */
    public Response getResponse(String userInput) {
        Response response = execute(userInput);
        return response;
    }

    /**
     * Parses command.
     * @param userInput command to be parsed
     * @return Response object
     */
    private Response execute(String userInput) {
        Response responseObject;
        try {
            Command parsedCommand = Parser.parse(userInput);
            responseObject = parsedCommand.runCommand(arrayOfTasks, ui, storage);
        } catch (DukeException error) {
            String errorMsg = error.getMessage();
            responseObject = ui.returnError(errorMsg);
        }
        return responseObject;
    }
}

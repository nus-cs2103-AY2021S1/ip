import java.io.File;

/**
 * Main driver class for Duke.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList arrayOfTasks;

    /**
     * Instantiates Duke object.
     * @param path File path giving the location of the input file.
     */
    public Duke(String path) {
        assert !path.equals("") : "File path should not be empty!";
        this.storage = new Storage(path);
        this.ui = new Ui();
    }

    /**
     * Loads input from duke.txt
     * Creates a 'data' folder if it does not exist in current directory.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        File newDataFolder = new File("data");
        newDataFolder.mkdir();
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
        assert !userInput.equals("") : "String from user input should not be empty!";
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
        assert arrayOfTasks != null || ui != null || storage != null
                : "arrayOfTasks, Ui and Storage objects cannot be null";
        try {
            Command parsedCommand = Parser.parse(userInput);
            responseObject = parsedCommand.runCommand(arrayOfTasks, ui, storage);
        } catch (DukeException error) {
            String errorMsg = error.getMessage();
            responseObject = ui.returnError(errorMsg);
        }
        assert responseObject != null : "There is no Response object being returned by Command object.";
        return responseObject;
    }
}

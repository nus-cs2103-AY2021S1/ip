public class Duke {

    /** TaskList class that stores and deals with the tasks **/
    private TaskList taskList;
    /** Parser class that parse and deal with the commands given **/
    private Parser parser;
    /** Storage class that handles loads and saves the task from/to hard drive **/
    private Storage storage;
    /** UI class that is responsible for the interaction with the user **/
    private Ui ui;

    /**
     *Class constructor
     */
    public Duke() {
        taskList = new TaskList();
        parser = new Parser(taskList);
        ui = new Ui();

        try {
            storage = new Storage(taskList);
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Initiate the stop to the program
     * Save the stored tasks into the hard drive
     */
    public void stop() {
        try {
            storage.saveFile();
        } catch (DukeException e) {
            ui.showSavingError();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parseCommand(splitCommand(input));
    }

    public String[] splitCommand(String input){
        return input.trim().split(" ", 2);
    }

}
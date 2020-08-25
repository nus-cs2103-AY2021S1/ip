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
        this.taskList = new TaskList();
        this.parser = new Parser(taskList);
        this.ui = new Ui();
        try{
            this.storage = new Storage(taskList,parser);
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Initialize the start of the program
     */
    public void run() {
        ui.printStarting();
        while(taskList.isUpdating()) {
            String[] fullCommand = ui.readCommand();
            parser.ParseCommand(fullCommand);
            ui.printLine();
        }
    }

    /**
     * Initiate the stop to the program
     * Save the stored tasks into the hard drive
     */
    public void stop() {
        try{
            storage.saveFile();
        }catch(DukeException e){
            ui.showSavingError();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
        duke.stop();
    }

}
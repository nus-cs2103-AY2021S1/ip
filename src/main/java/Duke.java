/**
 * Initializes the chatbot and starts up the UI to take in user input
 */
public class Duke {
    /**
     * ui refers to the User Interface object which will be used to accept user input
     */
    private UI ui;

    /**
     * Uses the filepath to intiialize the storage and hence, the UI object
     * @param filePath
     */
    public Duke(String filePath){
        Storage storage = new Storage(filePath);
        this.ui = new UI(storage);
    }

    /**
     * Looks for user input using the UI object
     */
    public void run(){
        ui.welcome();
        ui.run();
        ui.escape();
    }

    public static void main(String[] args) {
        new Duke("Data/duke.txt").run();
    }
}

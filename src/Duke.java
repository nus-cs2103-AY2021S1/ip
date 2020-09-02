import java.io.IOException;

public class Duke {
    private Storage storage;
    //private TaskList tasks;
    private Ui ui;

    /**
     * Method to initialize a Chatbot instance and start the bot. Catch errors specific to the bot.
     * @param args
     */
    public static void main(String[] args) throws DukeException, IOException {
        Ui ui = new Ui();
        ui.chat();
    }
}

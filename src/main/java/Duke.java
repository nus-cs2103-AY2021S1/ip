/**
 * The Duke chat bot that can help you keep track of your upcoming schedules.
 * Use the commands todo, event, deadline to differentiate your schedules.
 */
public class Duke {
    private Storage storage;
    private UI ui;

    public Duke() {
        this.storage = new Storage();
        this.ui = UI.initialise(this.storage);
    }

    private void run() {
        try {
            this.storage.initialise();
            this.ui.startUpMessage();
            this.ui.processInput();
        } catch (Exception e) {
           this.ui.showError(e);
        }
    }

    /**
     * This is the main method that creates a Duke object and runs the bot.
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}


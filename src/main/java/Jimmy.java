/**
 * The {@code Jimmy} class represents the core of the Jimmy/Duke
 * program. The class allows the program to start/end, and it
 * facilitates the behaviour of the program by making use of the
 * {@code Ui}, {@code Storage}, {@code Parser} and {@code TaskList}
 * classes.
 */
public class Jimmy {

    private final Ui ui;
    private final Storage storage;
    private final TaskList planner;
    private boolean isLoaded;

    /**
     * Initialises an instance of Jimmy.
     */
    public Jimmy() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.planner = new TaskList();
        this.isLoaded = false;
    }

    /**
     * Loads any pre-existing data stored from previous use of the
     * program. Method does nothing if no prior data is found.
     */
    public void loadList() {
        storage.loadList(planner);
        this.isLoaded = true;
    }

    /**
     * Saves current {@code TaskList} into {@code Storage} for future
     * reference. Method is usually called when the program terminates.
     */
    public void saveList() {
        storage.updateList(planner);
    }

    /**
     * Starts the program on the CLI (console terminal) and allows current
     * user to feed input and interact with the program. Upon inputting the
     * "bye" command, the method allows the program to terminate.
     */
    public void run() {
        ui.print(ui.greet());
        loadList();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.process();
                if (command.equals("bye")) {
                    isExit = true;
                }
                String reply = Parser.parse(planner, command);
                ui.print(reply);
            } catch (JimmyException e) {
                ui.print(e.getMessage());
            }
        }
        saveList();
    }

    /**
     * Generates {@code String }response from user input. This method is
     * used as a helper function for the GUI part of the program.
     *
     * @param input The text that is typed by the user into the GUI text box.
     * @return Appropriate response to user input.
     */
    public String getResponse(String input) {
        if (!isLoaded) {
            loadList();
        }
        try {
            String reply = Parser.parse(planner, input);
            if (input.equals("bye")) {
                saveList();
                reply += "\n Click the \" X \" button to close this window.";
            }
            return reply;
        } catch (JimmyException e) {
            ui.print(e.getMessage());
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Jimmy().run();
    }
}

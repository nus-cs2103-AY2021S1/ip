import javafx.application.Platform;

public class Jimmy {

    private final Ui ui;
    private final Storage storage;
    private final TaskList planner;
    private boolean isLoaded;

    public Jimmy() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.planner = new TaskList();
        this.isLoaded = false;
    }

    public void loadList() {
        storage.loadList(planner);
        this.isLoaded = true;
    }

    public void saveList() {
        storage.updateList(planner);
    }

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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

package duke;

public class Duke {
    public static void main(String[] args) {
        initialize();
    }

    /**
     * Starts the chatbot.
     */
    public static void initialize() {
        Storage storage = new Storage("./data");
        if (Storage.hasLoadingError) {
            return;
        }
        Ui.greet();
        TaskListHandler handler = new TaskListHandler(storage.getListFromFile());
        Ui userInterface = new Ui(handler, storage);
        userInterface.run();
    }
}

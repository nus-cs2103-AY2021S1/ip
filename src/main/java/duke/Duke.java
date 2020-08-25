package duke;

public class Duke {
    public static void main(String[] args)  {
        initialize();
    }

    public static void initialize() {
        Storage storage = new Storage("./data");
        if (Storage.isLoadingError) {
            return;
        }
        Ui.greet();
        taskListHandler handler = new taskListHandler(storage.getListFromFile());
        Ui userInterface = new Ui(handler, storage);
        userInterface.run();
    }
}
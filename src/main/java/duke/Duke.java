package duke;

public class Duke {
    public static void main(String[] args)  {
        initialize();
    }

    public static void initialize() {
        Storage storage = new Storage("./src/main/java/duke/data");
        if (Storage.isLoadingError) {
            return;
        }
        Ui.greet();
        TaskListHandler handler = new TaskListHandler(storage.getListFromFile());
        Ui userInterface = new Ui(handler, storage);
        userInterface.run();
    }
}

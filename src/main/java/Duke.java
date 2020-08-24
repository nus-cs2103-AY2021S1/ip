public class Duke {
    public static void main(String[] args)  {
        initialize();
    }

    public static void initialize() {
        Ui.greet();
        Storage storage = new Storage();
        TaskListHandler handler = new TaskListHandler(storage.getListFromFile());
        Ui userInterface = new Ui(handler, storage);
        userInterface.run();
    }
}

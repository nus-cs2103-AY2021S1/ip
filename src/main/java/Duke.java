import java.io.IOException;

public class Duke {
    static final String FILE_PATH = "savedTasks.txt";

    public static void main(String[] args) {
        Ui.showWelcomeMessage();

        Storage storage = new Storage(FILE_PATH);
        try {
            Parser parser;
            if (storage.doesExist()) {
                TaskList savedList = storage.load();
                parser = new Parser(storage, savedList);
                Ui.showSuccessfulLoad();
            } else {
                Ui.showNoExistingSave();
                parser = new Parser(storage);
            }
            parser.scan();
        } catch (IOException e) {
            Ui.showErrorMessage("An exception occurred:", e);
        } catch (ClassNotFoundException e) {
            Ui.showErrorMessage("The file specified doesn't store a TaskList object.", e);
        }
    }
}

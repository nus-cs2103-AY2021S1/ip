import java.io.File;
import java.util.Scanner;

public class Chatbot {
    private final String dataPathName = "./data";
    private final String dataFilePath = "./data/dukedata.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Chatbot() {
        ui = new Ui();
        storage = new Storage(dataPathName, dataFilePath);
        File file = storage.getSavedData(ui);

        if (file != null) {
            tasks = new TaskList(file, ui);
        } else {
            tasks = new TaskList();
        }
    }

    public void start() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        while (!Parser.isDone()) {
            String input = scanner.nextLine();
            Parser.parse(input, storage, tasks, ui);
        }

        scanner.close();
    }
}

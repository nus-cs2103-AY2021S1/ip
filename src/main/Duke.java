import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    protected final static String DATA_FILE_PATH = "data/dukeData.txt";

    public Duke(String filePath) {
        ui = new Ui();

        ui.printLogo();
        System.out.println("Initializing...");

        storage = new Storage(filePath);
        parser = new Parser();
        try {
            ArrayList<Task> existingTasks = storage.loadExistingData();
            tasks = new TaskList(existingTasks);
            ui.printWelcome(existingTasks);
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            new Ui("An error occurred while retrieving the data.").printMessage();

        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            parser.parse(input, tasks, storage, ui);
        }
    }

    public static void main(String[] args) {
        new Duke(DATA_FILE_PATH).run();
    }
}
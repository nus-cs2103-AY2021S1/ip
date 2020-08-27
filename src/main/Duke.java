import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    protected final static String DATA_FILE_PATH = "./data/dukeData.txt";

    public Duke(String filePath) {
        ui = new Ui();
        ui.printLogo();
        System.out.println("Initializing...");

        storage = new Storage(filePath);
        parser = new Parser();

        ui.printWelcome();

        try {
            tasks = new TaskList(storage.loadExistingData());
            ui.printExistingTasks(storage.loadExistingData());
            ui.printWhatCanIDo();
        } catch (Exception e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            parser.parse(input, tasks, storage, ui, sc);
        }
    }

    public static void main(String[] args) {
        new Duke(DATA_FILE_PATH).run();
    }
}
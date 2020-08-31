import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            // ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        ui.greeting();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            Parser.parseUserInput(input, ui, tasks, storage);
        }
    }

}

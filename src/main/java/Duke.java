import java.util.Scanner;

public class Duke {
    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printGeneralChatWindow(e.toString());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        // Initial greeting, prompt user for commands
        ui.printWelcome();

        // Start scanning for user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Loop continues echoing input until input == "bye"
        while (!input.equals("bye")) {
            // Print top border
            ui.printBorder();

            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printGeneralChatWindow(e.toString());
            } finally {
                // Print bottom border
                ui.printBorder();

                // Scan for any more inputs
                input = sc.nextLine();
            }
        }

        // Print goodbye chat window
        ui.printGoodbye();
    }
}

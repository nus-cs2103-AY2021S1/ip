/**
 * duke is a chat bot cum task manager
 */

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
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.showWelcome();

        String input = ui.getNextLine();
        String firstWord = input.split(" ")[0];

        StringBuilder sb = new StringBuilder();

        while (!firstWord.equals("bye")) {
            sb.append("--------------------------------------------------------------\n")
                    .append(ui.respondToUser(input, tasks))
                    .append("\n--------------------------------------------------------------");
            storage.save(tasks);
            System.out.println(sb.toString());
            sb = new StringBuilder();
            input = ui.getNextLine();
            firstWord = input.split(" ")[0];
        }
        sb.append("--------------------------------------------------------------\n")
                .append(ui.exit())
                .append("\n--------------------------------------------------------------");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
